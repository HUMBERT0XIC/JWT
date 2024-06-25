package com.mx.Catalago.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

	private static final String POKEMON_API_URL = "https://api.chucknorris.io/jokes/random";

	private final RestTemplate restTemplate;

	public PokemonService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getPokemonInfo() {
		return restTemplate.getForObject(POKEMON_API_URL, String.class);
	}
}
