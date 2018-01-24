package net.proselyte.dao;


import net.proselyte.model.Manufacturer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 20.11.2017.
 */
public interface ManufacturerDAO {
    List<Manufacturer> getAll();
    void save(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
     Manufacturer getById(Long id);
}
