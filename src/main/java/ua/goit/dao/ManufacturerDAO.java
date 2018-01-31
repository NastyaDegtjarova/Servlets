package ua.goit.dao;


import ua.goit.model.Manufacturer;

import java.util.List;

public interface ManufacturerDAO {
    List<Manufacturer> getAll();
    void save(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void delete(Manufacturer manufacturer);
     Manufacturer getById(Long id);
}
