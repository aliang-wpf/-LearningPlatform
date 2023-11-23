package com.wpf.service.impl;

import com.wpf.mapper.DeptLogMapper;
import com.wpf.pojo.DeptLog;
import com.wpf.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    //(propagation = Propagation.REQUIRES_NEW)
    // 事务传播行为：有事务就加入、没有事务就新建事务
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
