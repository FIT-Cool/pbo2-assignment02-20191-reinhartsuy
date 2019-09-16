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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public Button btnUpdate;
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
    private Item selectedItems;

    /**
     *
     * @author Reinhart 1672072
     */

    @FXML
    private void saveAction(ActionEvent actionEvent) {
        if ((txtName.getText().isEmpty()) || (txtPrice.getText().equals("")) || (coboxCategory.getValue()==null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Diisi ya!");
            alert.showAndWait();
        }
        else{
            items.add(new Item(txtName.getText(),Integer.valueOf(txtPrice.getText()),coboxCategory.getValue()));
            resetAction(actionEvent);
        }
    }


    @FXML
    private void resetAction(ActionEvent actionEvent) {
        txtName.clear();
        txtPrice.clear();
        coboxCategory.setValue(null);
    }

    @FXML
    private void updateAction(ActionEvent actionEvent) {
    }

    @FXML
    private void saveCateAction(ActionEvent actionEvent) {
        if ((txtCateName.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Diisi ya!");
            alert.showAndWait();
        }
        else{

            categorys.add(new Category(txtCateName.getText()));

        }
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        selectedItems = tableDepartment.getSelectionModel().getSelectedItem();
        if(selectedItems!=null){
            txtName.setText(tableDepartment.getSelectionModel().getSelectedItem().getName());
            txtPrice.setText(tableDepartment.getSelectionModel().getSelectedItem().getPrice()+"");
            coboxCategory.setValue((Category)tableDepartment.getSelectionModel().getSelectedItem().getCategory());

            btnUpdate.setDisable(false);

        }

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
