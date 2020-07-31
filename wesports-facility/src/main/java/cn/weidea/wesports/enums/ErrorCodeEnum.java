package cn.weidea.wesports.enums;

/**
 * 错误码枚举定义
 *
 * @author L99
 */
public enum ErrorCodeEnum {

    SUCCESS(200, "成功"),

    // 用户名与密码不匹配
    MISMATCH(400, "账户名与密码不匹配，请重新输入"),

    NODATA(1000, "数据库查询结果为空，请确认查询参数");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
