package com.ykeshtdar.StartP9Monolothic.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import org.springframework.web.util.*;

@Service
public class JwtSessionService {
    private final RestTemplate restTemplate;
    @Value("${service.url.patientGetTokenBase}")
    private String patientGetTokenUrlBase;

    public JwtSessionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getToken(String sessionId){
        String patientGetTokenUrl = String.format("%s/getToken",patientGetTokenUrlBase);
        String url = UriComponentsBuilder.fromHttpUrl(patientGetTokenUrl)
                .queryParam("sessionId",sessionId)
                .toUriString();
        String token = restTemplate.getForObject(url,String.class);
        return token;
    }

}
