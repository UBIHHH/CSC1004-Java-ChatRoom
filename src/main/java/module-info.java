module Client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires ibatis.sqlmap;
    opens client.Controller to javafx.fxml;
    exports client.Controller to javafx.fxml;
    exports client to javafx.graphics;

}