package cn.exev.demo.controller;

import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
import cn.exev.demo.entity.TbOrderPayment;
import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.factory.PrintDetailFactory;
import cn.exev.demo.service.PrintDetail;
import cn.exev.demo.service.PrintService;
import cn.exev.demo.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrintTestController {

    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private PrintDetailFactory printDetailFactory;

    @Autowired
    private PrintService printService;

    @RequestMapping("/print")
    public void print() {
        TbOrder tbOrder = tbOrderService.selectById("EATE_IN");
        List<TbOrderItem> itemList = tbOrderService.selectItemById();
        TbOrderPayment orderPayment = tbOrderService.selectPaymentById();
        List<TbOrderPayment> orderPayments = new ArrayList<TbOrderPayment>();
        orderPayments.add(orderPayment);
        TbOrderPojo tbOrderPojo = new TbOrderPojo(tbOrder, itemList, orderPayments);
        System.out.println("打印开始执行");
        printService.print(tbOrderPojo,printDetailFactory);
        System.out.println("打印完成");
    }

}
