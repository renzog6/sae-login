package ar.nex.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class HomeController implements Initializable {

    public HomeController() {
    }

    @FXML
    private BorderPane bpHome;

    private Parent root;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             root = MainApp.getInstance().getRoot();
        showLogin(new Stage());

        if (!MainApp.getInstance().isLogin()) {            
            close(new ActionEvent());
        }
    }

    private void showLogin(Stage stage) {
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/login/Login.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/fxml//login/Login.css");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
        }
    }

    @FXML
    private void goPedido(MouseEvent event) {
        loadUI("pedido/Pedido");
    }

    @FXML
    private void goRepuesto(MouseEvent event) {
        loadUI("repuesto/Repuesto");
    }

    @FXML
    private void goEquipo(MouseEvent event) {
        loadUI("equipo/Equipo");
    }

    public void loadUI(String ui) {
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/" + ui + ".fxml"));
            bpHome.getStylesheets().add("/fxml/" + ui + ".css");
            bpHome.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void close(ActionEvent e) {
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }
}
