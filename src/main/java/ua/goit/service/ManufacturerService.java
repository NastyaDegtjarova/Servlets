package ua.goit.service;

import ua.goit.model.Manufacturer;
import ua.goit.service.impl.BaseServise;

import java.util.List;

public interface ManufacturerService extends BaseServise<Manufacturer>{
    Manufacturer getById(Long manufId);

    void delete(Long manufId);

    void save(Manufacturer manufacturer);

    Manufacturer update(Manufacturer manufacturer);

    List<Manufacturer> getAll();
}
