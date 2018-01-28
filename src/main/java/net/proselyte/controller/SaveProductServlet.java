package net.proselyte.controller;

import net.proselyte.dao.ManufacturerDAO;
import net.proselyte.dao.ProductDAO;
import net.proselyte.dao.hibernate.HibernateManufacturerDAOImpl;
import net.proselyte.dao.hibernate.HibernateProductDAOImpl;
import net.proselyte.model.Manufacturer;
import net.proselyte.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Анастасия on 27.12.2017.
 */
@WebServlet(urlPatterns = "/saveProd")
public class SaveProductServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(SaveProductServlet.class);
    private ProductDAO productDAO = HibernateProductDAOImpl.getInstance();
    private ManufacturerDAO manufacturerDAO = HibernateManufacturerDAOImpl.getInstance();

    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Manufacturer> manufacturers = manufacturerDAO.getAll();
            req.setAttribute("manufs", manufacturers);
            req.getRequestDispatcher("/saveProd.jsp").forward(req, resp);
        } catch(Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    private void forwardToProds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/prods").forward(req, resp);
    }

}
