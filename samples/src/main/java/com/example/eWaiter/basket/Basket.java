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
        basket.put(dishName, 0);
    }

    public static HashMap<String, Integer> getAll() {
        return basket;
    }
//
//    public static int getsize() {
//        int n = 0;
//        for (String item: basket.keySet()) {
//            n += basket.get(item);
//        }
//        return n;
//    }
}
