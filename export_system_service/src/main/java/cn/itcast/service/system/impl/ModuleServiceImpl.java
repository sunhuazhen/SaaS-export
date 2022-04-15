package cn.itcast.service.system.impl;

import cn.itcast.dao.system.ModuleDao;
import cn.itcast.domain.system.Module;
import cn.itcast.domain.system.User;
import cn.itcast.service.system.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public void save(Module module) {
        moduleDao.save(module);
    }

    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public void deleteById(String id) {
        moduleDao.deleteById(id);
    }
    @Override
    public PageInfo findPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Module> list = moduleDao.findAll();
        return new PageInfo(list,5);
    }

    @Override
    public List<String> findModulesByRoleId(String roleid) {
        return moduleDao.findModulesByRoleId(roleid);
    }

    @Override
    public List<Module> findModuleListByUser(User user) {
        if (user.getDegree() == 0){
            //saas平台管理员,只查询系统相关菜单
            return moduleDao.findByBelong(0);
        }else if (user.getDegree() == 1){
            //租户企业管理员,查询企业所有菜单
            return moduleDao.findByBelong(1);
        }else {
            //租户企业普通员工,根据权限查询菜单
            return moduleDao.findByUserId(user.getId());
        }
    }
}
