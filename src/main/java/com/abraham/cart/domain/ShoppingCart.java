package com.abraham.cart.domain;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.UUID;

public class ShoppingCart {

    UUID cartId;
    private Hashtable<Item, BigDecimal> items;

    public ShoppingCart() {
        this.cartId = UUID.randomUUID();
        this.items = new Hashtable<Item, BigDecimal>();
    }

    public void AddItemToCart(Item item, BigDecimal quantity) {
        if (this.items.containsKey(item)) {
            quantity = quantity.add(this.items.get(item));
            UpdateItemInCart(item, quantity);
        }
        else {
            this.items.put(item, quantity);
        }

    }

    public void RemoveItemFromCart(Item item) {
        this.items.remove(item);
    }

    public void UpdateItemInCart(Item item, BigDecimal quantity) {
        this.items.replace(item, quantity);
    }

    public BigDecimal GetItemQuantity(Item item) {
        return this.items.get(item);
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Item item : this.items.keySet()) {
            total = total.add(item.price().multiply(this.items.get(item)));
        }
        return total;
    }

    public Hashtable<Item, BigDecimal> getItems() {
        return this.items;
    }

    public void Checkout() {
    }

}

