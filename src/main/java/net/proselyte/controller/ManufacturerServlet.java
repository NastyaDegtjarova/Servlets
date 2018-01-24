package net.proselyte.controller;

import net.proselyte.dao.ManufacturerDAO;
import net.proselyte.dao.hibernate.HibernateManufacturerDAOImpl;
import net.proselyte.model.Manufacturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nastya on 20.12.2017.
 */
@WebServlet(urlPatterns = "/manuf")
public class ManufacturerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ManufacturerServlet.class);
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
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    private void forwardToManufs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/manufs").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name != null && !name.isEmpty()) {
            manufacturerDAO.save(new Manufacturer(name));
            System.out.println("save");
        }
        forwardToManufs(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        logger.info("name = " + name);
        if (name == null || name.isEmpty()) {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        String idParam = req.getParameter("id");
        logger.info("id = " + idParam);
        if (idParam == null || idParam.isEmpty()) {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        try {
            long id = Long.parseLong(idParam);
            Manufacturer manufacturer = manufacturerDAO.getById(id);
            manufacturer.setNameManufact(name);
            manufacturerDAO.save(manufacturer);
            forwardToManufs(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
