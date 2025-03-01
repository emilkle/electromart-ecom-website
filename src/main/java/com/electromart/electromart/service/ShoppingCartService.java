package com.electromart.electromart.service;

import com.electromart.electromart.entity.CartItem;
import com.electromart.electromart.entity.Product;
import com.electromart.electromart.repository.InventoryRepository;

import java.util.ArrayList;


public class ShoppingCartService {

//
    private final ArrayList<CartItem> shoppingCart = new ArrayList<>();

    // assign to inventory service?
    //private final InventoryService is = new InventoryService();

    //REFACTORED
    // Not sure if its best practice to instantiate a new InventoryService outside a controller class.
    private final InventoryService inventoryService;

    public ShoppingCartService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //Need to implement updateQuantityCart, check item in shoppingCart

    /**
     *
     * @param product Product to add to shopping cart
     * @param amount amount of product to add to shopping cart
     *
     */

    public boolean addProductToCart(Product product, int amount) {
        boolean addToCart =  false;
        long productID = product.getProductId();
        try {
            //Check if productID, amount is valid
            if (inventoryService.checkInventoryQuantity(productID, amount)){
                //Check if item is in cart
                if (isProductInCart(product)){
                    int indexArray = 0;
                    for (int index = 0; index < shoppingCart.size(); index++) {
                        if (shoppingCart.get(index).getCartItemID() == product.getProductId()) {
                            indexArray = index;
                        }
                    this.shoppingCart.get(indexArray).setCartAmount(amount);
                    addToCart = true;
                    }

                }
                else    {
                    CartItem cartItem = new CartItem(product,amount);
                    shoppingCart.add(cartItem);
                    addToCart = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Wrong amount added to cart.");
        }
        return addToCart;

    }

    /**
     * Removes product from cart
     * @param product Assumes valid product for now
     * @return boolean if removal is performed or not performed.
     */
    public boolean removeProductFromCart(Product product) {
        boolean removedFromCart = false;
        if (isProductInCart(product)) {
            int indexArray = 0;
            for (int i = 0; i < shoppingCart.size(); i++) {
                if (shoppingCart.get(i).getCartItemID() == product.getProductId()) {
                    indexArray = i;
                }
            }
            this.shoppingCart.remove(indexArray);
            removedFromCart = true;
        }
        return  removedFromCart;
    }

    /**
     * Calculates total price of products in cart
     * @return float total price of products in cart
     */
    public float cartTotalPrice() {
        float totalAmount = 0;
        if (!shoppingCart.isEmpty()) {
            for (CartItem cartItem : shoppingCart) {
                totalAmount += cartItem.getCartItemPrice();
            }
        }
        return totalAmount;
    }

    /**
     *  empties shoppingcart
     * @return boolean to communicate empty check complete
     */
    public boolean emptyShoppingCart() {
        if (!shoppingCart.isEmpty()) {
            shoppingCart.clear();
        }
        return true;

    }

    private boolean isProductInCart(Product product) {
        return  shoppingCart.stream().anyMatch(e -> e.getCartItemID() == product.getProductId());
    }

}
