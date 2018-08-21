package com.felipeassoline.subscription.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({Processor.class})
public class PaymentProcessor {
    private final Processor processor;
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessor.class);

    public PaymentProcessor(Processor processor) {
        this.processor = processor;
    }

    @StreamListener(target = Processor.INPUT)
    public void processPayment(Message<String> paymentApprovalRequest) {

        if (System.currentTimeMillis() % 2 == 0) {
            String paymentResult = paymentApprovalRequest.getPayload() + ": Payment approved";
            LOGGER.info(paymentResult);
            processor.output().send(MessageBuilder.withPayload(paymentResult).build());
        } else {
            String paymentResult = paymentApprovalRequest.getPayload() + ": Payment denied";
            LOGGER.info(paymentResult);
            processor.output().send(MessageBuilder.withPayload(paymentResult).build());
        }

    }


}
