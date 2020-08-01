package cn.weidea.wesports.dto;

import lombok.Data;

@Data
public class AliyunFaceSearchResultDTO {
    //阿里云接口里的字段（人脸特征数据对应的唯一标识）
    private String faceId;

    //人脸匹配度得分
    private double score;

    //人脸身份标识
    private String entityId;

    private String extraData;

}
