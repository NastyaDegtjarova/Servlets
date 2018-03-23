package ua.goit.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.model.Manufacturer;
import ua.goit.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.goit.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

//    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getProduct(@PathVariable("productId") Long productId) {
        if (productId == null) {
            return "error";
        }

        Product product = this.productService.getById(productId);

        if (product == null) {
            return "error";
        }

        return "products";
    }

//    @RequestMapping(value = { "/prod" }, method = RequestMethod.GET)
//    protected ResponseEntity<List<Product>> getProduct()   {
//        try {
//            Long id = Long.parseLong(req.getParameter(ID_PARAM));
//            Product product = productDAO.getById(id);
//            req.setAttribute(PROD_ATTR, product);
//            req.getRequestDispatcher(EDIT_PROD_JSP).forward(req, resp);
//        } catch (Exception e) {
//            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
//        }
//    }

//    private void forwardToProds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/prods").forward(req, resp);
//    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllProducts() {
        List<Product> productList = this.productService.getAll();

        if (productList.isEmpty()) {
            return "error";
        }

        return "products";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteProduct(@PathVariable("prodId") Long prodId) {
        Product product = this.productService.getById(prodId);

        if (product == null) {
            return "error";
        }

        this.productService.delete(prodId);

        return "products";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return "error";
        }

        this.productService.save(product);
//        headers.setLocation(builder.path("/api/products/{productId}").buildAndExpand(product.getId()).toUri());
        return "products";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return "error";
        }

        this.productService.save(product);
//        headers.setLocation(builder.path("/api/products/{productId}").buildAndExpand(product.getId()).toUri());
        return "products";
    }

//    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter(NAME_PARAM);
//        if (StringUtils.isBlank(name)) {
//            forwardToErrorPage(name, req, resp);
//            return;
//        }
//
//        String manufIdParam = req.getParameter(MANUF_ID_PARAM);
//        if (StringUtils.isBlank(manufIdParam)) {
//            forwardToErrorPage(manufIdParam, req, resp);
//            return;
//        }
//
//        String priceParam = req.getParameter(PRICE_PARAM);
//        if (StringUtils.isBlank(priceParam)) {
//            forwardToErrorPage(priceParam, req, resp);
//            return;
//        }
//
//        try {
//            Manufacturer manufacturer = manufacturerDAO.getById(Long.parseLong(manufIdParam));
//            Product product = new Product(name, BigDecimal.valueOf(Long.parseLong(priceParam)), manufacturer);
//            productDAO.save(product);
//            forwardToProds(req, resp);
//        } catch (Exception e) {
//            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
//            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
//        }
//    }

/*    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }*/
}
