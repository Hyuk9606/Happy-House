package com.ssafy.happyhouse.vue.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/open")

public class OpenApiController {
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/apt")
	public Object GetAptDealInfo(@RequestParam Map<String, Object> param) {
		logger.debug("apt - 호출  {}", param);
		UriComponents comp = UriComponentsBuilder.newInstance().scheme("http").host("openapi.molit.go.kr").port(80)
				.path("OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev")
				.queryParam("ServiceKey", param.get("ServiceKey")).queryParam("pageNo", param.get("pageNo"))
				.queryParam("numOfRows", param.get("numOfRows")).queryParam("LAWD_CD", param.get("LAWD_CD"))
				.queryParam("DEAL_YMD", param.get("DEAL_YMD")).build(true);
		logger.debug("uri: {}", comp.toUri());
		return restTemplate.getForObject(comp.toUri(), Map.class);
	}

}
