package com.felipeassoline.subscription.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import java.util.Map;


@EnableBinding({Sink.class})
public class EmailSink {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSink.class);

    @StreamListener(target = Sink.INPUT, condition = "headers['notificationPriority']=='normal'")
    public void sentEmail(Message<Map<String, Object>> paymentNotification) {
        String status = String.valueOf(paymentNotification.getPayload().getOrDefault("status", "pending"));
        LOGGER.info("Normal Payment =" + status);
    }

    @StreamListener(target = Sink.INPUT, condition = "headers['notificationPriority']=='urgent'")
    public void sentUrgentEmail(Message<Map<String, Object>> paymentNotification) {
        String status = String.valueOf(paymentNotification.getPayload().getOrDefault("status", "pending"));
        LOGGER.info("Urgent Payment =" + status);
    }
}