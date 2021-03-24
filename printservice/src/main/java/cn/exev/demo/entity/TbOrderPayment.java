package cn.exev.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.exev.spring.demo.module5.constant.OrderConstants;
import cn.hutool.core.util.IdUtil;
import lombok.Data;

/**
 * tb_order_payment
 * @author 
 */
@Data
public class TbOrderPayment implements Serializable {
    /**
     * ID
     */
    private String id= IdUtil.simpleUUID();

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * WX
   ACCOUNT
   COUPON
   SUBSIDY
   QUALIFICATION
   MILK_TEA
   SWEET
     */
    private OrderConstants.ORDER_PAYMENT_TYPE type;

    /**
     * - 0:待支付 
   - 1:支付完成（业务时间终态）
   - 2:支付中
   - 3:支付超时关闭（终态）
   
   - 11 退款中
   - 12 退款完成（终态）
     */
    private OrderConstants.ORDER_PAYMENT_STATUS status;

    /**
     * 微信：客户openid
     */
    private String mainId;

    /**
     * 退款时
   正负处理
     */
    private BigDecimal mainReduceValue;

    /**
     * 支付主体扣减时间
     */
    private Date mainReduceTime;

    /**
     * 微信transaction_id 微信支付订单号
     */
    private String mainJournalId;

    /**
     * 支付主体退款流水ID
     */
    private String mainRefundJournalId;

    /**
     * 支付主体退款完成时间
     */
    private Date mainRefundTime;

    /**
     * 支付主体退款金额
     */
    private BigDecimal mainRefundValue;

    /**
     * 抵扣金额
     */
    private BigDecimal amt;

    /**
     * 用于重新拉起支付，微信2小时有效
     */
    private String thirdPreparedId;

    /**
     * 微信支付记录支付渠道号
     */
    private String thirdAccountId;

    /**
     * 微信支付手续费=金额x0.006，保留两位，四舍五入
     */
    private BigDecimal thirdPaymentFee;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}