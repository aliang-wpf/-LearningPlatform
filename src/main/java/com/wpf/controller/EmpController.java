package com.wpf.controller;

import com.wpf.pojo.Emp;
import com.wpf.pojo.PageBean;
import com.wpf.pojo.Result;
import com.wpf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    //员工列表查询
    @GetMapping()
    public Result selectList(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                              String name,
                              Short gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        PageBean pageBean = empService.page(page, pageSize,name,gender,begin,end);
        log.info("分页查询员工列表成功 属性:页面 {},每页个数 {},关键字 {},性别 {},时间 {},   {}",page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    //删除员工
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable List<Integer> ids){
        log.info("要删除的员工ID:{} 姓名：",ids);
        empService.deleteEmp(ids);
        log.info("已删除！");
        return Result.success();
    }

    //添加员工
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){

        log.info("员工{},{},{},{},{},{},{}已创建",emp.getUsername(),emp.getName(),emp.getGender(),emp.getImage(),emp.getDeptId(),emp.getEntrydate(),emp.getJob());
        empService.addEmp(emp);
        return Result.success();
    }

    //员工数据回显
    @GetMapping("/{id}")
    public  Result echoEmp(@PathVariable Integer id){
        Emp emp = empService.echoEmp(id);
        log.info("回显{}数据成功",id,emp.getName());
        return Result.success(emp);
    }

    //员工信息修改
    @PutMapping
    public Result uodateEmp(@RequestBody Emp emp){
        log.info("要修改的参数：{}",emp);
        empService.updateEmp(emp);
        return Result.success();
    }
}
