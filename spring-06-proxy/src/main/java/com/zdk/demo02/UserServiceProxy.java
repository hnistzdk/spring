package com.zdk.demo02;

/**
 * @author zdk
 * @date 2021/4/7 17:21
 */
public class UserServiceProxy {
    private UserServiceImpl userService;
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    public void add() {
        log("add");
        userService.add();
    }
    public void delete() {
        log("delete");
        userService.delete();
    }
    public void modify() {
        log("modify");
        userService.modify();
    }
    public void query() {
        log("query");
        userService.query();
    }

    //打印日志

    public void log(String msg){
        System.out.println("[Debug]使用了"+msg+"方法");
    }
}
