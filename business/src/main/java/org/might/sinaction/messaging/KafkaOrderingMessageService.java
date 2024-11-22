package org.might.sinaction.messaging;

import lombok.extern.slf4j.Slf4j;
import org.might.sinaction.db.entity.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Primary
@Slf4j
@Component
public class KafkaOrderingMessageService implements OrderMessagingService {

    private final KafkaTemplate<String, TacoOrder> kafkaTemplate;

    @Autowired
    public KafkaOrderingMessageService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        kafkaTemplate.send("cloud.orders.topic", order);
    }

    @KafkaListener(id = "tacoOrder",
            topicPartitions = {
                    @TopicPartition(
                            topic = "cloud.orders.topic",
                            partitionOffsets = @PartitionOffset(
                                    partition = "0",
                                    initialOffset = "0")
                    )
            })
    public void listener(Message<TacoOrder> message) {
        log.info("Received from partition: {} with timestamp: {}, order: {}, headers: {}",
                message.getHeaders().get(KafkaHeaders.RECEIVED_PARTITION),
                message.getHeaders().get(KafkaHeaders.RECEIVED_TIMESTAMP),
                message.getPayload(),
                message.getHeaders()
        );
    }

}
