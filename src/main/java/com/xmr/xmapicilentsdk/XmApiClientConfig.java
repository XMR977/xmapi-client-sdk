package com.xmr.xmapicilentsdk;


import com.xmr.xmapicilentsdk.client.XmApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("xmapi.client")
@Data
@ComponentScan("com.xmr.xmapicilentsdk")
public class XmApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public XmApiClient xmApiClient(){
        return new XmApiClient(accessKey,secretKey);
    }
}
