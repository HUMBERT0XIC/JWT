// RestClient.java
package com.mx.Banco.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    private static final String CHUCK_NORRIS_API_URL = "https://api.chucknorris.io/jokes/random";

    private final RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getChuckNorrisJoke() {
        return restTemplate.getForObject(CHUCK_NORRIS_API_URL, String.class);
    }
}
