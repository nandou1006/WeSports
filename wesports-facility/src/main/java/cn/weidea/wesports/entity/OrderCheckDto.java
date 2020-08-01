package cn.weidea.wesports.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCheckDto implements Serializable{
    private static final long serialVersionUID = 2419522607958548447L;

    private String username;

    private String health;

    private String temp;

    private String stat;
}
