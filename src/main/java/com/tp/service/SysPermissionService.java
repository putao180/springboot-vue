package com.tp.service;

import com.tp.domain.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> findPermissionByUserLoginName(String name);
}
