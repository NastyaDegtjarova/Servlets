package ua.goit.controller;

import ua.goit.dao.ManufacturerDAO;
import ua.goit.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.model.Manufacturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/manuf")
public class ManufacturerServlet extends AbstractBaseServlet {
    private static final Logger LOGGER = LogManager.getLogger(ManufacturerServlet.class);
    private ManufacturerDAO manufacturerDAO = HibernateManufacturerDAOImpl.getInstance();
    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Manufacturer manufacturer = manufacturerDAO.getById(id);
            req.setAttribute("manuf", manufacturer);
            req.getRequestDispatcher("/editManuf.jsp").forward(req, resp);
        } catch(Exception e) {
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    private void forwardToManufs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/manufs").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        LOGGER.info("id = " + idParam);
        if (idParam == null || idParam.isEmpty()) {
            forwardToErrorPage(idParam, req, resp);
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Manufacturer manufacturer = manufacturerDAO.getById(id);
            manufacturerDAO.delete(manufacturer);
            forwardToManufs(req, resp);
        } catch (Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name != null && !name.isEmpty()) {
            manufacturerDAO.save(new Manufacturer(name));
        }
        forwardToManufs(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        LOGGER.info("name = " + name);
        if (name == null || name.isEmpty()) {
            forwardToErrorPage(name, req, resp);
            return;
        }
        String idParam = req.getParameter("id");
        LOGGER.info("id = " + idParam);
        if (idParam == null || idParam.isEmpty()) {
            forwardToErrorPage(idParam, req, resp);
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Manufacturer manufacturer = manufacturerDAO.getById(id);
            manufacturer.setNameManufact(name);
            manufacturerDAO.update(manufacturer);
            forwardToManufs(req, resp);
        } catch (Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }
}
