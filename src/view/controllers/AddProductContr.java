package view.controllers;

import dao.impl.DBCFoodDao;
import dao.impl.FileSystemDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.FoodService;
import services.impl.FoodServiceImpl;

public class AddProductContr {
    private FoodService foodService;

    public AddProductContr() {
        this.foodService = new FoodServiceImpl(new DBCFoodDao(), new FileSystemDaoImpl());
    }

    @FXML
    public TextField productName;
    @FXML
    public TextField calories;

    @FXML
    public Button exit;
    @FXML
    public Button ok;

    public void addToDB(ActionEvent actionEvent) {
        foodService.addProductDBC(productName.getText(), new Integer(calories.getText()));
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    public void exit(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }




}
