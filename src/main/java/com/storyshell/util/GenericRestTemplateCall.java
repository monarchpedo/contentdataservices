package com.storyshell.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Monarchpedo
 *
 * @param <T>
 * @param <V>
 */

@Component
public class GenericRestTemplateCall<T, V> {

	private RestTemplate restTemplate = new RestTemplate();

	private static final String URL = "http://localhost:8000/oauth/v1";

	/**
	 * 
	 * @param requestUri
	 * @param data
	 * @param genericClass
	 * @return it is used to get data from other services remotely
	 */
	public V doGetExecute(String requestUri, T data, Class<V> genericClass) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json");

		HttpEntity<T> entity = new HttpEntity<T>(headers);

		ResponseEntity<V> response = restTemplate.exchange(URL + requestUri, HttpMethod.GET, entity, genericClass);

		return response.getBody();
	}

	/**
	 * 
	 * @param requestUri
	 * @param data
	 * @param genericClass
	 * @return it is used for post data to other services remotely
	 */
	public ResponseEntity<V> doPostExecute(String requestUri, T data, Class<V> genericClass) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json");
        
		HttpEntity<T> entity = new HttpEntity<T>(data,headers);

		ResponseEntity<V> response = restTemplate.exchange(URL + requestUri, HttpMethod.POST, entity, genericClass);

		return response;
	}

	public static void main(String args[]) {
		GenericRestTemplateCall<String, Boolean> rest = new GenericRestTemplateCall<String, Boolean>();
		Boolean result = rest.doGetExecute("/user/2/check", null, Boolean.class);
		System.out.println(result.booleanValue());
	}
}
