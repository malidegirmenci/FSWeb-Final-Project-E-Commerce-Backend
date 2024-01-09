package org.workintech.utils;

import java.util.UUID;

public class Helper {
    public static String generateDummyToken(){
        return UUID.randomUUID().toString();
    }
}
