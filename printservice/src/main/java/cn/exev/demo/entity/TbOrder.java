package cn.exev.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.exev.demo.constant.OrderConstants;
import lombok.Data;

/**
 * tb_order
 * @author 
 */
@Data
public class TbOrder implements Serializable {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 冗余信息
     */
    private String merchantName;

    /**
     * 冗余信息
     */
    private String merchantAddress;

    /**
     * 店铺主图
     */
    private String merchantPicUrl;

    /**
     * - DELIVERY 快递订单
   - RECOMMEND 推荐购
   - QUALIFICATION 资格购
   - MILK_TEA 奶茶购
   - SWEET 甜品购
   - FAST_PAY 快捷买单
   - EAT_IN 堂食
   - TAKE_AWAY 外带
     */
    private OrderConstants.ORDER_TYPE type;

    /**
     * - 0:待付款
   - 1:未支付超时取消（终）
   - 2:未支付用户取消（终）
   - 3:待发货
   - 4:待收货
   - 5:收货完成（终）
   - 6:收货超时完成（终）
   - 7:待核销
   - 8:核销完成（终）
   - 9 核销超时（终）
     */
    private String status;

    /**
     * APPID
     */
    private String appid;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 计划到店消费/取餐时间
     */
    private Date planConsumeTime;

    /**
     * 就餐人数
     */
    private Integer dinnerNumber;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 预约手机号
     */
    private String orderMobile;

    /**
     * 标准QRCode
     */
    private String verifyQrcode;

    /**
     * 服务号/取餐码
     */
    private String serviceNo;

    /**
     * 核销码
     */
    private String verifyCode;

    /**
     * 核销时间
     */
    private Date verifyTime;

    /**
     * 核销开始时间
     */
    private Date verifyBeginTime;

    /**
     * 核销结束时间
     */
    private Date verifyEndTime;

    /**
     * - 0:无售后
   - 1:待商户确认
   - 2:商户拒绝
   - 3:待客户发货
   - 4:待商户收货
   - 5:待退款
   - 6:退款完成
   - 7:平台取消
     */
    private String asStatus;

    /**
     * - 0:未结算 初始状态
   - 1:已结算
   - 2:待结算 
   
   待结算：为定时任务扫描准备，防止重复扫描，扫描未计算0，加入延迟队列，修改状态2.结算处理支持未结算、待结算
   
   也可设计为业务触发，发生核销后放入结算队列，严谨一些更新DB
     */
    private String settleStatus;

    /**
     * 无微信类支付即下单时间，否则微信回调时间
     */
    private Date payTime;

    /**
     * - 0:未删除（默认）
   - 1:已删除
     */
    private String delFlag;

    /**
     * 下单IP
     */
    private String orderIp;

    /**
     * 总金额=订单金额（不减任何优惠）
   
   普通商品：售卖价格
   奶茶、资格购、甜品商品：原价，抵扣金额可按原价-售卖价格计算
   
     */
    private BigDecimal orderTotalAmt;

    /**
     * 用户外部支付渠道金额
     */
    private BigDecimal orderUserOutPay;

    /**
     * 账户+外部支付
     */
    private BigDecimal orderUserPay;

    /**
     * 不减任何优惠
   
   快捷买单、店内就餐、打包外带、预约堂食、预约外带:订单金额-津贴
   其余:商品计划结算价格汇总
   
     */
    private BigDecimal orderSellerAmt;

    /**
     * 订单货款金额减去商家优惠的金额
     */
    private BigDecimal planSettleAmt;

    /**
     * 下单时点，商户交易费率，例如4%
     */
    private BigDecimal commissionFeePer;

    /**
     * 计划结算金额x费率 小数点后两位，四舍五入
     */
    private BigDecimal commissionFee;

    /**
     * REMARK
     */
    private String remark;

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