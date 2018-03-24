package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.model.Product;
import ua.goit.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

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

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllProducts(ModelMap model) {
        List<Product> productList = this.productService.getAll();

        if (productList.isEmpty()) {
            return "error";
        }
        model.put("prods", productList);
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
        return "products";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return "error";
        }

        this.productService.save(product);
        return "products";
    }

}
