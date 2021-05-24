package GestionClients;
import GestionVentes.FormVenteWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
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

public class ClientListWindow {
    ClientListHandler handler= new ClientListHandler(this);

    Stage window = new Stage();
    VBox root =new VBox();
    Scene scene = new Scene(root);
    TableView<Client> clientTableView = new TableView<>();
    Label titleLabel= new Label("Liste des clients");
    HBox ButtonsBox = new HBox();


    TableColumn<Client, Long> Idcolumn = new TableColumn<>("Id");
    TableColumn<Client, String>     Nomcolumn = new TableColumn<>("Nom");
    TableColumn<Client, Integer> Prenomcolumn = new TableColumn<>("Prenom");
    TableColumn<Client, Double> Telephonecolumn= new TableColumn<>("Telephone");
    TableColumn<Client, Double> Adressecolumn= new TableColumn<>("Adresse");
    ObservableList<Client> clientObservableList= FXCollections.observableArrayList();

    Button ClientUpdateButton = new Button("Modifier");
    Button ClientDeleteButton  =new Button("Supprimer");
    Button NouvelleVente = new Button("Nouvelle vente");
    private  void addColumnsToTableView(){
        clientTableView.getColumns().addAll(Idcolumn,Nomcolumn, Prenomcolumn, Telephonecolumn,Adressecolumn);
        clientTableView.setItems(clientObservableList);
    }

    private void addStylesToNodes(){
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setAlignment(Pos.CENTER);
        clientTableView.getStyleClass().add("table-row-cell");
        clientTableView.setMinHeight(500);
        titleLabel.setMinWidth(window.getWidth());



    }
    private void addButtonToWindow(){
        ButtonsBox.getChildren().addAll(ClientUpdateButton,ClientDeleteButton , NouvelleVente);
        root.getChildren().add(ButtonsBox);
        ButtonsBox.setSpacing(25);
        ButtonsBox.setPadding(new Insets(20));
    }
    private void  updateColumns(){
        Idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        Idcolumn.setPrefWidth(100);
        Nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Nomcolumn.setPrefWidth(250);
        Prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Prenomcolumn.setPrefWidth(150);
        Telephonecolumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        Telephonecolumn.setPrefWidth(150);
        Adressecolumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Adressecolumn.setPrefWidth(200);
    }
    private void initWindow(){
        window.setWidth(1000);
        window.setHeight(700);
        window.setScene(scene);

    }
    private void addNodeToPane(){
        root.getChildren().addAll(titleLabel,clientTableView);

    }
    private void addEvents() {
            ClientDeleteButton.setOnAction(event -> {
                handler.deleteClient();
            });
/*
            clientTableView.setOnMouseClicked( event -> {
                clientTableView.setEditable(true);
            Nomcolumn.setCellFactory(TextFieldTableCell.forTableColumn());

        });*/





        }

    public  ClientListWindow(){
        initWindow();
        addStylesToNodes();
        updateColumns();
        addColumnsToTableView();
        handler.updateClientListWindow();
        addNodeToPane();
        addButtonToWindow();
        addEvents();
        window.show();
    }}

