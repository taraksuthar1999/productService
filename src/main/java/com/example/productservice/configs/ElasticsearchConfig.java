package com.example.productservice.configs;



import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.time.Duration;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class ElasticsearchConfig{
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate(ElasticsearchClient client) {
//        // ...
//        return new ElasticsearchTemplate(client);
//    }
//    @Override
//    @Bean
//    public RestHighLevelClient opensearchClient() {
//        // ...
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("https://localhost:9200")
////                .usingSsl()
//                .withBasicAuth("admin", "Tarak.Suthar1999@1")
//                .withConnectTimeout(Duration.ofSeconds(5))
//                .withSocketTimeout(Duration.ofSeconds(3))
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
}

