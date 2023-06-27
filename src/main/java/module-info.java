module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.prefs;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
    exports com.example.demo2.models to  org.mongodb.bson;
    exports com.example.demo2.view to javafx.fxml;
    opens com.example.demo2.view  to javafx.fxml;
}