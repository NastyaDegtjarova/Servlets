package ua.goit.service;

import ua.goit.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(Long id);

    Product save(Product entity);

    void delete(Long id);

    Product update(Product entity);
}
