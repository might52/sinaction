package org.might.sinaction.messaging;


import org.might.sinaction.db.entity.TacoOrder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        sendConvertAndSend(order);
    }

    private void sendOrderMessage(TacoOrder order) {
        MessageConverter converter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        Message message = converter.toMessage(order, messageProperties);
        rabbitTemplate.send(message);
    }

    private void sendConvertAndSend(TacoOrder order) {
        rabbitTemplate.convertAndSend("taco_exchange", "central", order);
    }
}
