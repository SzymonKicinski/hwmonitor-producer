package pl.softwareskill.course.kafka.hwmoniotr.alertproducer;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.File;

public class HardwareAlertInfoFactory {

    public static HardwareAlertInfo createHardwareInfo() {
        HardwareAbstractionLayer hal = new SystemInfo().getHardware();
        File cDrive = new File("C:");

        return HardwareAlertInfo.builder()
                .availableRAMMemory(hal.getMemory().getAvailable())
                .discFreeSpace(cDrive.getFreeSpace())
                .discTotalSpace(cDrive.getTotalSpace())
                .build();
    }
}
