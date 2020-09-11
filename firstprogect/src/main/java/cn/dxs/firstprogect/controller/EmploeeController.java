package cn.dxs.firstprogect.controller;

import cn.dxs.firstprogect.dao.DepartmentDao;
import cn.dxs.firstprogect.dao.EmployeeDao;
import cn.dxs.firstprogect.entities.Department;
import cn.dxs.firstprogect.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmploeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        //将上面取到的值放到那个model容器里面；
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //跳转到添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //在这里将所有的部门号查出来；
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //进行添加
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //模拟存储到数据库；
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //进行编辑
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        //将上面取到的值放到那个model容器里面；
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    /*其他的方法界面根本就无法进入方法，应该是前端写的有问题
    一直提示的是方法类型不符，所以我又换成了post方法；
    然后就不报错了；所以想要不错试试Post接收；
     */
    @PostMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id,Model model){
        employeeDao.delete(id);
        //将没删除的显示
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "emp/list";
    }
}
