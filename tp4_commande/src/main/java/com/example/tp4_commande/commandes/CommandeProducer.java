public class CommandeProducer {

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommandeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CommandeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(String message) {
        kafkaTemplate.send("my-first-topic", message);
    }
}

}
