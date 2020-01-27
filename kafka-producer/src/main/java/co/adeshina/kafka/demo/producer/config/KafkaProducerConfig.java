package co.adeshina.kafka.demo.producer.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:kafka-producer-config.properties")
public class KafkaProducerConfig {

    private Producer<String, String> stringProducer;

    @Value("${acks}")
    private String acks;

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${client.id}")
    private String clientId;

    @Bean
    public Producer<String, String> StringProducer() {

        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.ACKS_CONFIG, acks);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);

        stringProducer = new KafkaProducer<>(config, new StringSerializer(), new StringSerializer());
        return stringProducer;
    }

    @PreDestroy
    private void destroy() {
        stringProducer.close();
    }

}
