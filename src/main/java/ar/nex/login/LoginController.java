package ar.nex.login;

import ar.nex.app.MainApp;
import ar.nex.entity.Usuario;
import ar.nex.service.JpaService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class LoginController implements Initializable {

    public LoginController() {
    }

    public Parent getRoot() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/login/Login.fxml"));
            root.setStyle("/fxml/login/Login.css");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }

    @FXML
    private BorderPane bpLogin;
    @FXML
    private Button btnIniciar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfig;
    @FXML
    private TextField boxUser;
    @FXML
    private PasswordField boxPass;
 
    private JpaService jpa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jpa = new JpaService();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boxUser.requestFocus();
            }
        });

        btnCancelar.setOnAction(e -> this.close(e));
        btnConfig.setOnAction(e -> config(e));
        btnIniciar.setOnAction(e -> login(e));
    }

    private void config(ActionEvent e) {
        try {
            Stage dialog = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/LoginConfig.fxml"));
            LoginConfigController controller = new LoginConfigController();
            loader.setController(controller);

            Scene scene = new Scene(loader.load());

            dialog.setScene(scene);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.resizableProperty().setValue(Boolean.FALSE);

            dialog.showAndWait();
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }

    private void login(ActionEvent e) {
        try {
            EntityManager em = jpa.getFactory().createEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username");
            query.setParameter("username", boxUser.getText());

            Usuario usuraio = (Usuario) query.getSingleResult();
            if (usuraio == null) {
                LoginUtils.errorDialog("Login Error.", "El Usuario NO exite!!!");
            } else if (usuraio.getPassword().compareTo(boxPass.getText()) != 0) {
                LoginUtils.errorDialog("Login Error.", "Contraseña Incorrecta!!!");
            } else {
                MainApp.getInstance().setUsuario(usuraio);
                this.close(e);
            }
        } catch (Exception ex) {
            LoginUtils.errorDialog("Login Error.", "El Usuario NO exite!!!");
        }

    }

    public void close(ActionEvent ev) {
        Stage s = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        s.close();
    }

}
