package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/main-view.fxml")
        );

        Scene scene =  new Scene(loader.load());
        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm()
        );
        stage.setTitle("Calculadora FX");

        stage.getIcons().add(
                new javafx.scene.image.Image(
                        getClass().getResource("/icons/favicon-32x32.png").toExternalForm()
                )
        );

        stage.initStyle(StageStyle.UNDECORATED); // esto quita la barra de control de arriba. dejandome luego crear una personalizada en javafx
        stage.setScene(scene);

        controller.MainController controller = loader.getController();
        controller.init(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
