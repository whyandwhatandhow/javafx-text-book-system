module com.example.textbooksystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.textbooksystem to java.xml;
    exports com.example.textbooksystem;
    exports com.example.textbooksystem.Controller;
    opens com.example.textbooksystem.Controller to javafx.fxml;
    opens com.example.textbooksystem.Domain to javafx.base;
}