module com.example.tpbda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.tpbda to javafx.fxml;
    exports com.example.tpbda;
}