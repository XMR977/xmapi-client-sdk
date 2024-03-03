package com.xmr.xmapicilentsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtils {

    /**
     * generate sign
     * @param body
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body +"."+secretKey;
        String cryptmd5 = md5.digestHex(content);
        return cryptmd5;

    }
}
