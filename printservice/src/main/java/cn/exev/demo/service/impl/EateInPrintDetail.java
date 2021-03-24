package cn.exev.demo.service.impl;



import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPayment;
import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.service.PrintDetail;
import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.util.List;

public class EateInPrintDetail implements PrintDetail {

    @Override
    public String print(TbOrderPojo tbOrderPojo) {
        TbOrder order = tbOrderPojo.getTbOrder();
        List<TbOrderItem> itemList = tbOrderPojo.getTbOrderItemList();
        List<TbOrderPayment> paymentList = tbOrderPojo.getTbOrderPaymentList();
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>后厨联</center></FS2>\n");
        sb.append("<FS2><center>#"+order.getServiceNo()+" 美食卡</center></FS2>\n");
        sb.append("----------------------\n");
        sb.append("店内就餐  人数："+order.getDinnerNumber()+"  桌号："+order.getSeatNumber()+"\n\n");
        sb.append("----------------------\n");
        sb.append("下单时间："+ DateUtil.format(order.getPayTime(),"yyyy-MM-dd HH:mm:ss")+"\n\n");
        sb.append("订单编号："+order.getCustomerId()+"\n");
        sb.append("----------------------\n");
        sb.append("<FH><FW><table>");
        for(int i = 0 ; i < itemList.size() ; i++){
            TbOrderItem orderItem = (TbOrderItem) itemList.get(i);
            BigDecimal price = orderItem.getPrice();
            if(orderItem.getBenefit() != null && orderItem.getBenefit().isEmpty()){
                price = orderItem.getOriginalPrice();
            }
            BigDecimal quantity = new BigDecimal(orderItem.getQuantity());
            sb.append("<tr><td>"+orderItem.getName()+"</td><td>x"+orderItem.getQuantity()+"</td><td>"+(price.multiply(quantity))+"</td></tr><tr><td></td><td></td><td></td></tr>");
        }
        sb.append("</table></FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("消费合计："+order.getOrderTotalAmt()+"元\n\n");
        sb.append("实际支付：<FH><FW>"+order.getOrderUserPay()+"元</FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("<FS>备注："+order.getRemark()+"</FS>\n");
        sb.append("----------------------\n");
        sb.append("----------#"+order.getServiceNo()+"完-------\n");

        System.out.println(sb.toString());
        return sb.toString();
    }
}
