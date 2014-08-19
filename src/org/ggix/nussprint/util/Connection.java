package org.ggix.nussprint.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.ggix.nussprint.util.Constants.URL;

public class Connection {
	private static RestTemplate restTemplate;
	private static Connection connection;

	public static RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
					new GsonHttpMessageConverter());
		}
		return restTemplate;
	}

	public static Connection getConnection() {
		if (connection == null) {
			connection = new Connection();
		}
		return connection;
	}

	public ResponseEntity<Object> post(String method, Object request,
			@SuppressWarnings("rawtypes") Class response)
			throws HttpClientErrorException {
		try {
			ArrayList<Charset> charsets = new ArrayList<Charset>();
			charsets.add(Charset.forName("UTF-8"));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setAcceptCharset(charsets);
			
			
			getRestTemplate()
					.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

			return getRestTemplate().postForEntity(URL + method, request,
					response, headers);
		} catch (HttpClientErrorException e) {
			switch (e.getStatusCode().value()) {
			case 400:
				e.printStackTrace();
				return null;
			case 404:
				e.printStackTrace();
				return null;
			case 500:
				e.printStackTrace();
				return null;
			}
			throw e;
		}
	}

	public ResponseEntity<Object> postMultipartData(String method,
			MultiValueMap<String, Object> mvm,
			@SuppressWarnings("rawtypes") Class response)
			throws HttpClientErrorException {

		try {
			FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
			formHttpMessageConverter.setCharset(Charset.forName("UTF8"));
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

//			restTemplate = new RestTemplate();
			restTemplate = getRestTemplate();
			restTemplate.getMessageConverters().add(formHttpMessageConverter);
			restTemplate
					.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
					mvm, requestHeaders);
			return restTemplate.exchange(URL + method, HttpMethod.POST,
					requestEntity, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ResponseEntity<Object> get(String method,
			@SuppressWarnings("rawtypes") Class response,
			Map<String, Object> params) {
//		method += (method.endsWith("/")) ? "" : "/";
		Map<String, Object> vars = new HashMap<String, Object>();

		ResponseEntity<Object> object = null;
		try {
			object = getRestTemplate().getForEntity(URL + method, response,
					vars);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return object;
	}

	public void put(String method, Object request) {
		getRestTemplate().put(URL + method, request);
	}
}
