package net.proselyte.controller;

import net.proselyte.dao.ProductDAO;
import net.proselyte.dao.hibernate.HibernateProductDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Анастасия on 27.12.2017.
 */
public class ProductServlet extends HttpServlet {
    private ProductDAO manufacturerDAO = HibernateProductDAOImpl.getInstance();
    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //manufacturerDAO.getAll();
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
