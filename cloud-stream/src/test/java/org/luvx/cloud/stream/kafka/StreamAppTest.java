package org.luvx.cloud.stream.kafka;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StreamAppTest.class)
@EmbeddedKafka(count = 4, ports = {9092, 9093, 9094, 9095}, zookeeperPort = 52181)
class StreamAppTest {
    static {
        System.setProperty(EmbeddedKafkaBroker.BROKER_LIST_PROPERTY,
                "spring.kafka.bootstrap-servers");
    }

    @Test
    public void contextLoads() throws IOException {
        System.in.read();
    }
}