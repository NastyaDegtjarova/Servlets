package ua.goit.controller;

import org.apache.commons.lang.StringUtils;
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
            Long id = Long.parseLong(req.getParameter(ID_PARAM));
            Manufacturer manufacturer = manufacturerDAO.getById(id);
            req.setAttribute(MANUF_ATTR, manufacturer);
            req.getRequestDispatcher(EDIT_MANUF_JSP).forward(req, resp);
        } catch(Exception e) {
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    private void forwardToManufs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/manufs").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ID_PARAM);
        LOGGER.info("id = " + idParam);
        if (StringUtils.isBlank(idParam)) {
            forwardToErrorPage(idParam, req, resp);
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Manufacturer manufacturer = manufacturerDAO.getById(id);
            manufacturerDAO.delete(manufacturer);
            forwardToManufs(req, resp);
        } catch (Exception e) {
            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAM);
        if (StringUtils.isBlank(name)) {
            manufacturerDAO.save(new Manufacturer(name));
        }
        forwardToManufs(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAM);
        LOGGER.info("name = " + name);
        if (StringUtils.isBlank(name)) {
            forwardToErrorPage(name, req, resp);
            return;
        }
        String idParam = req.getParameter(ID_PARAM);
        LOGGER.info("id = " + idParam);
        if (StringUtils.isBlank(idParam)) {
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
            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }
}
