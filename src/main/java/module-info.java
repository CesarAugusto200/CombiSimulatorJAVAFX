module com.example.combissimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.combissimulator to javafx.fxml;
    exports com.example.combissimulator;
    exports com.example.combissimulator.Controller;
    opens com.example.combissimulator.Controller to javafx.fxml;
    exports com.example.combissimulator.Scenes;
    opens com.example.combissimulator.Scenes to javafx.fxml;
}