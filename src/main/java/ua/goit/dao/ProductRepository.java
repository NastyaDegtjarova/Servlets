package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByNameProduct(String name);
    Product findByIdProduct(Long id);
}
