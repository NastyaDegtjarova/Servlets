package ua.goit.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractBaseServlet extends HttpServlet {
    public static final String SAVE_ACTION = "save";
    public static final String UPDATE_ACTION = "update";
    public static final String DELETE_ACTION = "delete";
    public static final String ACTION_PARAM = "action";
    public static final String ERROR_JSP = "/error.jsp";
    public static final String EDIT_MANUF_JSP = "/editManuf.jsp";
    public static final String EDIT_PROD_JSP = "/editProd.jsp";
    public static final String PRODUCTS_JSP = "/products.jsp";

    public static final String MANUF_ATTR = "manuf";
    public static final String ERR_MESSAGE_ATTR = "errMessage";
    public static final String PROD_ATTR = "prod";

    public static final String ID_PARAM = "id";
    public static final String NAME_PARAM = "name";
    public static final String MANUF_ID_PARAM = "manufId";
    public static final String PRICE_PARAM = "price";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAM);
        if (action == null || action.isEmpty()) {
            req.setAttribute("errMessage", "action is empty");
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
            return;
        }
        switch (action) {
            case SAVE_ACTION:
                save(req, resp);
                break;
            case UPDATE_ACTION:
                update(req, resp);
                break;
            case DELETE_ACTION:
                delete(req, resp);
                break;
            default:
                req.setAttribute("errMessage", "action is wrong");
                req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
                break;
        }
    }

    abstract protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    abstract protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    abstract protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected void forwardToErrorPage(String paramName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errMessage", paramName + " is empty");
        req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
    }
}
