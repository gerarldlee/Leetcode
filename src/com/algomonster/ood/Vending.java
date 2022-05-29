package com.algomonster.ood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vending {

    public static class Product {
        String name;
        int price;
        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }
        public String toString() {
            return String.format("%s %d", this.name, this.price);
        }
    }
    public static class Machine {
        HashMap<String, Product> products = new HashMap<>();
        int balance;

        public void newProduct(String name, int price) {
            products.put(name, new Product(name, price));
        }
        public List<String> printProducts() {
            List<Product> products = new ArrayList<Product>(this.products.values());
            products.sort((Product a, Product b) -> Integer.compare(a.price, b.price));
            List<String> out = new ArrayList<String>();
            for (Product product : products) {
                out.add(product.toString());
            }
            return out;
        }

        public void insertCoin(int value) {
            this.balance += value;
        }

        public boolean purchase(String name) {
            Product product = products.get(name);
            if (this.balance < product.price) {
                return false;
            }

            this.balance -= product.price;
            return true;
        }

        public int checkout() {
            int balance = this.balance;
            this.balance = 0;
            return balance;
        }
    }
}
