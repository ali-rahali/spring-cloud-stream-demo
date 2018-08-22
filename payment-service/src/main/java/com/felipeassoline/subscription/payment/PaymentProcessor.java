package com.felipeassoline.subscription.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.Map;

@EnableBinding({Processor.class})
public class PaymentProcessor {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessor.class);

    @SendTo(Processor.OUTPUT)
    @StreamListener(target = Processor.INPUT)
    public Message<Map<String, Object>> processPayment(Message<Map<String, Object>> paymentApprovalRequest) {
        Map<String, Object> request = paymentApprovalRequest.getPayload();
        String priority = "normal";
        if (System.currentTimeMillis() % 2 == 0) {
            request.put("status", "approved");
        } else {
            request.put("status", "denied");
            priority = "urgent";
        }

        LOGGER.info(request.toString());

        return MessageBuilder.withPayload(request)
                .setHeader("notificationPriority", priority)
                .build();
    }

}
