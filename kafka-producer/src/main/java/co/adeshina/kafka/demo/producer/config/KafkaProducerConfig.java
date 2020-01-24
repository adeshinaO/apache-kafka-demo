package co.adeshina.kafka.demo.producer.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PreDestroy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:kafka-producer-config.properties")
public class KafkaProducerConfig {

    private Producer<String, String> stringProducer;

    @Bean
    public Producer<String, String> StringProducer() {

        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.ACKS_CONFIG, "");
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "");
        // todo: look at docs for producer config, set some other values here to show knowledge of them e.g retention

        stringProducer = new KafkaProducer<>(config, new StringSerializer(), new StringSerializer());

        return stringProducer;
    }

    @PreDestroy
    private void destroy() {
        stringProducer.close();
    }

}
