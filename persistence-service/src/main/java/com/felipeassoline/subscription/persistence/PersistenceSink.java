package com.felipeassoline.subscription.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding({Sink.class})
public class PersistenceSink {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceSink.class);

    @StreamListener(target = Sink.INPUT)
    public void persist(Message<String> paymentNotification) {
        LOGGER.info("Persistence=" + paymentNotification.getPayload());
    }
}