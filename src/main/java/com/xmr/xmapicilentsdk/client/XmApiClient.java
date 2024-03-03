package com.xmr.xmapicilentsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xmr.xmapicilentsdk.model.User;
import com.xmr.xmapicilentsdk.utils.SignUtils;


import java.util.HashMap;
import java.util.Map;


public class XmApiClient {

    private String accessKey;
    private String secretKey;

    public XmApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result = HttpUtil.get("http://localhost:8123/api/name/", paramMap);
        return result;
    }


    public String getNameByPost(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result = HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        return result;
    }



    private Map<String, String> getHeaderMap(String body){
        Map<String, String> hasMap = new HashMap<>();
        hasMap.put("accessKey", accessKey);
//        hasMap.put("secretKey", secretKey);
        hasMap.put("nonce", RandomUtil.randomNumbers(4));
        hasMap.put("body", body);
        hasMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hasMap.put("sign", SignUtils.getSign(body, secretKey));
        return hasMap;
    }



    public String getUsernameByPost(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        System.out.println(httpResponse.body());
        return "sss"+httpResponse.body();
    }
}
