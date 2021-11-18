package view;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

public class CashierView extends Application {
    User currentUser;

    public CashierView(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Button add = new Button("Add");
        Button cancel = new Button(" Cancel");
    }
}
