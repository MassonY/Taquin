package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private GameController controller;

    public Main() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initRootLayout();
        launchGame(rootLayout, this, BoardFactory.getNewBoard(5, 4));
    }

    private void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/root.fxml")));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launchGame(BorderPane rootLayout, Main main, Board board){
        this.primaryStage.setTitle("Jeu du Taquin");
        afficherContenu(rootLayout, main, "game.fxml", board);
    }


    private void afficherContenu(BorderPane rootLayout, Main main, String root, Board board){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/"+root)));
            AnchorPane contenu = loader.load();

            controller = loader.getController();
            controller.setMain(main, board);

            rootLayout.setCenter(contenu);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        controller.stop();
    }

    public void startGame(Board board) {
        launchGame(rootLayout, this, board);
    }
}
