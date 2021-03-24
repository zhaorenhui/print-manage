package cn.exev.demo.service.impl;

import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.service.PrintDetail;
import cn.hutool.core.date.DateUtil;

import java.util.List;


/**
 * 测试打印
 */
public class TestPrintDetail implements PrintDetail {
    @Override
    public String print(TbOrderPojo tbOrderPojo) {
        TbOrder tbOrder = tbOrderPojo.getTbOrder();
        List itemList = tbOrderPojo.getTbOrderItemList();
        List PaymentList = tbOrderPojo.getTbOrderPaymentList();
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>测试打印</center></FS2>\n");
        sb.append("<FS2><center>#"+tbOrder.getServiceNo()+" 美食卡</center></FS2>\n");
        sb.append("点菜清单\n\n");
        sb.append("----------------------\n");
        sb.append("堂食   人数："+tbOrder.getDinnerNumber()+"    桌号："+tbOrder.getSeatNumber()+"\n\n");
        sb.append("----------------------\n");
        sb.append("下单时间："+ DateUtil.format(tbOrder.getPayTime(),"yyyy-MM-dd HH:mm:ss")+"\n\n");
        sb.append("订单编号："+tbOrder.getCustomerId()+"\n");
        sb.append("----------------------\n");
        sb.append("<FH><FW>【菜品明细】</FW></FH>\n");
        for(Object object : itemList){
            TbOrderItem item = (TbOrderItem) object;
            Long sumPrice = (item.getBenefit()!=null&&!item.getBenefit().equals(""))? item.getOriginalPrice().longValue()*item.getQuantity():item.getPrice().longValue()*item.getQuantity();
            sb.append("<FH><FW><table><tr><td>"+item.getName()+"</td><td>x"+item.getQuantity()+"</td><td>"+sumPrice+"</td></tr><tr><td></td><td></td><td></td></tr></table></FW></FH>\n");
        }
        sb.append("----------------------\n");
        sb.append("消费合计："+tbOrder.getOrderTotalAmt()+"元\n\n");
        sb.append("实际支付：<FH><FW>"+tbOrder.getOrderUserPay()+"元</FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("<FS>客户备注：</FS>\n<FS>"+tbOrder.getRemark()+"</FS>\n");
        sb.append("----------#"+tbOrder.getServiceNo()+"完-------\n");

        System.out.println(sb.toString());
        return sb.toString();
    }
}
