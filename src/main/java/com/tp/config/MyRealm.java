package com.tp.config;

import com.tp.domain.SysPermission;
import com.tp.domain.User;
import com.tp.service.SysPermissionService;
import com.tp.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService us;
    @Autowired
    private SysPermissionService sysPermissionService;
    //权限的
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String primaryPrincipal = (String)principals.getPrimaryPrincipal();
        List<SysPermission> userPermissionByUserName = sysPermissionService.findPermissionByUserLoginName(primaryPrincipal);
        if (userPermissionByUserName!=null&&userPermissionByUserName.size()>0){
            //去重
            Collection list = new HashSet<>();
            for (SysPermission per :userPermissionByUserName
                    ) {
                list.add(per.getPermName());
            }

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermissions(list);
            return simpleAuthorizationInfo;
        }
        return null;
    }
    //登陆的
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws org.apache.shiro.authc.AuthenticationException {
        //获取到当前登录的用户名
        String name = (String)token.getPrincipal();
        User user = us.findByName(name);
        if (user!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPass(), getName());
            return simpleAuthenticationInfo;
        }

        return null;
    }
}