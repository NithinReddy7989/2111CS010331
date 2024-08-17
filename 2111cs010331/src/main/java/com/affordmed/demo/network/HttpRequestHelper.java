package com.affordmed.demo.network;

import com.affordmed.demo.model.Response;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestBuilderKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;

public interface HttpInterface {
    Response getData(String url) throws Exception;
}

public class HttpInterfaceImpl implements HttpInterface {
    private final HttpClient client;

    public HttpInterfaceImpl(HttpClient client) {
        this.client = client;
    }

    @Override
    public Response getData(String url) {
        try {
            String response = client.get(new HttpRequestBuilder() {{
                url(url);
            }}).body(String.class);
            // ObjectMapper objectMapper = new ObjectMapper();
            // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // return objectMapper.readValue(response, Response.class);
            return new Gson().fromJson(response, Response.class);
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
            return null;
        }
    }
}

