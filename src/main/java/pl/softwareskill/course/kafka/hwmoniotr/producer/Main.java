package pl.softwareskill.course.kafka.hwmoniotr.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class Main {

    private final static String TOPIC_NAME = "cpuinfo";
    private final static String BOOTSTRAP_SERVERS = "127.0.0.1:9091";

    public static void main(String[] args) {
        var producerProperites = createProducerProperites();

        KafkaProducer<String, HardwareInfo> producer = new KafkaProducer<String, HardwareInfo>(producerProperites);

        Thread thread = new Thread(new KafkaProducerRunnable(producer, TOPIC_NAME));
        thread.start();
    }

    private static Properties createProducerProperites() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
        return props;
    }
}