package com.wpf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wpf.mapper.EmpMapper;
import com.wpf.pojo.Emp;
import com.wpf.pojo.PageBean;
import com.wpf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;



    /* @Override
            public PageBean page(Integer page, Integer pageSize) {
                //1.获取总记录数
                long count = empMapper.count();
                //2、获取分页查询结果列表
                Integer start = (page - 1) * pageSize;//计算起始索引 , 公式: (页码-1)*页大小
                List<Emp> empList = empMapper.page(start, pageSize);
                //3、封装PageBean对象
                PageBean pageBean = new PageBean(count, empList);
                return pageBean;

            }*/

    @Override
    public Emp login(Emp emp) {
        Emp e = empMapper.login(emp);
        return e;
    }

    //分页查询
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2、执行查询
        List<Emp> empList = empMapper.selectEmp(name,gender,begin,end);
        Page<Emp> p =(Page<Emp>) empList;
        //3、封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void deleteEmp(List<Integer> ids) {
        empMapper.empDelete(ids);
    }

    //添加员工
    @Override
    public Emp addEmp(Emp emp) {

        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
        return emp;
    }

    //回显
    @Override
    public Emp echoEmp(Integer id) {
        Emp emp = empMapper.empEcho(id);
        return emp;
    }

    //更新员工
    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

    }





}
