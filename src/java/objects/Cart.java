/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 *
 * @author Aziz
 */
public class Cart {
    
    private ArrayList<DishOrd> dishes;
    private double total;
    
    public Cart(){
        dishes = new ArrayList<DishOrd>();
    }

    public double getTotal() {
        total = Math.round(total*100)/100.00;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<DishOrd> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<DishOrd> dishes) {
        this.dishes = dishes;
    }
    
    public void addDish(DishOrd d){
        dishes.add(d);
    }
    
    public void addTotal(double p){
        total += p;
    }
    
    public void removeTotal(double p){
        total -= p;
    }
    
}
