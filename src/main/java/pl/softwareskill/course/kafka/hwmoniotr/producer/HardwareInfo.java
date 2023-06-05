package pl.softwareskill.course.kafka.hwmoniotr.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import static lombok.AccessLevel.PRIVATE;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = PRIVATE)
@Builder
@Data
public class HardwareInfo {

    Long availableMemory;
    Long discFreeSpace;

    HardwareInfo(@JsonProperty("availableMemory") Long availableMemory,
                 @JsonProperty("discFreeSpace") Long discFreeSpace) {
        this.availableMemory = availableMemory;
        this.discFreeSpace = discFreeSpace;
    }
}
