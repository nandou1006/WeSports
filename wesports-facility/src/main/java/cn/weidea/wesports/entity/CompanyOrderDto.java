package cn.weidea.wesports.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOrderDto implements Serializable{
    private static final long serialVersionUID = 8419526607958544447L;

    private String orderId;

    private String userId;

    private Integer fieldId;

    private String name;

    private Integer companyId;

    private Integer stat;

    private Integer points;

    private BigDecimal cost;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
