package com.reinhart.controller;

import com.reinhart.entity.Category;
import com.reinhart.entity.Item;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private ChoiceBox<Category> coboxCategory;
    @FXML
    private TextField txtCateName;
    @FXML
    private TableView<Item> tableDepartment;
    @FXML
    private TableColumn<Item, String> col01;
    @FXML
    private TableColumn<Item, Double> col02;
    @FXML
    private TableColumn<Item, String > col03;

    private ObservableList<Category> categorys ;
    private ObservableList<Item> items ;

    /**
     *
     * @author Reinhart 1672072
     */

    @FXML
    private void saveAction(ActionEvent actionEvent) {

    }

    @FXML
    private void resetAction(ActionEvent actionEvent) {
    }

    @FXML
    private void updateAction(ActionEvent actionEvent) {
    }

    @FXML
    private void saveCateAction(ActionEvent actionEvent) {
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categorys = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
        coboxCategory.setItems(categorys);
        tableDepartment.setItems(items);
        col01.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getName());
        });
        col02.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleDoubleProperty(i.getPrice()).asObject();
        });
        col03.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getCategory().getName());
        });
    }
}
