package cn.exev.demo.service;



import cn.exev.demo.entity.TbOrder;

import java.util.List;

public interface PrintDetail {
    public void print(TbOrder tbOrder, List itemList, List PaymentList);
}
