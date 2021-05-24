package GestionProduit;

import GestionProduit.ProdListHandler;
import GestionProduit.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.time.LocalDate;

public class ProduitsListWindow {
ProdListHandler handler= new ProdListHandler(this);
Stage window = new Stage();
VBox root =new VBox();
Scene scene = new Scene(root);
TableView<Produit> prodTableView = new TableView<>();
Label titleLabel= new Label("Liste des produits");
HBox totalHbox= new HBox();
Label totalLabel = new Label("total");
Label totalLabelVal  = new Label("0.0");
HBox ButtonsBox = new HBox();

TableColumn<Produit, Long> Idcolumn = new TableColumn<>("Id");
TableColumn<Produit, String> Designcolumn = new TableColumn<>("Designation");
TableColumn<Produit, Integer> Qtecolumn = new TableColumn<>("Qte");
TableColumn<Produit, Double> Prixcolumn= new TableColumn<>("Prix");
TableColumn<Produit, Double> Totalcolumn= new TableColumn<>("Total");
TableColumn<Produit, LocalDate> Datecolumn = new TableColumn<>("Date");
ObservableList<Produit> produitObservableList= FXCollections.observableArrayList();

    Button ProdUpdateButton = new Button("Modifier");
    Button ProdDeleteButton  =new Button("Supprimer");
private  void addColumnsToTableView(){
    prodTableView.getColumns().addAll(Idcolumn,Designcolumn,Qtecolumn,Prixcolumn,Totalcolumn,Datecolumn);
    prodTableView.setItems(produitObservableList);

}

private void addStylesToNodes(){
    scene.getStylesheets().add("css/styles.css");
  titleLabel.getStyleClass().add("labelTitle");
  titleLabel.setAlignment(Pos.CENTER);
  totalLabel.getStyleClass().add("labelTotal");
  totalLabelVal.getStyleClass().add("labelTotal");
  totalHbox.getStyleClass().add("boxTotal");
  prodTableView.getStyleClass().add("table-row-cell");
  prodTableView.setMinHeight(500);
  titleLabel.setMinWidth(window.getWidth());
  totalHbox.setSpacing(15);


}
private void addButtonToWindow(){
    ButtonsBox.getChildren().addAll(ProdUpdateButton, ProdDeleteButton);
    root.getChildren().add(ButtonsBox);
    ButtonsBox.setSpacing(25);
    ButtonsBox.setPadding(new Insets(20));
}
private void  updateColumns(){
    Idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    Idcolumn.setPrefWidth(100);
    Designcolumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
    Designcolumn.setPrefWidth(250);
    Qtecolumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
    Qtecolumn.setPrefWidth(150);
    Prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
    Prixcolumn.setPrefWidth(150);
    Datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    Datecolumn.setPrefWidth(200);
    Totalcolumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    Totalcolumn.setPrefWidth(150);
}
private void initWindow(){
window.setWidth(1000);
window.setHeight(700);
window.setScene(scene);
    window.setTitle("Liste des Produits");
    //window.getIcons().add(new Image(""));
    window.initModality(Modality.APPLICATION_MODAL);

}
private void addNodeToPane(){
    totalHbox.getChildren().addAll(totalLabel, totalLabelVal);
    root.getChildren().addAll(titleLabel, prodTableView, totalHbox);
  
}
    private void addEvents(){

        ProdDeleteButton.setOnAction(event -> {
            handler.deleteProduit();
        });
        prodTableView.setOnMouseClicked( event -> {
            prodTableView.setEditable(true);
            Designcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        });
        ProdUpdateButton.setOnAction( event ->{
            handler.updateProduit();
        });


    }

public  ProduitsListWindow(){
    initWindow();
    addStylesToNodes();
    updateColumns();
    addColumnsToTableView();
    handler.updateProdListWindow();
    addNodeToPane();
    addButtonToWindow();
    addEvents();
    window.show();
}}
