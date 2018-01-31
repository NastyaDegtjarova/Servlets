package ua.goit.controller;

import org.apache.commons.lang.StringUtils;
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
            Long id = Long.parseLong(req.getParameter(ID_PARAM));
            Product product = productDAO.getById(id);
            req.setAttribute(PROD_ATTR, product);
            req.getRequestDispatcher(EDIT_PROD_JSP).forward(req, resp);
        } catch (Exception e) {
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    private void forwardToProds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/prods").forward(req, resp);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter(ProductServlet.ID_PARAM);
        LOGGER.info("id = " + idParam);
        if (StringUtils.isBlank(idParam)) {
            forwardToErrorPage(idParam, req, resp);
            return;
        }
        try {
            long id = Long.parseLong(idParam);
            Product product = productDAO.getById(id);
            productDAO.delete(product);
            forwardToProds(req, resp);
        } catch (Exception e) {
            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAM);
        if (StringUtils.isBlank(name)) {
            forwardToErrorPage(name, req, resp);
            return;
        }

        String manufIdParam = req.getParameter(MANUF_ID_PARAM);
        if (StringUtils.isBlank(manufIdParam)) {
            forwardToErrorPage(manufIdParam, req, resp);
            return;
        }

        String priceParam = req.getParameter(PRICE_PARAM);
        if (StringUtils.isBlank(priceParam)) {
            forwardToErrorPage(priceParam, req, resp);
            return;
        }

        try {
            Manufacturer manufacturer = manufacturerDAO.getById(Long.parseLong(manufIdParam));
            Product product = new Product(name, BigDecimal.valueOf(Long.parseLong(priceParam)), manufacturer);
            productDAO.save(product);
            forwardToProds(req, resp);
        } catch (Exception e) {
            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAM);
        if (StringUtils.isBlank(name)) {
            forwardToErrorPage(name, req, resp);
            return;
        }
        String idParam = req.getParameter(ID_PARAM);
        if (StringUtils.isBlank(idParam)) {
            forwardToErrorPage(idParam, req, resp);
            return;
        }
        String idManufactParam = req.getParameter(MANUF_ID_PARAM);
        if (StringUtils.isBlank(idManufactParam)) {
            forwardToErrorPage(idManufactParam, req, resp);
            return;
        }
        String priceProductParam = req.getParameter(PRICE_PARAM);
        if (StringUtils.isBlank(priceProductParam)) {
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
            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }
}
