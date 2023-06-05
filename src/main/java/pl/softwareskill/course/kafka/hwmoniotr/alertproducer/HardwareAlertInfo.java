package pl.softwareskill.course.kafka.hwmoniotr.alertproducer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Builder
@Data
public class HardwareAlertInfo {

    Long availableRAMMemory;
    Long discFreeSpace;
    Long discTotalSpace;

    HardwareAlertInfo(@JsonProperty("availableRAMMemory") Long availableRAMMemory,
                      @JsonProperty("discFreeSpace") Long discFreeSpace,
                      @JsonProperty("discAllSpace") Long discTotalSpace) {
        this.availableRAMMemory = availableRAMMemory;
        this.discFreeSpace = discFreeSpace;
        this.discTotalSpace = discTotalSpace;
    }
}
