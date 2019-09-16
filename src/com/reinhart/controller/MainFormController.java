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
    public Button btnSave;
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
    private ObservableList<Item> Item ;
    private Item selectedItems;
    int hitung;

    /**
     *
     * @author Reinhart 1672072
     */

    @FXML
    private void saveAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if ((txtName.getText().isEmpty()) || (txtPrice.getText().equals("")) || (coboxCategory.getValue()==null)) {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Diisi ya!");
            alert.showAndWait();
        }
        else{
            Item i = new Item();
            i.setName(txtName.getText());
            hitung = (int) Item.stream().filter(p -> p.getName().equalsIgnoreCase(txtName.getText())).count();
            if (hitung > 0 ){
                alert.setTitle("Error");
                alert.setContentText("Duplicate item name");
                alert.show();
            } else {
                i.setName(txtName.getText().trim());
                i.setPrice((txtPrice.getText().trim()));
                i.setCategory(coboxCategory.getValue());
                Item.add(i);
            }




        }
    }


    @FXML
    private void resetAction(ActionEvent actionEvent) {
        txtName.clear();
        txtPrice.clear();
        txtCateName.clear();
        coboxCategory.setValue(null);
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }

    @FXML
    private void updateAction(ActionEvent actionEvent) {
        Item i = tableDepartment.getSelectionModel().getSelectedItem();
        i.setName(txtName.getText());
        i.setPrice(txtPrice.getText());
        i.setCategory(coboxCategory.getValue());
        tableDepartment.refresh();
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);

    }

    @FXML
    private void saveCateAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if ((txtCateName.getText().equals(""))) {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Diisi ya!");
            alert.showAndWait();
        }
        else{
            Category c = new Category();
            c.setName(txtCateName.getText());
            hitung = (int) categorys.stream().filter(p -> p.getName().equalsIgnoreCase(txtCateName.getText())).count();
            if (hitung > 0){
                alert.setTitle("Error");
                alert.setContentText("Duplicate");
                alert.show();
            }
            else {
                categorys.add(c);
                txtCateName.clear();
            }


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
        Item = FXCollections.observableArrayList();
        coboxCategory.setItems(categorys);
        tableDepartment.setItems(Item);
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
