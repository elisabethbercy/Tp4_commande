package com.example.tp4_commande.commandes;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.tp4_commande.articles.Articles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommandeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public CommandeProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        // ObjectMapper is used  to transform Object to Json 
        this.objectMapper = objectMapper;
        // KafkaTemplate is used to send messages to Kafka topics
        this.kafkaTemplate = kafkaTemplate;
    }

    // public void produce(String message) {
    //     kafkaTemplate.send("commandes-topic", message);

    // }

    public void produce(Commandes commandes) {
        //produce 
        try {
            String cmessage = objectMapper.writeValueAsString(commandes);
            kafkaTemplate.send("commandes-topic", cmessage);

            System.out.println("Commandes a Kafka: " + cmessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void produce(Articles articles) {
        try {
            String amessage = objectMapper.writeValueAsString(articles);
            kafkaTemplate.send("articles-topic", amessage);

            System.out.println("Articles a Kafka: " + amessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}

