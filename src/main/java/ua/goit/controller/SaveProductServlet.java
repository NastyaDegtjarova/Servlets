package ua.goit.controller;

import ua.goit.dao.ManufacturerDAO;
import ua.goit.dao.ProductDAO;
import ua.goit.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.model.Manufacturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/saveProd")
public class SaveProductServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(SaveProductServlet.class);
    public static final String EDIT_PROD_JSP = "/editProd.jsp";
    public static final String SAVE_PROD_JSP = "/saveProd.jsp";
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
            req.getRequestDispatcher(SAVE_PROD_JSP).forward(req, resp);
        } catch(Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher(AbstractBaseServlet.ERROR_JSP).forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Manufacturer> manufacturers = manufacturerDAO.getAll();
            req.setAttribute("manufs", manufacturers);
            req.setAttribute("id", req.getParameter("id"));
            req.setAttribute("name", req.getParameter("name"));
            req.setAttribute("price", req.getParameter("price"));
            Manufacturer manufacturer = manufacturerDAO.getById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("manufIdSelect", manufacturer.getIdManufact());
            req.setAttribute("manufNameSelect", manufacturer.getNameManufact());
            req.getRequestDispatcher(EDIT_PROD_JSP).forward(req, resp);
        } catch(Exception e) {
            req.setAttribute("errMessage", e.getMessage());
            req.getRequestDispatcher(AbstractBaseServlet.ERROR_JSP).forward(req, resp);
        }
    }

    private void forwardToProds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/prods").forward(req, resp);
    }

}
