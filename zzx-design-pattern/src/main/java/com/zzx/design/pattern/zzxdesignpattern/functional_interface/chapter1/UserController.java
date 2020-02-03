//package com.zzx.design.pattern.zzxdesignpattern.functional_interface.chapter1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * @author zhouzhixiang
// * @company 叮当快药科技集团有限公司
// * @Date 2019-10-31
// */
//public class UserController extends BaseController {
//
//    @Autowired
//    private IUserService userService;
//
//    @RequestMapping("user")
//    public void getUserInfo(String userId) {
//        this.execute(() -> userService.getUserInfoByUserId(userId));
//    }
//}
