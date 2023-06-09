package pl.softwareskill.course.kafka.hwmoniotr.alertproducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class KafkaJsonSerializer implements Serializer {

    @Override
    public void configure(Map map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(o);
        } catch (Exception e) {
            log.error("Error while serialize message ", e);
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
