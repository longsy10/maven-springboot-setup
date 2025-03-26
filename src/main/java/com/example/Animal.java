package com.example;

public class Animal {
    private String name;
    private int age;
    private String species;

    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;

        // Automatically save to database
        DatabaseManager.insertAnimal(name, age, species);
    }

    // Optional: Getters (if needed later)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }
}
