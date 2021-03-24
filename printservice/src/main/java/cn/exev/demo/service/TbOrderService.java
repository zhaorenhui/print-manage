package cn.exev.demo.service;


import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPayment;

import java.util.Date;
import java.util.List;

public interface TbOrderService {

    TbOrder selectById(String orderType);

    public List<TbOrderItem> selectItemById();

    TbOrderPayment selectPaymentById();

    String getDateString(Date time);
}
