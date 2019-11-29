package com.tp.dao;

import com.tp.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Mapper
public interface SysPermissionMapper {
    List<SysPermission> findPermissionByUserLoginName(String name);
}
