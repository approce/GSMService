package sms.com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sms.com.model.Request;

@Component
public class RemoteController {

    @Value("${remote.server.url}")
    String remote_url;

    public void sendRequestToRemote(Request request) {

        String url = remote_url + "?request_id=" + request.getID() + "&code=" +
                request.getMessageList().get(0).getCode();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }
}