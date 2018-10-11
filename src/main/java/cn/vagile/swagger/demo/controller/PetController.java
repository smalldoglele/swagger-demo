package cn.vagile.swagger.demo.controller;

import cn.vagile.swagger.demo.other.ResultEntity;
import cn.vagile.swagger.demo.other.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
public class PetController {
    @RequestMapping(value="get",method = RequestMethod.GET)
    @ApiOperation("根据id获得用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "query")
    public ResultEntity<User> get(String id) {
        User user = new User("王利强", 30, "濮阳");

        ResultEntity<User> result1 = ResultEntity.ok();
        ResultEntity<User> result2 = ResultEntity.ok(user);
        ResultEntity<User> result3 = ResultEntity.bad("xxx");
        ResultEntity<User> result4 = ResultEntity.status(ResultEntity.OK).xdata("some extend data").result(user);
        ResultEntity<User> result5 = ResultEntity.status(ResultEntity.BAD).message("xx").xdata("some extend data").build();
        return result1;
    }
}
