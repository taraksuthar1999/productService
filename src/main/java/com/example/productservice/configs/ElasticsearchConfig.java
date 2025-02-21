package com.example.productservice.configs;

import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class ElasticsearchConfig extends AbstractOpenSearchConfiguration {
    @Value("${ELASTICSEARCH_HOST}")
    private String host;
    @Value("${ELASTICSEARCH_USERNAME}")
    private String username;
    @Value("${ELASTICSEARCH_PASSWORD}")
    private String password;
    @Override
    @Bean
    public RestHighLevelClient opensearchClient() {
        try{

            return RestClients.create(ClientConfiguration.builder()
                    .connectedTo(host)
                    .usingSsl()
                    .withBasicAuth(username, password)
                    .withConnectTimeout(10000)
                    .withSocketTimeout(30000)
                    .build()
            ).rest();
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("error");
        }
    }
}


