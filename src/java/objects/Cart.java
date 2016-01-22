/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 * Cart object used temporarily before the order is submitted to the database.
 * @author Aziz
 */
public class Cart {
    
    private ArrayList<DishOrd> dishes;
    private double total;
    
    /**
     * Creates a new cart which is simply an ArrayList of dishes.
     */
    public Cart(){
        dishes = new ArrayList<DishOrd>();
    }

    /**
     * Sends the subtotal of the cart before taxes to the user.
     * @return the subtotal of the items in the cart
     */
    public double getTotal() {
        total = Math.round(total*100)/100.00;
        return total;
    }
    
    /**
     * Used to set the total in the cart.
     * @param total current total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Returns list of dishes to the user
     * @return ArraList of dishes
     */
    public ArrayList<DishOrd> getDishes() {
        return dishes;
    }

    /**
     * Sets the dishes currently in the cart.
     * @param dishes list of current dishes
     */
    public void setDishes(ArrayList<DishOrd> dishes) {
        this.dishes = dishes;
    }
    
    /**
     * Adds a dish to the cart.
     * @param d dish to be added
     */
    public void addDish(DishOrd d){
        dishes.add(d);
    }
    
    /**
     * Adds price of dish to the current subtotal.
     * @param p price of dish.
     */
    public void addTotal(double p){
        total += p;
    }
    
    /**
     * Removes the price of dish from the total.
     * @param p price of dish that is to be removed.
     */
    public void removeTotal(double p){
        total -= p;
    }
    
}
