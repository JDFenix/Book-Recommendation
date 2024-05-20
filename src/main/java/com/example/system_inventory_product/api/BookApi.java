package com.example.system_inventory_product.api;

import com.example.system_inventory_product.dto.book.BookDetailsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class BookApi {

    private String urlApi = "https://www.googleapis.com/books/v1/volumes";

    public BookDetailsDto getBookDetails(String authorName, String authorSurname, String bookName) {
        String query = "intitle=" + bookName + "+inauthor=" + authorName + authorSurname;
        String url = urlApi + "?q=" + query;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        return extractBookDetails(jsonResponse);
    }

    private BookDetailsDto extractBookDetails(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode itemsNode = rootNode.path("items");

            if (itemsNode.isArray() && itemsNode.size() > 0) {
                JsonNode firstItem = itemsNode.get(0);
                JsonNode volumeInfo = firstItem.path("volumeInfo");
                JsonNode saleInfo = firstItem.path("saleInfo");

                // Extracting required fields
                String description = volumeInfo.path("description").asText();
                String publisher = volumeInfo.path("publisher").asText();
                String publishedDate = volumeInfo.path("publishedDate").asText();
                double retailPrice = saleInfo.path("retailPrice").path("amount").asDouble();
                String currencyCode = saleInfo.path("retailPrice").path("currencyCode").asText();
                String buyLink = saleInfo.path("buyLink").asText();

                return new BookDetailsDto(description, publisher, publishedDate, currencyCode, retailPrice, buyLink);
                //String.format("Description: %s\nPublisher: %s\nPublished Date: %s\nRetail Price: %.2f %s\nBuy Link: %s",
                //description, publisher, publishedDate, retailPrice, currencyCode, buyLink);
            }else {
                System.out.println("No items found in API response");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
