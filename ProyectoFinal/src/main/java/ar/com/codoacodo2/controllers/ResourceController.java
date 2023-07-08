package ar.com.codoacodo2.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import ar.com.codoacodo2.dto.reqres.ListResource;
import ar.com.codoacodo2.services.impl.FeignResourceService;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import feign.slf4j.Slf4jLogger;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

	@Value(value = "${ENDPOINT_REQ_RES}")
	private String apiEndpoint;
	
	@GetMapping()
	public ResponseEntity<ListResource> findAll() {
		
		FeignResourceService response  = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .logger(new Slf4jLogger(FeignResourceService.class))
				  .logLevel(Logger.Level.FULL)
				  .target(FeignResourceService.class, apiEndpoint);
		

		//response.getStatusCode();
		return ResponseEntity.ok(response.findAll());
		
	}
}