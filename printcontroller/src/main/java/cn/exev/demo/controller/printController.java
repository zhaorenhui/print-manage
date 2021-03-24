package cn.exev.demo.controller;

import cn.exev.spring.demo.printController.entity.Printer;
import cn.exev.spring.demo.printController.entity.TbOrder;
import cn.exev.spring.demo.printController.entity.TbOrderItem;
import cn.exev.spring.demo.printController.entity.TbOrderPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.PrintService;
import java.util.List;

@Controller
@RequestMapping("/printer")
public class printController {
    @Autowired
    PrintService printService;
    @Autowired


    @RequestMapping("addPrinter")
    public void addPrinter(@RequestParam(value = "brand") String brand,
                           @RequestParam(value = "model") String model,
                           @RequestParam(value = "sn") String sn,
                           @RequestParam(value = "key") String key,
                           @RequestParam(value = "name") String name){

        printService.addPrinter(brand,model,sn,key,name);
    }

    @RequestMapping("updatePrinter")
    public void updatePrinter(@RequestParam(value = "id") String id,
                              @RequestParam(value = "name") String name){
        printService.updatePrinter(id,name);
    }

    @RequestMapping("deletePrinter")
    public void deletePrinter(@RequestParam(value = "id") String id){
        printService.deletePrinter(id);
    }

    @RequestMapping("selectPrinter")
    public List<Printer> selectPrinter(){
        return printService.selectPrinter();
    }

    @RequestMapping("testPrinter")
    public void testPrinter(@RequestParam(value = "id") String id){
        printService.testPrinter(id);
    }

    @RequestMapping("printerData")
    public void printDetail(TbOrder order, List<TbOrderItem> itemList, List<TbOrderPayment> paymentList){

    }



}
