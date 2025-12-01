module calculadorafx {
    requires javafx.controls;
    requires javafx.fxml;

    exports app;                 // donde está App.java (punto de entrada)
    opens controller to javafx.fxml; // para que FXML pueda usar el MainController
}
