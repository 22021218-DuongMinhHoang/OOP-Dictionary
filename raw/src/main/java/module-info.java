module com.example.hello {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.json;
    requires controlsfx;

    opens com.example.hello to javafx.fxml;
    opens com.example.hello.data to javafx.fxml;
    exports com.example.hello;
    exports com.example.hello.data;
}