package cn.exev.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TbMerchantPrinter {
    private String id;
    private String merchantId;
    private String brand;
    private String model;
    private String sn;
    private String name;
    private String platformName;
    private String key;
    private String status;
    private Date createTime;
    private Date updateTime;
}
