package com.example.tp4_commande.commandes;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.tp4_commande.articles.Articles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.support.SendResult;
import java.util.concurrent.CompletableFuture;

@Service
public class CommandeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CommandeProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        // ObjectMapper is used to transform Object to JSON
        this.objectMapper = objectMapper;
        // KafkaTemplate is used to send messages to Kafka topics
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(Articles articles) {
        try {
            String message = objectMapper.writeValueAsString(articles);
            System.out.println("<----------------------------------------------------------");
            System.out.println("Sending message to Kafka Fom ** CommandeProducer: " + message);

            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("articles-topic", message);

            future.thenAccept(result -> 
                System.out.println("Message sent successfully: " + result.getProducerRecord().value())
            ).exceptionally(ex -> {
                System.err.println("Message sending failed: " + ex.getMessage());
                return null;
            });

        } catch (JsonProcessingException e) {
            System.err.println("Error converting Articles to JSON: " + e.getMessage());
        }
    }
}





    // public void produce(String message) {
    //     kafkaTemplate.send("commandes-topic", message);

    // }

    // public void produce(Commandes commandes) {
        
    //     try {
    //         String cmessage = objectMapper.writeValueAsString(commandes);
    //         kafkaTemplate.send("commandes-topic", cmessage);

    //         System.out.println("Commandes a Kafka: " + cmessage);

    //     } catch (JsonProcessingException e) {
    //         e.printStackTrace();
    //     }
    // }

