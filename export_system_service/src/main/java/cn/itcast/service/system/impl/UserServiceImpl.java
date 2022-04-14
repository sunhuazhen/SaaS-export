package cn.itcast.service.system.impl;

import cn.itcast.dao.system.UserDao;
import cn.itcast.domain.system.User;
import cn.itcast.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public void save(User user) {
        String password = user.getPassword(); //明文的
//        md5高级加密方式用到3个参数  p1:原密码  p2：加的盐   p3:加几次
        password = new Md5Hash(password,user.getEmail(),2).toString();
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteById(id);
    }



    public PageInfo findPage(String companyId,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.findAll(companyId); //表面上是查询所有，但是在执行select查询之前会根据分页拦截器把sql语句做了处理，
//        1、分页带上了limit关键字 2、还执行了count
        return new PageInfo(list,5);
    }


    //TODO 2019-11-18 立下flag  5天后去网站 https://www.cmd5.com  查询此密码b9624cad1a4c522e9d458f42c6c6183f



    @Override
    public void changeRole(String userid, String[] roleIds) {
//        删除之前用户的角色数据 delete from pe_role_user where user_id=?
        userDao.deleteRoleAndUserByUserId(userid);
//        向中间表中插入数据
        for (String roleid : roleIds) {
            userDao.saveUserAndRole(userid,roleid);
        }
    }

    @Override
    public List<String> findRoleIdsByUserId(String userid) {
        return userDao.findRoleIdsByUserId(userid);
    }


}
