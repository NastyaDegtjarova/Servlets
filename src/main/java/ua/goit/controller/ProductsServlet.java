package ua.goit.controller;

import ua.goit.dao.ProductDAO;
import ua.goit.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/prods")
public class ProductsServlet extends HttpServlet {
    private ProductDAO productDAO = HibernateProductDAOImpl.getInstance();
    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.getAll();
        req.setAttribute("prods", products);
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
