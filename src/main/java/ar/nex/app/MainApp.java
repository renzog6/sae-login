package ar.nex.app;

import ar.nex.entity.Usuario;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    private Usuario usuario;

    private static MainApp instance;

    public static MainApp getInstance() {
        return instance;
    }

    private Parent root;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        try {
            this.usuario = null;
            instance = this;
            this.stage = stage;
            
            stage.setTitle("App by HellNeX");
            showHome();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showHome() {
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));

            scene = new Scene(root);
            scene.getStylesheets().add("/fxml/Home.css");
            stage.setScene(scene);
            stage.setMaximized(true);
            //stage.setOnCloseRequest();
            stage.show();
        } catch (Exception e) {
        }
    }
    
    public void close(ActionEvent ev){
        Stage s =(Stage) ((Node) ev.getSource()).getScene().getWindow();
        s.close();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogin() {
        return usuario != null;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
