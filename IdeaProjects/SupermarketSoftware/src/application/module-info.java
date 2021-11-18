module com.example.supermarketsoftware {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.supermarketsoftware to javafx.fxml;
    exports com.example.supermarketsoftware;
    opens reports to javafx.graphics;

}
