package pl.softwareskill.course.kafka.hwmoniotr.producer;

import java.io.File;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class HardwareInfoFactory {

    public static HardwareInfo createHardwareInfo() {
        HardwareAbstractionLayer hal = new SystemInfo().getHardware();
        File cDrive = new File("C:");

        return HardwareInfo.builder()
                .availableMemory(hal.getMemory().getAvailable())
                .discFreeSpace(cDrive.getFreeSpace())
                .build();
    }
}
