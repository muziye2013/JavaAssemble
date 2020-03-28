package com.tison.basic;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class MockOOMTestController {

    @Test
    public void test(){

        for(int i = 0 ; i<10000; i++){
            System.out.println("send the get count=" + i);
            sendGet("http://localhost:8080/oom/test0");
        }
    }

    /**
     * 模拟请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        String result = "";
        try(
                CloseableHttpClient httpClient=HttpClients.createDefault();
        ) {
            // 通过址默认配置创建一个httpClient实例
            // 创建httpGet远程连接实例
                        // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            HttpGet httpGet = new HttpGet(url);

            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            CloseableHttpResponse response=httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
