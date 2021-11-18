package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    Label username = new Label("Username");
    TextField usernameTf = new TextField();
    Label password = new Label("Password");
    TextField passwordTf = new TextField();
    Button login = new Button("Login");
    Button cancel = new Button("Cancel");
    login.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String username = usernameTf.getText();
            String password = passwordTf.getText();
        }
    });
        BorderPane pane = new BorderPane();
        HBox hb = new HBox();
        hb.getChildren().addAll(login,cancel);
        VBox vb = new VBox();
        pane.getChildren().addAll(hb,vb);
        Scene sc = new Scene(pane,400,400);
        stage.setScene(sc);
        stage.show();
    }
}
