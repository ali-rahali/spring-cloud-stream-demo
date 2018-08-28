package com.felipeassoline.subscription.payment.api;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding({Source.class})
public class SubscriptionRequestsProducer {
    private final Source source;

    public SubscriptionRequestsProducer(Source source) {
        this.source = source;
    }

    public void requestApproval(SubscriptionRequest subscriptionRequest) {
        source.output().send(MessageBuilder.withPayload(subscriptionRequest).build());
    }
}
