package sample;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Martynas Valatka PS 1 kursas 4 gr. 2 pogrupis
 * Būsto paskolos skaičiuoklė
 */

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Būsto Paskola");

        Image icon = new Image("House contract.png");
        window.getIcons().add(icon);

        Scenes homeScene = new Scenes();
        window.setScene(homeScene.getHomeScene());
        window.show();
    }

}
