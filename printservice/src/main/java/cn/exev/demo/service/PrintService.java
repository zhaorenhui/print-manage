package cn.exev.demo.service;

import cn.exev.demo.entity.TbOrderPojo;
import cn.exev.demo.factory.PrintDetailFactory;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

//import java.nio.charset.StandardCharsets;

public class PrintService {

    private RestTemplate restTemplate;

    public PrintService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final static String partner = "43247299";//用户id
    private final static String machine_code = "4004730601";//打印机终端号
    private final static String apiKey = "5607af76f2cf3c915f767a68cbf3dbeb28646cd1";//API密钥
    private final static String mKey = "107711783508";//打印机密钥


    private final static String URI_ADDPRINT = "https://deli-open.10ss.net/addprint";
    private final static String URI_REOMVEPRINT = "https://deli-open.10ss.net/removeprint";
    private final static String URI_GETSTATUS = "https://deli-open.10ss.net/getstatus";
    private final static String URI_PRINT = "https://deli-open.10ss.net";


    private static void removePrint() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("machine_code", machine_code);
        params.put("partner", partner);
        params.put("sign", signRequest(params));

        exec(URI_REOMVEPRINT, params);
    }

    private static void addPrint() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("partner", partner);
        params.put("machine_code", machine_code);
        params.put("printname", "程序添加打印机");
        params.put("mobilephone", "");
        params.put("username", "yhme");

        String sign = signRequest(params);
        params.put("sign", sign);
        params.put("msign", mKey);

        exec(URI_ADDPRINT, params);


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

    //打印机是否在线接口0是离线1是在线2是缺纸
    public static void getStatus() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("machine_code", machine_code);
        params.put("partner", partner);
        String sign = signRequest(params);
        params.put("sign", sign);
        exec(URI_GETSTATUS, params);
    }

    //打印机打印消息
    public void print(TbOrderPojo tbOrderPojo,PrintDetailFactory printDetailFactory) {
        PrintDetail printDetail = printDetailFactory.createPrintDetail(tbOrderPojo);
        String s = printDetail.print(tbOrderPojo);

        Map<String, String> params = new HashMap<String, String>();
        params.put("partner", partner);
        params.put("machine_code", machine_code);
        params.put("time", String.valueOf(System.currentTimeMillis()));

        String sign = signRequest(params);
        params.put("sign", sign);
        params.put("content", s);
        exec(URI_PRINT, params);
    }

    /**
     * 打印签名
     *
     * @param params
     * @return
     */
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

}
