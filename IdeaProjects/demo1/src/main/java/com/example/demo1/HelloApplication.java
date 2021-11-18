package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        int temp, size;
        int a[] = {7, 5, 2, 1, 4, 8};
        size = a.length;
        int min = a[0], max = a[0], secondMin;
        for(int i = 0; i < size; i++){
            if(a[i] < min){
                min = a[i];
            }
            if(a[i] > max){
                max = a[i];
            }
        }
    secondMin = max;
        for(int i = 0; i < size; i++){
            if((a[i] < secondMin) && (a[i] != min)){
                secondMin = a[i];
            }
        }
        System.out.println(secondMin);
    }

    String chesarCipher(String s1, int n) {
        r
    }
}