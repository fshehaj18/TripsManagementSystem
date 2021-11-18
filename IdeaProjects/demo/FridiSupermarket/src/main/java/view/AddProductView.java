package view;

import RW.RWCategory;
import RW.RWProduct;
import RW.RWSuppliers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DateFormat;
import model.Product;
import model.User;

public class AddProductView extends Application {
    User currentUser;

    public AddProductView(User currentUser) {
        this.currentUser = currentUser;
    }



    @Override
    public void start(Stage stage) throws Exception {

        RWSuppliers rws = new RWSuppliers();
        RWCategory rwc = new RWCategory();

        Label barcode = new Label("Barcode");
        TextField barcodeTf = new TextField();
        Label quantity = new Label("Quantity");
        TextField quantityTf = new TextField();
        Label name = new Label("Name");
        TextField nameTf = new TextField();
        Label supplier = new Label("Supplier");
        ChoiceBox supplierChoice = new ChoiceBox();
        supplierChoice.setItems(FXCollections.observableList(rws.getSupplierNames()));
        Label category = new Label("Category");
        ChoiceBox categoryChoice = new ChoiceBox();
        categoryChoice.setItems(FXCollections.observableList(rwc.getCategoriesNames()));
        Label expiry = new Label("Expiry Date");
        DatePicker expiryTf = new DatePicker();
        Label sellPrice = new Label("Sell Price");
        TextField sellPriceTf = new TextField();
        Label boughtPrice = new Label("Bought Price");
        TextField boughtPriceTf = new TextField();

        GridPane gp = new GridPane();
        gp.addColumn(0,barcode,quantity,name,supplier,category,expiry,sellPrice,boughtPrice);
        gp.addColumn(1,barcodeTf,quantityTf,nameTf,supplierChoice,categoryChoice,expiryTf,sellPriceTf,boughtPriceTf);

        Button add = new Button("Add product");
        Button back = new Button("Back");

        HBox hb = new HBox();
        hb.getChildren().addAll(add,back);
        hb.setSpacing(10);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RWProduct rwp = new RWProduct();
                int day = expiryTf.getValue().getDayOfMonth();
                int month = expiryTf.getValue().getMonthValue();
                int year = expiryTf.getValue().getYear();
                String date = String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
                DateFormat expiryDate = new DateFormat(date);
                Product p = new Product(barcodeTf.getText(),nameTf.getText(),categoryChoice.getValue().toString(),supplierChoice.getValue().toString(),
                        Double.parseDouble(boughtPriceTf.getText()),expiryDate, Double.parseDouble(boughtPriceTf.getText()),Integer.parseInt(quantityTf.getText()));
                rwp.add(p);
                try {
                    new ViewProducts(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new ViewProducts(currentUser).start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(gp,hb);
        vb.setPadding(new Insets(10,10,10,10));
        Scene sc = new Scene(vb,500,500);
        stage.setScene(sc);
        sc.getStylesheets().add("stylesheet.css");
        stage.setTitle("Add Product");
        stage.show();
    }
}
