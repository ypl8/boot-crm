package cn.kfqjtdqb.core.bean;

import java.io.Serializable;

public class ResultCode  implements Serializable {
    /**
     * 0成功，其他失败
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public ResultCode() {
    }
    /**
     * 这个方法别动 ！！！
     *
     * @return true false
     */
    public boolean isSuccess() {
        return this.code == 0;
    }

  /*  public ResultCode(StatusCode statusCode, Object o) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = o;
    }*/

    /**
     * 单独处理e 签宝异常的返回，慎用
     * @param o
     */
    public ResultCode( Object o) {
        this.code = 80000;
        this.msg =o.toString();
        this.data = o;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}
