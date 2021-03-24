package cn.exev.demo.service.impl;


import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.service.PrintDetail;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 预约堂食
 */
public class AppointEateInPrintDetail implements PrintDetail {


    @Override
    public String print(TbOrderPojo tbOrderPojo) {
        TbOrder tbOrder = tbOrderPojo.getTbOrder();
        List itemList = tbOrderPojo.getTbOrderItemList();
        List PaymentList = tbOrderPojo.getTbOrderPaymentList();
        //        堂食 后厨联
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>后厨联</center></FS2>\n");
        sb.append("<FS2><center>#"+tbOrder.getServiceNo()+" 美食卡</center></FS2>\n");
        sb.append("----------------------\n");
        sb.append("堂食     人数："+tbOrder.getDinnerNumber()+"     桌号："+tbOrder.getSeatNumber()+"\n\n");
        sb.append("----------------------\n");
//        sb.append("取餐时间："+ DateUtil.format(tbOrder.getPlanConsumeTime(),"yyyy-MM-dd HH:mm:ss")+"\n");
        sb.append("下单时间："+ DateUtil.format(tbOrder.getCreateTime(),"yyyy-MM-dd HH:mm:ss")+"\n");
        sb.append("订单编号："+tbOrder.getCustomerId()+"\n");
        sb.append("----------------------\n");
        for(Object object : itemList){
            TbOrderItem item = (TbOrderItem) object;
            Long sumPrice = (item.getBenefit()!=null&&!item.getBenefit().equals(""))? item.getOriginalPrice().longValue()*item.getQuantity():item.getPrice().longValue()*item.getQuantity();
            sb.append("<FH><FW><table><tr><td>"+item.getName()+"</td><td>x"+item.getQuantity()+"</td><td>"+sumPrice+"</td></tr><tr><td></td><td></td><td></td></tr></table></FW></FH>\n");
        }
        sb.append("----------------------\n");
        sb.append("消费支付： "+tbOrder.getOrderTotalAmt()+"元\n");
        sb.append("实际支付：<FH><FW>"+tbOrder.getOrderUserPay()+"元</FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("<FS>备注："+tbOrder.getRemark()+"</FS>\n\n");
        sb.append("*********#"+tbOrder.getServiceNo()+"完*******\n");


        System.out.println(sb.toString());

        return sb.toString();
    }

}
