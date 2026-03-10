package com.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;

public class WebScraper {

    private static final Logger logger =
            LoggerFactory.getLogger(WebScraper.class);

    public static void main(String[] args) throws Exception {
        // create request
        String url = "https://www.alphavantage.co/query?function=NEWS_SENTIMENT&tickers=AAPL&apikey=YOUR_KEY";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        // send request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200){
            logger.error("Failed to get a response. Request: {}", request);

        }

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<JsonNode> feedNodes = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(response.body());
            JsonNode feedNode = root.get("feed");
            for (JsonNode feednode: feedNode){
                feedNodes.add(feednode.get("title"));
            }
        } catch (Exception e){
            logger.error("Failed to parse 'feed' from the JSON response.", e);
        }

        System.out.println(feedNodes);



    }
}