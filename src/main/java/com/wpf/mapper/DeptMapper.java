package com.wpf.mapper;

import com.wpf.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询所有部门数据
    @Select("select id, name, create_time, update_time from dept")
    List<Dept> list();


    //根据ID删除部门数据
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    //添加部门
    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);


    //修改部门
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void save(Dept dept);

    //获取id
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getId(Integer id);
}
