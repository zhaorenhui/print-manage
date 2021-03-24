package cn.exev.demo.service.impl;


import cn.exev.demo.constant.OrderConstants;
import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPayment;
import cn.exev.demo.service.TbOrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbOrderServiceImpl implements TbOrderService {
    public TbOrder selectById(String orderType) {
        TbOrder tbOrder = new TbOrder();
        tbOrder.setType(OrderConstants.ORDER_TYPE.EATE_IN);
        tbOrder.setDinnerNumber(2);
        tbOrder.setSeatNumber("999");
        tbOrder.setPlanConsumeTime(getDate("2021-3-12 12:50:00"));
        tbOrder.setCreateTime(getDate("2021-3-12 10:20:00"));
        tbOrder.setRemark("多放辣椒，不放葱花和香菜");
        tbOrder.setCustomerId("123456789000123456");
        tbOrder.setOrderMobile("15236253288");
        tbOrder.setOrderTotalAmt(new BigDecimal(57));
        return tbOrder;
    }

    public List<TbOrderItem> selectItemById(){
        List<TbOrderItem> list = new ArrayList<TbOrderItem>();
        for(int i = 0 ; i < 3 ; i++){
            TbOrderItem orderItem = new TbOrderItem();
            if(i == 0){
                orderItem.setName("南瓜松糕");
            }else if(i == 1){
                orderItem.setName("奶香大馒头");
            }else if(i == 2){
                orderItem.setName("小份地锅鸡（微辣 娃娃菜）");
            }
            orderItem.setQuantity(1);
            orderItem.setPrice(new BigDecimal(19));
            list.add(orderItem);
        }

        return list;
    }

    public TbOrderPayment selectPaymentById() {
        TbOrderPayment orderPayment = new TbOrderPayment();
        orderPayment.setAmt(new BigDecimal(25));
        return orderPayment;
    }

    public Date getDate(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            return df.parse(time);
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }

    }

    public String getDateString(Date time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            return df.format(time);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
