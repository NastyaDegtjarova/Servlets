package net.proselyte.controller;

import net.proselyte.dao.ProductDAO;
import net.proselyte.dao.hibernate.HibernateProductDAOImpl;
import net.proselyte.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Анастасия on 24.01.2018.
 */
@WebServlet(urlPatterns = "/prods")
public class ProductsServlet extends HttpServlet {
    private ProductDAO productDAO = HibernateProductDAOImpl.getInstance();
    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.getAll();
        req.setAttribute("prods", products);
        req.getRequestDispatcher("/prods.jsp").forward(req, resp);
    }
}
