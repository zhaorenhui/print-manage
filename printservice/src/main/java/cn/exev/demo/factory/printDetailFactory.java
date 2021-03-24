package cn.exev.demo.factory;


import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.service.PrintDetail;
import cn.exev.demo.service.impl.AppointEateInPrintDetail;
import cn.exev.demo.service.impl.AppointTakeAwayPrintDetail;
import cn.exev.demo.service.impl.EateInPrintDetail;
import cn.exev.demo.service.impl.TakeAwayPrintDetail;

import java.util.List;

public class printDetailFactory {

    /**
     * <li>DELIVERY 快递订单
     * <li>RECOMMEND 推荐购
     * <li>QUALIFICATION 资格购
     * <li>MILK_TEA 奶茶购
     * <li>SWEET 甜品购
     * <li>FAST_PAY 快捷买单
     * <li>EATE_IN 堂食
     * <li>TAKE_AWAY 外带
     */
    public static PrintDetail createPrintDetail(TbOrder order, List itemList, List paymentList){
        PrintDetail printDetail = null;
        switch (order.getType()){
            case SWEET:
                break;
            case EATE_IN:
                if(order.getPlanConsumeTime() != null){
                    printDetail = new AppointEateInPrintDetail();
                }else{
                    printDetail = new EateInPrintDetail();
                }
                break;
            case DELIVERY:
                break;
            case RECOMMEND:
                break;
            case QUALIFICATION:
                break;
            case MILK_TEA:
                break;
            case FAST_PAY:
                break;
            case TAKE_AWAY:
                if(order.getPlanConsumeTime() != null){
                    printDetail = new AppointTakeAwayPrintDetail();
                }else{
                    printDetail = new TakeAwayPrintDetail();
                }
                break;
        }
        return printDetail;
    }
}
