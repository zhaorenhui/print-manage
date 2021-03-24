package cn.exev.demo.service.impl;


import cn.exev.demo.entity.TbOrder;
import cn.exev.demo.entity.TbOrderItem;
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
    private final static String partner = "43247299";//用户id
    private final static String machine_code = "4004730601";//打印机终端号
    private final static String apiKey = "5607af76f2cf3c915f767a68cbf3dbeb28646cd1";//API密钥
    private final static String mKey = "107711783508";//打印机密钥


    private final static String URI_ADDPRINT = "https://deli-open.10ss.net/addprint";
    private final static String URI_REOMVEPRINT = "https://deli-open.10ss.net/removeprint";
    private final static String URI_GETSTATUS = "https://deli-open.10ss.net/getstatus";
    private final static String URI_PRINT = "https://deli-open.10ss.net";

    @Override
    public void print(TbOrder tbOrder, List itemList, List PaymentList) {

        //        堂食 后厨联
        StringBuffer sb = new StringBuffer("");
        sb.append("<FS2><center>后厨联</center></FS2>\n");
        sb.append("<FS2><center>#"+tbOrder.getSeatNumber()+" 美食卡</center></FS2>\n");
        sb.append("----------------------\n");
        sb.append("堂食     人数："+tbOrder.getDinnerNumber()+"     桌号："+tbOrder.getSeatNumber()+"\n\n");
        sb.append("----------------------\n");
//        sb.append("取餐时间："+ DateUtil.format(tbOrder.getPlanConsumeTime(),"yyyy-MM-dd HH:mm:ss")+"\n");
        sb.append("下单时间："+ DateUtil.format(tbOrder.getCreateTime(),"yyyy-MM-dd HH:mm:ss")+"\n");
        sb.append("订单编号："+tbOrder.getCustomerId()+"\n");
//        sb.append("下单时间：2015-04-09 13:01:22\n\n");
//        sb.append("订单编号：1243435235353434342343\n");
        sb.append("----------------------\n");
        sb.append("<FH><FW><table><tr><td>麻辣牛肉</td><td>x1</td><td>19</td></tr><tr><td></td><td></td><td></td></tr>" +
                "<tr><td>极品鲜毛肚</td><td>x1</td><td>19</td></tr><tr><td></td><td></td><td></td></tr>" +
                "<tr><td>精品千层肚测试测试测试测试测试 </td><td>x1</td><td>19</td></tr></table></FW></FH>\n");
        for(Object object : itemList){
            TbOrderItem item = (TbOrderItem) object;
            sb.append("<FH><FW><table><tr><td>"+item.getName()+"</td><td>x"+item.getQuantity()+"</td><td>"+item.getPrice()+"</td></tr><tr><td></td><td></td><td></td></tr></table></FW></FH>\n");
        }
        sb.append("----------------------\n");
        sb.append("消费合计：57.00元\n\n");
        sb.append("实际支付：<FH><FW>32.00元</FW></FH>\n");
        sb.append("----------------------\n");
        sb.append("<FS>备注：测试测试测试测试测试</FS>\n");
        sb.append("----------------------\n");
        sb.append("----------#999完-------\n");
        sb.append("----------------------\n");
        sb.append("<right>消费支付： "+tbOrder.getOrderTotalAmt()+"元</right>\n");
        sb.append("<right>实际支付：999元</right>\n");
//        sb.append("<right>实际支付："+tbOrder.getOrderTotalAmt().subtract(orderPayment.getAmt())+"元</right>\n");
        sb.append("----------------------\n");
        sb.append("<FS>备注："+tbOrder.getRemark()+"</FS>\n\n");
        sb.append("*********#"+tbOrder.getSeatNumber()+"完*******\n");


        System.out.println(sb.toString());

        Map<String, String> params = new HashMap<String, String>();
        params.put("partner", partner);
        params.put("machine_code", machine_code);
        params.put("time", String.valueOf(System.currentTimeMillis()));

        String sign = signRequest(params);
        params.put("sign", sign);
        params.put("content", sb.toString());

        exec(URI_PRINT, params);
    }

    public static String signRequest(Map<String, String> params) {
        Map<String, String> sortedParams = new TreeMap<String, String>();
        sortedParams.putAll(params);
        Set<Map.Entry<String, String>> paramSet = sortedParams.entrySet();
        StringBuilder query = new StringBuilder();
        query.append(apiKey);
        for (Map.Entry<String, String> param : paramSet) {
            query.append(param.getKey());
            query.append(param.getValue());
        }
        query.append(mKey);
        String encryptStr = DigestUtil.md5Hex(query.toString()).toUpperCase();
        return encryptStr;
    }
    private static void exec(String uri, Map<String, String> postBodyMap) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri);

        post.addHeader("charset", "utf-8");
        HttpEntity httpEntity = new UrlEncodedFormEntity(createParam(postBodyMap), Consts.UTF_8);
        post.setEntity(httpEntity);

        try (CloseableHttpResponse response = httpClient.execute(post)) {

            int statusCode = response.getStatusLine().getStatusCode();
            String str = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("执行返回:" + str);

        } catch (Exception e) {
            System.out.println("执行失败");
            e.printStackTrace();
        }

    }
    private static List<NameValuePair> createParam(Map<String, String> param) {
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (param != null) {
            for (String k : param.keySet()) {
                nvps.add(new BasicNameValuePair(k, param.get(k).toString()));
            }
        }
        return nvps;
    }
}
