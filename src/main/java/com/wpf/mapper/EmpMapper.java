package com.wpf.mapper;

import com.wpf.pojo.Emp;
import com.wpf.pojo.PageBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface EmpMapper {
    List<Emp> selectEmp(String name, Short gender, LocalDate begin, LocalDate end);

    //员工列表查询
//    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from emp")
    
    /*// 获取总记录数
    @Select("select count(*) from emp")
    long count();

    //获取当前页的结果列表
    @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> page(Integer start,Integer pageSize);*/

    //批量删除员工
    void empDelete(List<Integer> ids);

    //添加员工
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) VALUES (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);
        //编辑回显员工信息
    @Select("select * from emp where id =#{id}")
    public Emp empEcho(Integer id);

    //更新员工信息

     void update(Emp emp);

     //登录
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);


    @Delete("delete from emp where dept_id=#{deptID}")
    public int deleteByDeptId(Integer deptId);
}
