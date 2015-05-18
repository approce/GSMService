package sms.com.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sms.com.model.Request;

@Component
public class RemoteController {

    public void sendRequestToRemote(Request request) {

        String url = "http://localhost:8080/remote/?request_id=" + request.getID() + "&code=" +
                request.getMessageList().get(0).getCode();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }
}