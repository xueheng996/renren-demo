package com.xiaoxue.modules.sys.shiro;

import com.xiaoxue.common.utils.Contant;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserDao sysMenuDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUserEntity userEntity= (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId=userEntity.getUserId();
        if(userId== Contant.SUPER_ADMIN){
           // List<SysMenuEntity> menuList=sysMenuDao.selectList(null);

        }
        return null;
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
        if(user.getStatus()==0){
            throw new LockedAccountException("账户已被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
        return null;
    }
}
