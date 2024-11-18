package org.might.sinaction.messaging;

import lombok.extern.slf4j.Slf4j;
import org.might.sinaction.db.entity.TacoOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitOrderReceiver implements OrderReceiver {

    private RabbitTemplate rabbit;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public TacoOrder receiveOrder() {
        return rabbit.receiveAndConvert("queue", new ParameterizedTypeReference<TacoOrder>() {
        });
    }

    @RabbitListener(queues = "queue")
    public void receiveOrderListener(TacoOrder tacoOrder) {
        log.info("Received order: {}", tacoOrder);
    }


}
