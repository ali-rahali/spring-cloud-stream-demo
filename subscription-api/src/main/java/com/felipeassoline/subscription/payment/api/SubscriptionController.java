package com.felipeassoline.subscription.payment.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
    private final SubscriptionRequestsProducer subscriptionRequestsProducer;

    public SubscriptionController(SubscriptionRequestsProducer subscriptionRequestsProducer) {
        this.subscriptionRequestsProducer = subscriptionRequestsProducer;
    }

    /**
     * curl -d "MyRequest" -X POST http://localhost:8181/api/request-approval
     */
    @PostMapping("/api/request-approval")
    public String requestApproval(@RequestBody final String subscriptionRequest) {
        subscriptionRequestsProducer.requestApproval(subscriptionRequest);
        return "Your request was sent to bank approval";
    }
}
