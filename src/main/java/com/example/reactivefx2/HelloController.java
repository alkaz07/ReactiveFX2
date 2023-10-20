package com.example.reactivefx2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    ObservableList<Elephant> elephants = FXCollections.observableArrayList();

    @FXML
    TextField text1;

    @FXML
    private Button btnAdd;

    @FXML
    private TextArea log;

    @FXML
    private void addBtnClk() {
        String txt = text1.getText();
        Elephant e = new Elephant(txt);
        elephants.add(e);
    }

    public void initialize() {
        elephants.addListener((ListChangeListener<Elephant>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Elephant x : change.getAddedSubList()) {
                        log.appendText("\n" + "добавлен слон " + x.getName());
                    }
                }
                if(change.wasRemoved())
                    for (Elephant y: change.getRemoved() ) {
                        log.appendText("\n" + "удален слон " + y.getName());
                    }
            }
        });
    }

    @FXML
    private void add4BtnClk() {
        String txt = text1.getText();
        for (int i=0; i<4; i++) {
            Elephant e = new Elephant(txt+(i+1));
            elephants.add(e);
        }
    }
}