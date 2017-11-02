package com.flymegoc.cc.controller;

import com.flymegoc.cc.model.User;
import com.flymegoc.cc.service.UserService;
import com.flymegoc.cc.utils.BaseResult;
import com.flymegoc.cc.utils.PasswordHash;
import com.flymegoc.cc.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService mUserService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    private BaseResult userRegister(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord) throws InvalidKeySpecException, NoSuchAlgorithmException {
        logger.info("begin user register");
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(passWord)) {
            User oldUser=mUserService.findByUserName(userName);
            if (oldUser!=null){
                return ResultUtils.getErrorBaseResult(432,"用户名已经存在");
            }
            String hashPassWord = PasswordHash.createHash(passWord);
            User user=new User();
            user.setUserName(userName);
            user.setUserPassword(hashPassWord);
            user.setUserAge(0);
            user.setUserAvatar("");
            user.setUserEmail("");
            user.setUserPhoneNumber(0);
            Date date = new Date();
            user.setUserCreateTime(date);
            user.setUserUpdateTime(date);

            User saveUser = mUserService.saveUser(user);
            if (saveUser!=null){
                return ResultUtils.getSuccBaseResult();
            }else {
                return ResultUtils.getErrorBaseResult(432,"注册失败了");
            }
        }
        logger.info("end user register");
        return ResultUtils.getErrorBaseResult(432,"注册信息不完整");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    private BaseResult userLogin(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
        logger.info("begin user login");
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(passWord)) {
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,passWord);
            usernamePasswordToken.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            try {
                currentUser.login(usernamePasswordToken);
                return ResultUtils.getSuccBaseResult();
            }catch (AuthenticationException e){
                return ResultUtils.getErrorBaseResult(432,"用户名或者密码错误");
            }
        }
        logger.info("end user login");
        return ResultUtils.getErrorBaseResult(432,"用户名或者密码错误");
    }

    /**
     * 用户登出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
