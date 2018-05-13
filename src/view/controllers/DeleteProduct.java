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

public class DeleteProduct {
    private FoodService foodService;

    public DeleteProduct() {
        this.foodService = new FoodServiceImpl(new DBCFoodDao(), new FileSystemDaoImpl());
    }

    @FXML
    public TextField productName;

    @FXML
    public Button exit;
    @FXML
    public Button ok;

    public void deleteDB(ActionEvent actionEvent) {
        foodService.deleteProductDBC(productName.getText());
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    public void exit(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
