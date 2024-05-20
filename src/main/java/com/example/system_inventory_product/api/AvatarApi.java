package com.example.system_inventory_product.api;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AvatarApi {

    private String urlApi = "https://api.dicebear.com/8.x/micah/svg";


    public String getAvatarApi(String username) {
        return urlApi +"?seed=" + username;
    }

}