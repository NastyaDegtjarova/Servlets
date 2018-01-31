package ua.goit.controller;

import ua.goit.dao.ManufacturerDAO;
import ua.goit.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/manufs")
public class ManufacturersServlet extends HttpServlet {
    private ManufacturerDAO manufacturerDAO = HibernateManufacturerDAOImpl.getInstance();
    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Manufacturer> manufacturers = manufacturerDAO.getAll();
        req.setAttribute("manufs", manufacturers);
        req.getRequestDispatcher("/manufacts.jsp").forward(req, resp);
    }
}
