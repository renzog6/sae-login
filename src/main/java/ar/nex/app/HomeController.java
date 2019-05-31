package ar.nex.app;

import ar.nex.usuario.UsuarioController;
import ar.nex.util.DialogController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
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
    @FXML
    private MenuButton mbUsuario;

    private Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("ar.nex.app.HomeController.initialize()");
        try {          
            initMenuUsuario();
        } catch (Exception e) {
            DialogController.showException(e);
        }
    }

    private void initMenuUsuario() {
        mbUsuario.getItems().get(0).setOnAction(e -> show(new UsuarioController().getRoot()));
//        mbEquipo.getItems().get(1).setOnAction(e -> show(new RepuestoController().getRoot()));
//        mbEquipo.getItems().get(2).setOnAction(e -> show(new PedidoController().getRoot()));
//        mbEquipo.getItems().get(3).setOnAction(e -> show(new RepuestoUsoController().getRoot()));
    }

    public void show(Parent root) {
        bpHome.getStylesheets().add(root.getStyle());
        bpHome.setCenter(root);
    }

}
