package net.proselyte.dao;


import net.proselyte.model.Product;

import java.util.List;

/**
 * Created by Nastya on 21.11.2017.
 */
public interface ProductDAO {
    List<Product> getAll();
    void save(Product manufacturer);
    void update(Product manufacturer);
    void delete(Product manufacturer);
    Product getById(Long id);
}
