package cn.vagile.swagger.demo.other;

import io.swagger.annotations.ApiModel;

/**
 * 结果实体类
 * <p>使用示例:
 * <pre>
 * //快捷方法 返回status=1
 * ResultEntity<User> result = ResultEntity.ok();
 * //快捷方法 返回status=1 和 result={user}
 * ResultEntity<User> result = ResultEntity.ok(user);
 * //快捷方法 返回 status=0 和失败信息
 * ResultEntity<User> result = ResultEntity.bad("xxx");
 * //详细用法，注意:构造器以status()函数开头，以result()或者build()函数结尾;
 * ResultEntity<User> result = ResultEntity.status(ResultEntity.OK).xdata("some extend data").result(user);
 * ResultEntity<User> result = ResultEntity.status(ResultEntity.BAD).message("some message").xdata("some extend data").build();
 * @author walden
 * @param <T>
 */
@ApiModel("结果实体对象")
public class ResultEntity<T> {
    public static final int OK = 1;
    public static final int BAD = 0;
    private int status;
    private String message;
    private T result;
    private Object xdata;

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

    public ResultEntity(int status, String message, T result, Object xdata) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.xdata = xdata;
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

    public Object getXdata() {
        return xdata;
    }

    public void setXdata(Object xdata) {
        this.xdata = xdata;
    }


    public interface Builder {

        Builder message(String message);

        Builder xdata(Object xdata);

        <T> ResultEntity<T> build();

        <T> ResultEntity<T> result(T result);
    }

    private static class ResultBuilder implements Builder {

        private final int status;

        private String message;

        private Object xdata;

        public ResultBuilder(int status) {
            this.status = status;
        }

        @Override
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder xdata(Object xdata) {
            this.xdata = xdata;
            return this;
        }

        @Override
        public <T> ResultEntity<T> build() {
            return result(null);
        }

        @Override
        public <T> ResultEntity<T> result(T result) {
            return new ResultEntity<>(status, message, result, xdata);
        }
    }

    public static Builder status(int status) {
        return new ResultBuilder(status);
    }


    public static <T> ResultEntity<T> ok() {
        return status(ResultEntity.OK).build();
    }

    public static <T> ResultEntity<T> ok(T result) {
        return status(ResultEntity.OK).result(result);
    }

    public static <T> ResultEntity<T> bad(String message) {
        return status(ResultEntity.BAD).message(message).build();
    }
}
