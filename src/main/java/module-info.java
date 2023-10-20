module com.example.reactivefx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.reactivefx2 to javafx.fxml;
    exports com.example.reactivefx2;
}