package com.example.reactivefx2;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;

public class HelloController {
    ObservableList<Elephant> elephants = FXCollections.observableArrayList();

    @FXML
    TextField text1;

    @FXML
    private Button btnAdd;

    @FXML
    private TextArea log;

    @FXML
    GridPane grid;
    HashMap<Elephant, TextField> mapElText = new HashMap<>();
    //HashMap<Elephant, Integer> mapElNum= new HashMap<>();
    HashMap<Elephant, Button> mapElButton  = new HashMap<>();

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
                        pushElToGrid(x);
                        x.nameProperty().addListener((o, s, t1) ->
                                log.appendText("\n" + "переименован слон " + s+" ==> "+t1));
                    }
                }
                if(change.wasRemoved())
                    for (Elephant y: change.getRemoved() ) {
                        log.appendText("\n" + "удален слон " + y.getName());
                        removeElFromGrid(y);
                    }
            }
        });
    }

    private void removeElFromGrid(Elephant y) {
        TextField tfToDel = mapElText.get(y);
        Button   btnToDel = mapElButton.get(y);

        grid.getChildren().remove(tfToDel);
        grid.getChildren().remove(btnToDel);
        mapElText.remove(y);
        mapElButton.remove(y);
    }

    private void pushElToGrid(Elephant x) {
        int k = grid.getRowCount();
        TextField tf =new TextField(x.getName());
        Button bt=  new Button("del");
        grid.addRow(k, tf ,bt);
        x.nameProperty().bindBidirectional(tf.textProperty());
        bt.setOnAction(actionEvent -> elephants.remove(x));
        mapElText.put(x, tf);
        //int i = elephants.indexOf(x);
        mapElButton.put(x, bt);

    }

    @FXML
    private void add4BtnClk() {
        String txt = text1.getText();
        ArrayList<Elephant> tmp= new ArrayList<>();
        for (int i=0; i<4; i++) {
            Elephant e = new Elephant(txt+(i+1));
            tmp.add(e);
        }
        elephants.addAll(tmp);
    }
}