package com.tiger.user.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.tiger.common.utils.IpUtil;
import com.tiger.common.utils.ResponseUtil;
import com.tiger.user.pojo.LitemallUser;
import com.tiger.user.service.UserService;
import com.tiger.user.token.UserToken;
import com.tiger.user.token.UserTokenManager;
import com.tiger.user.vo.UserInfo;
import com.tiger.user.vo.WxLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanxinglong
 * @create 2018-12-29 22:42
 **/
@RestController
@RequestMapping("wx")
public class UserController {

    @Autowired
    private WxMaService wxService;

    @Autowired
    private UserService userService;

    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @param request     请求对象
     * @return 登录结果
     * TODO 这里可以改成restful风格接口，token做缓存处理
     */
    @PostMapping("user")
    public Object loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        //@RequestBody 会自动接收前端json对象参数并复制到对象中
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        //如果为空说明传参错误
        if (code == null || userInfo == null) {
            return ResponseUtil.badArgument();
        }
        String sessionKey = null;
        String openId = null;
        try {
            //根据code获取openId和sessionKey
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果sessionKey和openId为空，则说明获取失败
        if (sessionKey == null || openId == null) {
            return ResponseUtil.fail();
        }
        //根据openId查询出对应的用户
        LitemallUser user = userService.queryByOid(openId);
        //如果用户为空，则说明数据库中不存在，则插入数据
        if (user == null) {
            user = new LitemallUser();
            user.setUsername(openId);
            user.setPassword(openId);
            user.setWeixinOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setGender(userInfo.getGender());
            user.setUserLevel((byte) 0);
            user.setStatus((byte) 0);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.client(request));
            userService.add(user);
            // 新用户发送注册优惠券
            //couponAssignService.assignForRegister(user.getId());
        } else {
            //说明用户已经存在
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.client(request));
            //如果修改方法返回值为0，则说明修改失败
            if (userService.updateById(user) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());
        userToken.setSessionKey(sessionKey);
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }
}
