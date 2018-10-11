package cn.vagile.swagger.demo.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("响应数据对象")
public class ResponseResult<T> {
    @ApiModelProperty(value = "返回码：正确0, 警告2，错误为自定义码")
    private int responseCode;
    @ApiModelProperty(value = "返回消息")
    private String responseMsg;
    @ApiModelProperty(value = "返回具体内容")
    private T result;

    public ResponseResult(int responseCode, String responseMsg, T result) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.result = result;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getResult() {
        return result;
    }
}
