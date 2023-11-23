package com.wpf.service;

import com.wpf.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查部门列表
    List<Dept> list();

    //根据部门Id删除部门
    void delete(Integer id) throws Exception;

    //添加部门
    void add(Dept dept);

    //修改部门
    void save(Dept dept);


    //获取ID
    Dept getId(Integer id);
}
