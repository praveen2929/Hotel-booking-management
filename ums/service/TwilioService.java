package com.ums.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TwilioService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;
//
//    @Value("${twilio.phone-number}")
//    private String fromPhoneNumber;

    @PostConstruct
    private void initializeTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String to, String body) {
        Message message = Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber("+16417159549"),
                        body)
                .create();

        System.out.println("Message sent: " + message.getSid());
    }
}
