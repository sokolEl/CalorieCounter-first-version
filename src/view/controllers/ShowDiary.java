package view.controllers;

import dao.impl.DBCFoodDao;
import dao.impl.FileSystemDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Food;
import services.FoodService;
import services.impl.FoodServiceImpl;

public class ShowDiary {

    private FoodService foodService;

    public ShowDiary() {
        this.foodService = new FoodServiceImpl(new DBCFoodDao(), new FileSystemDaoImpl());

    }

    @FXML
    public Button exit;
    @FXML
    public Button ok;

    public void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TableView<Food> table;

    @FXML
    private TableColumn<Food, String> nameProduct;

    @FXML
    private TableColumn<Food, Integer> calories;


    @FXML
    public void initialize() {
        nameProduct.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        calories.setCellValueFactory(new PropertyValueFactory<>("cal"));

        ObservableList<Food> obList = FXCollections.observableList(foodService.showDiaryFoods());

        table.setItems(obList);
    }
}
