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

    // 人脸搜索失败
    FACENOTEXIST(500, "人脸验证失败，用户不存在"),

    // 人脸图像上传失败
    IMGUPLOADFAIL(501,"人脸上传失败");

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
