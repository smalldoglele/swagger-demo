package cn.vagile.swagger.demo.controller;

import cn.vagile.swagger.demo.other.ResultEntity;
import cn.vagile.swagger.demo.other.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value="宠物接口")
@RestController("user")
public class PetController {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ApiOperation("根据id获得用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "query")
    public ResultEntity<User> get(String id) {
        User user = new User("王利强", 30, "濮阳");
        return ResultEntity.ok().spare("这里是扩展信息").result(user);
    }
}
