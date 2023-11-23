package com.wpf.controller;

import com.wpf.pojo.Dept;
import com.wpf.pojo.Result;
import com.wpf.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    //查询部门列表
    @GetMapping
    public Result deptList() {
        List<Dept> list=deptService.list();
        log.info("部门列表查询成功");
        return Result.success(list);
    }

    //根据ID删除部门数据
    @DeleteMapping("/{id}")
    public Result selectId(@PathVariable Integer id) throws Exception {
        //log.info("已成功删除ID为"+id+"的部门");
        log.info("已成功删除ID为{}的部门",id);
        deptService.delete(id);
        return Result.success();
    }

    // 添加部门
    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("添加{}成功！",dept.getName());
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("已获取ID:{}",id);
        Dept dept = deptService.getId(id);
        return Result.success(dept);

    }


    // 修改部门
    @PutMapping
    public Result saveDept(@RequestBody Dept dept){
        log.info("ID为{}已修改成功",dept);
        deptService.save(dept);
        return Result.success();
    }
}
