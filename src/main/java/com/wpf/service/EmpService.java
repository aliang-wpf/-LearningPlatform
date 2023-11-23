package com.wpf.service;

import com.wpf.pojo.Emp;
import com.wpf.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {
     Emp login(Emp emp) ;

    /**
     * 条件分页查询
     * @param page     页码
     * @param pageSize 每页展示记录数
     * @param name     姓名
     * @param gender   性别
     * @param begin   开始时间
     * @param end     结束时间
     * @return
     */

    //分页查询
    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除员工
    void deleteEmp(List<Integer> ids);

    //添加员工
    Emp addEmp(Emp emp);

    //员工回显
    Emp echoEmp(Integer id);
    //更新员工
    void updateEmp(Emp emp);

}
