package ar.nex.app;

import ar.nex.login.LoginController;
import ar.nex.util.DateUtils;
import ar.nex.util.DialogController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("SAE-App");
            Parent root = new LoginController().getRoot();//FXMLLoader.load(getClass().getResource("/library/assistant/ui/login/login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            DialogController.showException(e);
        }
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
        Long startTime = System.currentTimeMillis();
        System.out.println("ar.nex.app.MainApp.main() launched on {} " + DateUtils.formatDateTimeString(startTime));
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
                System.out.println("ar.nex.app.MainApp.main() is closing on {} " + DateUtils.formatDateTimeString(exitTime));
            }
        });
    }
}
