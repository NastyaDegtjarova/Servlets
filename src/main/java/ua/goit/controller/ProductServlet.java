package ua.goit.controller;

import ua.goit.dao.ManufacturerDAO;
import ua.goit.dao.ProductDAO;
import ua.goit.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.model.Manufacturer;
import ua.goit.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/prod")
public class ProductServlet extends AbstractBaseServlet {
    private static final Logger LOGGER = LogManager.getLogger(ProductServlet.class);

    private ProductDAO productDAO = HibernateProductDAOImpl.getInstance();
    private ManufacturerDAO manufacturerDAO = HibernateManufacturerDAOImpl.getInstance();

    @Override

    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Product product = productDAO.getById(id);
            req.setAttribute("prod", product);
            req.getRequestDispatcher("/editProd.jsp").forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    private void forwardToProds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/prods").forward(req, resp);
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
            Product product = productDAO.getById(id);
            productDAO.delete(product);
            forwardToProds(req, resp);
        } catch (Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.isEmpty()) {
            forwardToErrorPage(name, req, resp);
            return;
        }

        String manufIdParam = req.getParameter("manufId");
        if (manufIdParam == null || manufIdParam.isEmpty()) {
            req.setAttribute("errMessage", "manufId is empty");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        String priceParam = req.getParameter("price");
        if (priceParam == null || priceParam.isEmpty()) {
            req.setAttribute("errMessage", "priceParam is empty");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        try {
            Manufacturer manufacturer = manufacturerDAO.getById(Long.parseLong(manufIdParam));
            Product product = new Product(name, BigDecimal.valueOf(Long.parseLong(priceParam)), manufacturer);
            productDAO.save(product);
            forwardToProds(req, resp);
        } catch (Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.isEmpty()) {
            forwardToErrorPage(name, req, resp);
            return;
        }
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            forwardToErrorPage(idParam, req, resp);
            return;
        }
        String idManufactParam = req.getParameter("manufId");
        if (idManufactParam == null || idManufactParam.isEmpty()) {
            forwardToErrorPage(idManufactParam, req, resp);
            return;
        }
        String priceProductParam = req.getParameter("price");
        if (priceProductParam == null || priceProductParam.isEmpty()) {
            forwardToErrorPage(priceProductParam, req, resp);
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Product product = productDAO.getById(id);
            Manufacturer manufacturer = manufacturerDAO.getById(Long.parseLong(idManufactParam));
            product.setNameProduct(name);
            product.setManufacturer(manufacturer);
            product.setPriceProduct(BigDecimal.valueOf(Long.parseLong(priceProductParam)));
            productDAO.update(product);
            forwardToProds(req, resp);
        } catch (Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
