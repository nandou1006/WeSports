package cn.weidea.wesports.enums;

/**
 * 错误码枚举定义
 *
 * @author L99
 */
public enum ErrorCode {

    SUCCESS(200, "成功");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String desc;

    ErrorCode(Integer code, String desc) {
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
