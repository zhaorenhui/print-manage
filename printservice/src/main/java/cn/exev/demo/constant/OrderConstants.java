package cn.exev.demo.constant;

public class OrderConstants {

    public enum ORDER_STATUS {
        WAITING_FOR_PAYMENT("0"), TIMEOUT("1"), CANCELED("2"), WAITING_FOR_DELIVERY("3"), WAITING_FOR_RECEIVE("4"),
        DELIVERY_FINISH(
                "5"), DELIVERY_FINISH_RECEIVE_TIMEOUT("6"), WAITING_FOR_VERIFY("7"), FINISH("8");

        private final String value;

        ORDER_STATUS(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }


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
    public enum ORDER_TYPE {
        DELIVERY, RECOMMEND, QUALIFICATION, MILK_TEA, SWEET, FAST_PAY, EATE_IN, TAKE_AWAY;
    }

    public enum ORDER_ITEM_BENEFIT {
        ACCOUNT, COUPON, SUBSIDY, QUALIFICATION, MILK_TEA, SWEET;
    }

    /**
     * 1 微信支付 2 个人账户支付 3 优惠券支付 4 津贴支付 5 资格支付 6 奶茶账户支付 7 甜品账户支付
     */
    public enum ORDER_PAYMENT_TYPE {
        WX, ACCOUNT, COUPON, SUBSIDY, QUALIFICATION, MILK_TEA, SWEET;
    }

    public enum ORDER_PAYMENT_STATUS {
        WAIT_PAYMENT, FINISH, PAYMENTING, PAY_TIMEOUT, REFUNDING, FINISH_REFUND;
    }
}
