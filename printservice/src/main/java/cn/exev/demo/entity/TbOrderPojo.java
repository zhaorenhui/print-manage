package cn.exev.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbOrderPojo {
    private TbOrder tbOrder;
    private List<TbOrderItem> tbOrderItemList;
    private List<TbOrderPayment> tbOrderPaymentList;


}
