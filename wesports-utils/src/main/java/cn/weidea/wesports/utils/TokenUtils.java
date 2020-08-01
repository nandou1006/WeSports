package cn.weidea.wesports.utils;


import org.apache.commons.codec.digest.DigestUtils;


/**
 * token 工具类
 *
 * @author L99
 */
public class TokenUtils {


    /**
     * 令牌生成，用于标识登录状态
     * <h1>
     * 使用 MD5 对字符串进行加密
     * </h1>
     *
     * @return 生成的令牌
     */
    public static String generateToken(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strings) {
            stringBuilder.append(str);
        }
        stringBuilder.append(System.currentTimeMillis());
        return DigestUtils.md5Hex(stringBuilder.toString().getBytes());
    }

}
