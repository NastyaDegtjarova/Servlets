package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.dao.ManufacturerRepository;
import ua.goit.model.Manufacturer;
import ua.goit.service.ManufacturerService;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer getById(Long manufId) {
        return manufacturerRepository.getByIdManufact(manufId);
    }

    @Override
    public void delete(Long manufId) {
        manufacturerRepository.delete(manufId);
    }

    @Override
    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void update(Manufacturer manufacturer) {
         manufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepository.findAll();
    }
}
