package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.User;

public class AdminStatistics extends Application {
    User currentUser;

    public AdminStatistics(User currentUser) {
        this.currentUser = currentUser;
    }


    @Override
    public void start(Stage stage) throws Exception {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Sales");
        /*Scene sc  = new Scene(series1, 500, 400);
        stage.setScene(sc);*/
        stage.show();
    }
}
