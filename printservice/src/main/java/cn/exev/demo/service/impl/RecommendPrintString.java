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
 * 推荐购-特惠购
 */
public class RecommendPrintString implements PrintDetail {
    @Override
    public String print(TbOrderPojo tbOrderPojo) {
        TbOrder tbOrder = tbOrderPojo.getTbOrder();
        List<TbOrderItem> itemList = tbOrderPojo.getTbOrderItemList();
        List<TbOrderPayment> PaymentList = tbOrderPojo.getTbOrderPaymentList();
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>后厨联</center></FS2>\n");
        sb.append("<FS2><center>#"+tbOrder.getServiceNo()+" 美食卡</center></FS2>\n");
        sb.append("----------------------\n");
        sb.append("店内消费            取餐号："+tbOrder.getServiceNo()+"\n");
        sb.append("----------------------\n");
        sb.append("下单时间："+DateUtil.format(tbOrder.getPayTime(),"yyyy-MM-dd HH:mm:ss")+"\n");
        sb.append("订单编号："+tbOrder.getCustomerId()+"\n");
        sb.append("----------------------\n");
        for(int i = 0 ; i < itemList.size() ; i++){
            TbOrderItem orderItem = (TbOrderItem) itemList.get(i);
            BigDecimal price = orderItem.getPrice();
            if(orderItem.getBenefit() != null && orderItem.getBenefit().isEmpty()){
                price = orderItem.getOriginalPrice();
            }
            BigDecimal quantity = new BigDecimal(orderItem.getQuantity());
            sb.append("<FH><FW><table><tr><td>"+orderItem.getName()+"</td><td>x"+orderItem.getQuantity()+"</td><td>"+(price.multiply(quantity))+"</td></tr></table></FW></FH>\n");
        }
        sb.append("----------------------\n");
        sb.append("消费合计："+tbOrder.getOrderTotalAmt()+"元\n");
        sb.append("实际支付：<FH><FW>"+tbOrder.getOrderUserPay()+"元</FW></FH>\n");
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
