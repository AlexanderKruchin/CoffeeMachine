package com.machine.coffeemachine.model;


public class CoffeeCount {

    private Integer milk = 300; //start millilitres
    private Integer coffee = 50; // start milligram
    private Integer water= 700; //start millilitres

    public Integer getMilk() {
        return milk;
    }

    public void setMilk(Integer milk) {
        this.milk = milk;
    }

    public Integer getCoffee() {
        return coffee;
    }

    public void setCoffee(Integer coffee) {
        this.coffee = coffee;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }



}
