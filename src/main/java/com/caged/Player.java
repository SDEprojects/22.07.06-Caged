package com.caged;

import java.util.List;

class Player {

    private String name;
    private String currentLocation;
    private int HitPoints;
    private List<String> Inventory;

    public Player(String name,String currentLocation, int HitPoints, List<String> Inventory){
        setInventory(Inventory);
        setHitPoints(HitPoints);
        setCurrentLocation(currentLocation);
        setName(name);
    }

    public Player(){

    }

    //functions
    public void playerActions(String verb, String noun){
        switch (verb) {
            case "go":
                move(noun);
                System.out.println("Moving "+ noun +"!");
                break;
            case "take":
                take(noun);
                System.out.println("Taking "+ noun +"!");
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    public void move(String direction){
        System.out.println("Player moves " + direction);
    }

    public void take(String item){
        System.out.println("Player takes " + item);
    }

    //getter & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getHitPoints() {
        return HitPoints;
    }

    public void setHitPoints(int hitPoints) {
        HitPoints = hitPoints;
    }

    public List<String> getInventory() {
        return Inventory;
    }

    public void setInventory(List<String> inventory) {
        Inventory = inventory;
    }
}