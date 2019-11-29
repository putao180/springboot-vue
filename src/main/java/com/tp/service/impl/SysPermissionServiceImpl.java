package com.tp.service.impl;

import com.tp.dao.SysPermissionMapper;
import com.tp.domain.SysPermission;
import com.tp.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findPermissionByUserLoginName(String name) {

        return sysPermissionMapper.findPermissionByUserLoginName(name);
    }
}
