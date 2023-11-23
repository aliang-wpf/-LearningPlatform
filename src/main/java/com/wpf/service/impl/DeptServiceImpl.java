package com.wpf.service.impl;

import com.wpf.mapper.DeptLogMapper;
import com.wpf.mapper.DeptMapper;
import com.wpf.mapper.EmpMapper;
import com.wpf.pojo.Dept;
import com.wpf.pojo.DeptLog;
import com.wpf.service.DeptLogService;
import com.wpf.service.DeptService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;


    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    //根据部门id，删除部门信息及部门下的所有员工

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {
        try {
            //根据部门id删除部门信息
            deptMapper.delete(id);
            //模拟：异常
            /*if (true) {
                throw new Exception("出现异常了~~~");
            }*/
            //删除部门下的所有员工信息
            empMapper.deleteByDeptId(id);
        } finally {
            //不论是否有异常，最终都要执行的代码：记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是" + id + "号部门");
            //调用其他业务类中的方法
            deptLogService.insert(deptLog);
        }
    }


    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);

    }

    @Override
    public void save(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.save(dept);

    }

    @Override
    public Dept getId(Integer id) {
        Dept dept = deptMapper.getId(id);

        return dept;
    }


}
