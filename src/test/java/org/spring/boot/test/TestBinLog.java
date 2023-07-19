package org.spring.boot.test;

import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import org.junit.Test;

import java.io.File;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-18
 */
public class TestBinLog {
    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        String filePath = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\data\\DESKTOP-ADEGGAS-bin.000003";
        File binlogFile = new File(filePath);
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        try (BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer)) {
            for (Event event; (event = reader.readEvent()) != null; ) {
                System.out.println(event);
            }
        }
    }

}
