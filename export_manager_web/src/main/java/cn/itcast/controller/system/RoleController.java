package cn.itcast.controller.system;

import cn.itcast.controller.BaseController;
import cn.itcast.domain.system.Dept;
import cn.itcast.domain.system.Role;
import cn.itcast.service.system.DeptService;
import cn.itcast.service.system.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/list" ,name = "展示角色列表数据")
    public String findAll(@RequestParam(name="page" ,defaultValue = "1") int pageNum, @RequestParam(name="pageSize" ,defaultValue = "10")int pageSize){
        PageInfo page = roleService.findPage(getCompanyId(),pageNum,pageSize);
        request.setAttribute("page",page);
        return "system/role/role-list";
    }

    @RequestMapping(value = "/toAdd" ,name = "进入新增角色页面")
    public String toAdd(){
        return "system/role/role-add";
    }


    @RequestMapping(value = "/edit" ,name = "保存角色数据")
    public String edit(Role role){
//        判断新增还是修改的依据是：role中是否有id值
        if(StringUtils.isEmpty(role.getId())){
//            新增 insert
            role.setId(UUID.randomUUID().toString()); //设置角色id
            role.setCompanyId(getCompanyId());
            role.setCompanyName(getCompanyName());
            role.setCreateTime(new Date());
            roleService.save(role);
        }else{
//            修改 update
            role.setUpdateTime(new Date());
            roleService.update(role);
        }
//        重定向到列表页面
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "/toUpdate" ,name = "进入修改角色页面")
    public String toUpdate(String id){
        Role role = roleService.findById(id);
        request.setAttribute("role",role);
        return "system/role/role-add";
    }


    @RequestMapping(value = "/delete" ,name = "删除角色数据")
    public String delete(String id){
        roleService.deleteById(id);
//        重定向到列表页面
        return "redirect:/system/role/list.do";
    }
}
