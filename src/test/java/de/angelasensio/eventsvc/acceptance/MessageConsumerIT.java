package de.angelasensio.eventsvc.acceptance;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import de.angelasensio.eventsvc.event.EmployeeEvent;

//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource("/application-test.properties")
//@DirtiesContext
public class MessageConsumerIT {

    public static final String EMP_TOPIC = "employee";

    @ClassRule
    public static final EmbeddedKafkaRule KAFKA_EMBEDDED = new EmbeddedKafkaRule(1, true, EMP_TOPIC);

    private Consumer<String, EmployeeEvent> employeeEventConsumer;

    @BeforeClass
    public static void init() throws IOException {
        System.setProperty("spring.kafka.bootstrap-servers", KAFKA_EMBEDDED.getEmbeddedKafka().getBrokersAsString());
    }

    @AfterClass
    public static void shutDown() {
        KAFKA_EMBEDDED.getEmbeddedKafka().destroy();
    }

    @Before
    public void setUp() {
        employeeEventConsumer = createConsumer();
        KAFKA_EMBEDDED.getEmbeddedKafka().consumeFromAnEmbeddedTopic(employeeEventConsumer, EMP_TOPIC);
    }

    @After
    public void cleanUp() {
        employeeEventConsumer.close();
    }

    private Consumer<String, EmployeeEvent> createConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("employeeGroup", "true", KAFKA_EMBEDDED.getEmbeddedKafka());
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EmployeeEventDeserializer.class);
        return new DefaultKafkaConsumerFactory<String, EmployeeEvent>(consumerProps)
                .createConsumer();
    }

    @Test
    public void consumeEvent() throws Exception {
        EmployeeEvent event = EmployeeEvent.builder().employeeUuid(UUID.randomUUID())
                .operation("create")
                .timestamp(System.currentTimeMillis())
                .build();


    }



}
