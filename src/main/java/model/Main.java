package model;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    /**
     * Primary Stage utilisé
     */
    private Stage primaryStage;

    /**
     * BorderPane utilisé
     */
    private BorderPane rootLayout;

    /**
     * Controlleur associé à la connexion
     */
    private Controller controller;

    /**
     * Constructeur par défaut
     */
    public Main() {
        //Nothing here
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Fenêtre settings");

        initRootLayout();

        launchSettings(rootLayout, this);
    }

    /**
     * Initialise notre fenêtre avec le BorderPane
     * correspondant au fond de notre affichage
     */
    private void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("root.fxml")));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance le jeu
     * @param rootLayout
     * @param main
     * @param board
     */
    private void launchGame(BorderPane rootLayout, Main main, Board board){
        this.primaryStage.setTitle("Jeu du Taquin");
        afficherContenu(rootLayout, main, "game.fxml", board);
    }

    /**
     * Lance les settings
     * @param rootLayout
     * @param main
     */
    private void launchSettings(BorderPane rootLayout, Main main) {
        afficherContenu(rootLayout, main, "settings.fxml", null);
    }

    /**
     * Affiche une vue
     * @param rootLayout
     * @param main
     * @param root
     * @param board
     */
    private void afficherContenu(BorderPane rootLayout, Main main, String root, Board board){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource(root)));
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


    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Redéfintion de la fonction stop appelée lors du clic sur la croix rouge
     */
    @Override
    public void stop(){
        controller.stop();
    }

    /**
     * Changement de vue
     * @param board
     */
    public void startGame(Board board) {
        launchGame(rootLayout, this, board);
    }
}
