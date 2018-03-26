package ua.goit.service;

import ua.goit.model.Role;
import ua.goit.service.impl.BaseServise;

import java.util.List;

public interface RoleService extends BaseServise<Role> {
    List<Role> getAll();

    Role getById(Long id);

    void save(Role entity);

    void delete(Long id);

    Role update(Role entity);

}
