package pl.softwareskill.course.kafka.hwmoniotr.producer;

import java.util.concurrent.atomic.AtomicBoolean;
import static lombok.AccessLevel.PRIVATE;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class KafkaProducerRunnable implements Runnable {
    AtomicBoolean closed = new AtomicBoolean(false);
    KafkaProducer producer;
    String topicName;

    public KafkaProducerRunnable(KafkaProducer producer, String topicName) {
        this.producer = producer;
        this.topicName = topicName;
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                HardwareInfo hardwareInfo = HardwareInfoFactory.createHardwareInfo();
                ProducerRecord<String, HardwareInfo> record = new ProducerRecord<>(topicName, "key", hardwareInfo);
                //send data
                producer.send(record);
                producer.flush();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Sending interrupted", e);
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) throw e;
        } finally {
            producer.close();
        }
    }
}