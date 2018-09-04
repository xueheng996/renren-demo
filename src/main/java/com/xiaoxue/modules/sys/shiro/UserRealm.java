package com.xiaoxue.modules.sys.shiro;

import com.xiaoxue.common.utils.Constant;
import com.xiaoxue.modules.sys.dao.SysMenuDao;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRealm extends AuthorizingRealm {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUserEntity userEntity= (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId=userEntity.getUserId();

        List<String> permsList;

        if(userId== Constant.SUPER_ADMIN){
            List<SysMenuEntity> menuList=sysMenuDao.selectList(null);
            permsList=new ArrayList<>(menuList.size());
            for (SysMenuEntity menu:menuList){
                permsList.add(menu.getPerms());
            }
        }else {
            permsList=sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String > permsSet=new HashSet<>();
        for (String perms:permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;

        //查询用户信息
        SysUserEntity user=new SysUserEntity();
        user.setUsername(usernamePasswordToken.getUsername());
        user=sysUserDao.selectOne(user);
        if(user==null){
            throw new UnknownAccountException("账户或密码不正确");
        }
        logger.info(user.toString());
        if(user.getStatus()==0){
            throw new LockedAccountException("账户已被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
