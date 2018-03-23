package ua.goit.service;

import ua.goit.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer getById(Long manufId);

    void delete(Long manufId);

    void save(Manufacturer manufacturer);

    void update(Manufacturer manufacturer);

    List<Manufacturer> getAll();
}
