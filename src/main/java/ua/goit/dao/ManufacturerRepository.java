package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByNameManufact (String name);
    Manufacturer getByIdManufact (Long id);
}
