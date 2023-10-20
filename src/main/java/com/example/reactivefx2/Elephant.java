package com.example.reactivefx2;

import javafx.beans.property.SimpleStringProperty;

public class Elephant {
    SimpleStringProperty name ;

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public Elephant(String name) {
        this.name= new SimpleStringProperty(name);
        System.out.println("слон родился "+name);
    }
}
