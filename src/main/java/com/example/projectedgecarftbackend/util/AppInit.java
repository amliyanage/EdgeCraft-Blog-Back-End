package com.example.projectedgecarftbackend.util;

import java.util.UUID;

public class AppInit {

    public static String generateUserId (){
        return "U-"+ UUID.randomUUID();
    }

    public static String generateProjectId (){
        return "P-"+UUID.randomUUID();
    }

}
