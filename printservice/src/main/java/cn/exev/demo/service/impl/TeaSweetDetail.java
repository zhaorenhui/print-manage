package cn.exev.demo.service.impl;

import cn.exev.demo.constant.OrderConstants;
import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.service.PrintDetail;
import cn.hutool.core.date.DateUtil;

import java.util.List;


/**
 * 测试打印
 */
public class TeaSweetDetail implements PrintDetail {
    @Override
    public String print(TbOrderPojo tbOrderPojo) {
        TbOrder tbOrder = tbOrderPojo.getTbOrder();
        List itemList = tbOrderPojo.getTbOrderItemList();
        List PaymentList = tbOrderPojo.getTbOrderPaymentList();
        //String title = tbOrder.getType().equals(OrderConstants.ORDER_TYPE.MILK_TEA)?"奶茶":"甜品";
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>后厨联</center></FS2>\n");
        sb.append("<FS2><center>#"+tbOrder.getServiceNo()+" 美食卡</center></FS2>\n");
        sb.append("----------------------\n");
        sb.append("店内就餐       取餐号："+tbOrder.getServiceNo()+"\n");
        sb.append("----------------------\n");
        sb.append("下单时间："+ DateUtil.format(tbOrder.getPayTime(),"yyyy-MM-dd HH:mm:ss")+"\n");
        sb.append("订单编号："+tbOrder.getCustomerId()+"\n");
        sb.append("----------------------\n");
        for(Object object : itemList){
            TbOrderItem item = (TbOrderItem) object;
            Long sumPrice = (item.getBenefit()!=null&&!item.getBenefit().equals(""))? item.getOriginalPrice().longValue()*item.getQuantity():item.getPrice().longValue()*item.getQuantity();
            sb.append("<FH><FW><table><tr><td>"+item.getName()+"</td><td>x"+item.getQuantity()+"</td><td>"+sumPrice+"</td></tr></table></FW></FH>\n");
        }
        sb.append("----------------------\n");
        sb.append("消费合计："+tbOrder.getOrderTotalAmt()+"元\n");
        sb.append("实际收款：<FH><FW>"+tbOrder.getPlanSettleAmt()+"元</FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("<FS>备注：");
        if(tbOrder.getRemark() != null && !tbOrder.getRemark().equals("")){
            sb.append(tbOrder.getRemark());
        }
        sb.append("</FS>\n");
        sb.append("----------#"+tbOrder.getServiceNo()+"完-------\n");

        System.out.println(sb.toString());
        return sb.toString();
    }
}
