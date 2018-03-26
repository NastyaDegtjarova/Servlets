package ua.goit.service;

import ua.goit.model.Product;
import ua.goit.service.impl.BaseServise;

import java.util.List;

public interface ProductService extends BaseServise<Product> {
    List<Product> getAll();

    Product getById(Long id);

    void save(Product entity);

    void delete(Long id);

    Product update(Product entity);
}
