package cn.exev.demo.service.impl;



import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPayment;
import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.service.PrintDetail;
import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * 外带
 */
public class TakeAwayPrintDetail implements PrintDetail {
    @Override
    public String print(TbOrderPojo tbOrderPojo) {
        TbOrder tbOrder = tbOrderPojo.getTbOrder();
        List<TbOrderItem> itemList = tbOrderPojo.getTbOrderItemList();
        List<TbOrderPayment> paymentList = tbOrderPojo.getTbOrderPaymentList();
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>后厨联</center></FS2>\n");
        sb.append("<FS2><center>#"+tbOrder.getServiceNo()+" 美食卡</center></FS2>\n");
        sb.append("----------------------\n");
        sb.append("打包外带   人数："+tbOrder.getDinnerNumber()+"    桌号："+tbOrder.getSeatNumber()+"\n\n");
        sb.append("----------------------\n");
        sb.append("下单时间："+ DateUtil.format(tbOrder.getPayTime(),"yyyy-MM-dd HH:mm:ss")+"\n\n");
        sb.append("订单编号："+tbOrder.getCustomerId()+"\n");
        sb.append("----------------------\n");
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
        sb.append("消费合计："+tbOrder.getOrderTotalAmt()+"元\n\n");
        sb.append("实际支付：<FH><FW>"+tbOrder.getOrderUserPay()+"元</FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("<FS>备注："+tbOrder.getRemark()+"/FS>\n");
        sb.append("----------#"+tbOrder.getServiceNo()+"完-------\n");

        System.out.println(sb.toString());
        return sb.toString();
    }
}
