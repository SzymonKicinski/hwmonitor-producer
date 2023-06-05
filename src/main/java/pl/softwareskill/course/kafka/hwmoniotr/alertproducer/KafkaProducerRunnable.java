package pl.softwareskill.course.kafka.hwmoniotr.alertproducer;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;

import java.util.concurrent.atomic.AtomicBoolean;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class KafkaProducerRunnable implements Runnable {
    AtomicBoolean closed = new AtomicBoolean(false);
    KafkaProducer producer;
//    String topicName;
    String topicAlertName;

    public KafkaProducerRunnable(KafkaProducer producer, String topicAlertName) {
        this.producer = producer;
//        this.topicName = topicName;
        this.topicAlertName = topicAlertName;
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                HardwareAlertInfo hardwareAlertInfo = HardwareAlertInfoFactory.createHardwareInfo();
//                if (hardwareAlertInfo.getDiscFreeSpace() < 53.40) {
                    ProducerRecord<String, HardwareAlertInfo> record = new ProducerRecord<>(topicAlertName, "key", hardwareAlertInfo);
                    //send data
                    producer.send(record);
                    producer.flush();
                    Thread.sleep(2000);
//                }
//                ProducerRecord<String, HardwareInfo> record = new ProducerRecord<>(topicName, "key", hardwareInfo);
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