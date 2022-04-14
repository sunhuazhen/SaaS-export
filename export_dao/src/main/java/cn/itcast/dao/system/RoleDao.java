package cn.itcast.dao.system;

import cn.itcast.domain.system.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll(String companyId);

    void save(Role role);

    Role findById(String id);

    void update(Role role);

    void deleteById(String id);
}
