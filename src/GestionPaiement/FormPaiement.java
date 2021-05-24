package GestionPaiement;

import GestionProduit.Produit;
import GestionVentes.IVenteDao;
import GestionVentes.VenteDaoImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.util.*;

import java.util.ArrayList;
import java.util.List;

public class FormPaiement {

    IPaiementDao paimdao=new PaiementDaoImp();
    IVenteDao venteDao = new VenteDaoImpl();
    long idVente=venteDao.getLastId() ;
    List<Paiement> paiementList= new ArrayList<>();
    BorderPane root = new BorderPane();
    Scene scene =new Scene( root);
    Stage window = new Stage();

    double total=0.0,resteMontant=0.0;
    Label titleLabel= new Label("Detail vente");
    Label titleLabel2= new Label("Paiements");
    Label titleLabel3= new Label("Detail paiement");
    Label id_vente = new Label(" Id Vente");
    Label idVenteTextField = new Label();
    Label dateVente = new Label(" Date");
    Label dateVentETextField = new Label();
    Label totalVente = new Label("Total Vente");
    Label totalVenteTextField= new Label();
    Label totalPaye=new Label("Total Paye: ");
    Label totalPayeValue=new Label(this.total +"DH");
    Label reste = new Label("Reste: ");
    Label resteTextField = new Label();


    VBox vBoxLeft = new VBox();
    HBox hboxLabel1= new HBox();
    HBox hboxLabel2= new HBox();
    HBox hboxLabel3= new HBox();
    HBox hboxLabel4= new HBox();
    HBox hboxLabel5= new HBox();
    VBox vboxDetailVente= new VBox();
    VBox vboxPaiements= new VBox();
    VBox vboxDetailPaiement= new VBox();
    Label datePaiement = new Label(" Date");
    DatePicker  datePaimSaisie = new DatePicker();
    Label montantPaim = new Label("Montant");
    TextField montantPaimTextField= new TextField();
    Button SaveButton = new Button("Enregistrer");
    HBox hboxButton= new HBox();
    VBox vBoxCenter = new VBox();
    HBox hboxCenter1= new HBox();
    HBox hboxCenter2= new HBox();
    ComboBox<Type>types=new ComboBox<Type>();
    Label typePaim= new Label("Type");
    HBox hboxCenter3 = new HBox();
    //--------------------------------------------------------tablePaiements
    TableView<Paiement> tablePaiement=new TableView<Paiement>();
    TableColumn<Paiement, Integer> numcol = new TableColumn<Paiement, Integer>("Id");
    TableColumn<Paiement, Double> montantcol = new TableColumn<Paiement, Double>("Montant");
    TableColumn<Paiement, String> datecol = new TableColumn<Paiement, String>("Date");
    TableColumn<Paiement, String> typecol = new TableColumn<Paiement, String>("Type");
    ObservableList<Paiement> paimentsObservableList= FXCollections.observableArrayList();


   private void addDetailVente(){

       idVenteTextField.setText(String.valueOf(idVente));
       dateVentETextField.setText(venteDao.find(idVente).getDate());
       totalVenteTextField.setText(String.valueOf(venteDao.find(idVente).getTotal()));
   }




    private void addEvents() {

       SaveButton.setOnAction(event -> {

           Paiement paim= null;
           Type t= types.getSelectionModel().getSelectedItem();
           double montant= Double.parseDouble(montantPaimTextField.getText());
           paim = new Paiement(venteDao.find(idVente), montant, t);
           paimdao.create(paim);
           tablePaiement.getItems().add(paim);
           double rest = Double.parseDouble(totalVenteTextField.getText())- Double.parseDouble(montantPaimTextField.getText());
           resteTextField.setText(String.valueOf(rest));
       });


    }

    private void addNodesToWindow() {
        hboxLabel1.getChildren().addAll(id_vente, idVenteTextField);
        hboxLabel2.getChildren().addAll(dateVente,dateVentETextField);
        hboxLabel3.getChildren().addAll(totalVente,totalVenteTextField);
        hboxLabel4.getChildren().addAll(totalPaye,totalPayeValue);
        hboxLabel5.getChildren().addAll(reste,resteTextField);
        vboxDetailVente.getChildren().addAll(hboxLabel1,hboxLabel2,hboxLabel3,hboxLabel4,hboxLabel5);
        vboxPaiements.getChildren().addAll(titleLabel2,tablePaiement);
        vBoxLeft.getChildren().addAll(titleLabel,vboxDetailVente,vboxPaiements);
        root.setLeft(vBoxLeft);

        hboxCenter1.getChildren().addAll(datePaiement,datePaimSaisie);
        hboxCenter2.getChildren().addAll(montantPaim,montantPaimTextField);
        types.getItems().addAll(paimdao.getAllType());
        hboxCenter3.getChildren().addAll(typePaim,types);
        hboxButton.getChildren().add(SaveButton);
        vboxDetailPaiement.getChildren().addAll(titleLabel3,hboxCenter1,hboxCenter2,hboxCenter3,hboxButton);
        root.setCenter(vboxDetailPaiement);




    }

    private void addColumnsToTableView() {
/*
        PaiementDAO PaiementDao=new PaiementDAOIMPL();
        ObservableList<Paiement> list = FXCollections.observableArrayList();
        list.setAll(PaiementDao.getAllV(idv));*/
        paimentsObservableList.setAll(paimdao.getAllPaim(venteDao.find(idVente)));
        tablePaiement.setItems(paimentsObservableList);
        tablePaiement.getColumns().addAll(numcol,montantcol,datecol,typecol);
    }

    private void updateColumns() {
        numcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        numcol.setPrefWidth(150);
        montantcol.setCellValueFactory(new PropertyValueFactory<>("montant"));
        montantcol.setPrefWidth(150);
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        datecol.setPrefWidth(150);
        typecol.setPrefWidth(150);
        typecol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Paiement, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Paiement, String> p) {
                return new SimpleStringProperty(p.getValue().getType().type);
            }
        });
    }

    private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMinWidth(600);
        titleLabel2.getStyleClass().add("labelTitle");
        titleLabel2.setMinWidth(600);
        titleLabel3.getStyleClass().add("labelTitle");
        titleLabel3.setMinWidth(330);
        hboxLabel5.setSpacing(10);
        hboxLabel4.setSpacing(10);
        hboxLabel3.setSpacing(10);
        hboxLabel2.setSpacing(10);
        hboxLabel1.setSpacing(10);
        vboxDetailVente.setSpacing(10);
        vboxDetailPaiement.setSpacing(30);
        hboxCenter1.setPadding(new Insets(20,30,10,30));
        hboxCenter2.setPadding(new Insets(0,30,0,30));
        hboxCenter3.setPadding(new Insets(0,30,0,30));
        hboxCenter1.setSpacing(20);
        hboxCenter2.setSpacing(20);
        hboxCenter3.setSpacing(30);
        hboxButton.setPadding(new Insets(20));
        vboxDetailPaiement.getStyleClass().add("Box");

    }

    private void initWindow() {
        window.setScene(scene);
        window.setWidth(950);
        window.setHeight(600);
        window.setTitle("Paiements");
        titleLabel.setAlignment(Pos.CENTER);
        window.initModality(Modality.APPLICATION_MODAL);
    }


    public FormPaiement() {
        initWindow();
        addStylesToNodes();
        updateColumns();
        addColumnsToTableView();
        addNodesToWindow();
        addDetailVente();
        addEvents();
        window.show();
    }

}
