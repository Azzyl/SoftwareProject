package com.example.eWaiter.basket;

import java.util.HashMap;

/**
 * Created by azori on 18.04.2018.
 */

public class Basket {
    private static HashMap<String, Integer> basket;

    static {
        basket = new HashMap();
    }

    public static boolean add(String dishName, int pieces) {
        int oldSize = basket.size();
        int amount;
        try {
            amount = basket.get(dishName);
        } catch (Exception e) {
            amount = 0;
        }
        basket.put(dishName, amount + pieces);
        return basket.size() != oldSize;
    }

    public static void remove(String dishName) {
        basket.remove(dishName);
    }

    public static String[] getAll() {
        String[] dishList = new String[0];
        dishList = (String[]) basket.keySet().toArray();
        return dishList;
    }
}
