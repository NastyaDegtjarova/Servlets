package ua.goit.dao;


import ua.goit.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();
    void save(Product manufacturer);
    void update(Product manufacturer);
    void delete(Product manufacturer);
    Product getById(Long id);
}
