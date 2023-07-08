package ar.com.codoacodo2.services.impl;

import ar.com.codoacodo2.dto.reqres.ListResource;
import feign.RequestLine;

public interface FeignResourceService {

		@RequestLine("GET")
		ListResource findAll();

		
}
