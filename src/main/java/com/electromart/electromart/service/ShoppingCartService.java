package com.electromart.electromart.service;

import com.electromart.electromart.entity.CartItem;
import com.electromart.electromart.entity.Product;

import java.util.ArrayList;

public class ShoppingCartService {


    private final ArrayList<CartItem> shoppingCart = new ArrayList<>();

    // assign to inventory service?
    private InventoryService is;

    public ShoppingCartService () {

    }
    //Need to implement updateQuantityCart, check item in shoppingCart

    /**
     *
     * @param product Product to add to shopping cart
     * @param amount amount of product to add to shopping cart
     */

    public boolean addProductToCart(Product product, int amount) {
        boolean addToCart =  false;
        long productID = product.getProductId();
        try {
            if (is.checkInventoryQuantity(productID, amount)){
                CartItem cartItem = new CartItem(product,amount);
                shoppingCart.add(cartItem);
                addToCart = true;
            }
        } catch (Exception e) {
            System.out.println("Wrong amount added to cart.");
        }
        return addToCart;


    }


}
