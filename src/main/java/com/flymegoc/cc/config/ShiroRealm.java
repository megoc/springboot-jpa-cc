package com.flymegoc.cc.config;

import com.flymegoc.cc.model.User;
import com.flymegoc.cc.model.UserRole;
import com.flymegoc.cc.service.UserRoleService;
import com.flymegoc.cc.service.UserService;
import com.flymegoc.cc.utils.PasswordHash;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**登陆认证和权限认证
 * Created by flymegoc on 2017/4/21.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //LoginUser user = (LoginUser) SecurityUtils.getSubject().getSession().getAttribute(AuthAppConfig.LOGIN_USER);
        String currentLoginName = (String) principalCollection.getPrimaryPrincipal();

        User user = userService.findByUserName(currentLoginName);

        if (user != null) {
            // 当前用户角色编码集合
            //获取当前用户下所有ACL权限列表 待续。。。
            //获取当前用户下拥有的所有角色列表

            List<UserRole> roles = userRoleService.findByUser(user);

            List<String> roleIds = new ArrayList<>();
            for (UserRole role : roles) {
                roleIds.add(String.valueOf(role.getId()));
            }
            authorizationInfo.addRoles(roleIds);
            //TODO add permits
            //authorizationInfo.addStringPermissions(null);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (StringUtils.isEmpty(token.getUsername())) {
            throw new IncorrectCredentialsException("username is null!");
        } else if (StringUtils.isEmpty(token.getCredentials())) {
            throw new IncorrectCredentialsException("password is null!");
        }

        User user = userService.findByUserName(token.getUsername());

        try {
            if (user != null && PasswordHash.validatePassword(token.getPassword(), user.getUserPassword())) {
                return new SimpleAuthenticationInfo(user.getUserName(), token.getPassword(), getName());
            } else {
                throw new AuthenticationException();
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }
}
