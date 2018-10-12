package cn.vagile.swagger.demo.other;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 结果实体类
 * <p>使用示例:
 * <pre>
 * //快捷方法 返回status=1
 * ResultEntity<User> result = ResultEntity.ok().build();
 * //快捷方法 返回status=0
 * ResultEntity<User> result = ResultEntity.bad().build();
 * //快捷方法 返回status=1 和 result={user}
 * ResultEntity<User> result = ResultEntity.ok(user);
 * //快捷方法 返回 status=0 和失败信息
 * ResultEntity<User> result = ResultEntity.bad("xxx");
 * //详细用法，注意:构造器以status()函数开头,以result()或者build()函数结尾，没有设置result或报错信息都以build()结尾
 * ResultEntity<User> result = ResultEntity.ok().spare("some extend data").result(user);
 * ResultEntity<User> result = ResultEntity.bad().message("xxxxx").spare("some extend data").result(user);
 * ResultEntity<User> result = ResultEntity.status(ResultEntity.OK).message("some message").spare("some extend data").build();
 * @author walden
 * @param <T>
 */
@ApiModel(value = "ResultEntity", description = "结果实体类")
public class ResultEntity<T> {
    public static final int OK = 1;
    public static final int BAD = 0;
    @ApiModelProperty(value = "结果状态: 0-失败 1-成功", position = 1)
    private int status;
    @ApiModelProperty(value = "提示信息", position = 2)
    private String message;
    @ApiModelProperty(value = "结果数据", position = 3)
    private T result;
    @ApiModelProperty(value = "备用字段", position = 4)
    private Object spare;

    public ResultEntity() {
    }

    public ResultEntity(int status) {
        this.status = status;
    }

    public ResultEntity(int status, T result) {
        this.status = status;
        this.result = result;
    }

    public ResultEntity(int status, T result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public ResultEntity(int status, String message, T result, Object spare) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.spare = spare;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Object getSpare() {
        return spare;
    }

    public void setSpare(Object spare) {
        this.spare = spare;
    }

    public interface Builder {

        Builder message(String message);

        Builder spare(Object spare);

        <T> ResultEntity<T> build();

        <T> ResultEntity<T> result(T result);
    }

    private static class ResultBuilder implements Builder {

        private final int status;

        private String message;

        private Object spare;

        public ResultBuilder(int status) {
            this.status = status;
        }

        @Override
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder spare(Object spare) {
            this.spare = spare;
            return this;
        }

        @Override
        public <T> ResultEntity<T> build() {
            return result(null);
        }

        @Override
        public <T> ResultEntity<T> result(T result) {
            return new ResultEntity<>(status, message, result, spare);
        }
    }

    public static Builder status(int status) {
        return new ResultBuilder(status);
    }


    public static Builder ok() {
        return status(ResultEntity.OK);
    }

    public static Builder bad() {
        return status(ResultEntity.BAD);
    }

    public static <T> ResultEntity<T> ok(T result) {
        return status(ResultEntity.OK).result(result);
    }

    public static <T> ResultEntity<T> bad(String message) {
        return status(ResultEntity.BAD).message(message).build();
    }
}
