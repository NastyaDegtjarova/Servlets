package net.proselyte.controller;

import net.proselyte.dao.ManufacturerDAO;
import net.proselyte.dao.hibernate.HibernateManufacturerDAOImpl;
import net.proselyte.model.Manufacturer;

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
@WebServlet(urlPatterns = "/manufs")
public class ManufacturersServlet extends HttpServlet {
    private ManufacturerDAO manufacturerDAO = HibernateManufacturerDAOImpl.getInstance();
    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Manufacturer> manufacturers = manufacturerDAO.getAll();
        req.setAttribute("manufs", manufacturers);
        req.getRequestDispatcher("/manufacts.jsp").forward(req, resp);
    }
}
