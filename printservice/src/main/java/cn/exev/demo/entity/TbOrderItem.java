package cn.exev.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

/**
 * tb_order_item
 * @author 
 */
@Data
public class TbOrderItem implements Serializable {
    /**
     * ID
     */
    private String id= IdUtil.simpleUUID();

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品规格ID
     */
    private String productSpecId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品主图
     */
    private String mainPicUrl;

    /**
     * 商品营销描述/副标题
     */
    private String promotionDesc;

    /**
     * 商品原价/划线价
     */
    private BigDecimal originalPrice;

    /**
     * 商品售卖价
     */
    private BigDecimal price;

    /**
     * 计划结算金额
     */
    private BigDecimal planSettleAmt;

    /**
     * ACCOUNT
   COUPON
   SUBSIDY
   QUALIFICATION
   MILK_TEA
   SWEET
     */
    private String benefit;

    /**
     * 权益消耗值
     */
    private BigDecimal benefitValue;

    /**
     * 权益抵扣金额
     */
    private BigDecimal benefitAmt;

    /**
     * 商品营销某库存价格降低，售空后，恢复原价
     */
    private String discountFlag;

    /**
     * 商品详情
     */
    private String productDetails;

    /**
     * 商品使用规则
     */
    private String useRule;

    /**
     * 1 普通商品
   2 优享商品
     */
    private String type;

    /**
     * 奶茶、甜品、资格购有关联父商品ID
     */
    private String relaParentProductId;

    /**
     * 店铺商品分类ID
     */
    private String merchantCategoryId;

    /**
     * 购买数量
     */
    private Integer quantity;

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