package cn.weidea.wesports.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Order {
    private int id;

    private String orderId;

    private int userId;

    private int fieldId;

    private int status;

    private int points;

    private BigDecimal cost;

    private long startTime;

    private long endTime;

    private long createTime;

    private long updateTime;
}
