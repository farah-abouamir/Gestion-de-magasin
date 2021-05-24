package GestionVentes;

import GestionClients.Client;
import GestionClients.ClientDaoImp;
import GestionPaiement.FormPaiement;
import GestionProduit.IProduitDao;
import GestionProduit.Produit;
import GestionProduit.ProduitDaoImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class FormVenteWindow {

    VenteHandler venteHandler= new VenteHandler(this);
    List<LigneCommande> Listcmd = new ArrayList<>();
    List<Client> clients;
    Client cl=new Client();
    IProduitDao pdao = new ProduitDaoImp();
    IVenteDao venteDao = new VenteDaoImpl();
    BorderPane root = new BorderPane();
    Scene scene =new Scene( root);
    Stage window = new Stage();
    Label titleLabel= new Label("Detail vente");

    //------------------Detail vente
    VBox vBoxLeft = new VBox();
    Label NomClientLabel= new Label("Nom :");
    TextField NomClientTextField = new TextField();

    Label PrenomClientLabel= new Label("Prenom :");
    TextField PrenomClientTextField = new TextField();

    HBox ClientHbox1= new HBox();
    HBox ClientHbox2= new HBox();

    Label DateVenteLabel= new Label("Date :");
    DatePicker  DateVenteSaisie = new DatePicker();
    HBox DateHbox= new HBox();
    VBox DetailVente= new VBox();

    //---------------------------ligne commande
    HBox lCmdBox= new HBox();
    Label DesignLabel= new Label("Designation");
    TextField DesignTextField = new TextField();
    HBox DesignHbox= new HBox();
    Label PrixVenteLabel= new Label("Prix:");
    TextField PrixTextField = new TextField();
    HBox PrixHbox = new HBox();
    Label QteVenteLabel= new Label("Quantité:");
    TextField QteTextField = new TextField();
    HBox QteHBox = new HBox();
    VBox lCmdDetailVbox= new VBox();
    HBox lCmdDetailHbox= new HBox();

    Label titleLabelReg = new Label("Réglement de vente");
    Label labelHT = new Label("Total HT");
    Label HTvaleur= new Label();
    Label labelTVA1= new Label("TVA 7% ");
    Label TVAval7= new Label();
    Label labelTVA2= new Label("TVA 20%");
    Label TVAval20= new Label();
    Label labelTotal= new Label("Total ");
    Label Totalval= new Label();
    HBox hboxLabel1= new HBox();
    HBox hboxLabel2= new HBox();
    HBox hboxLabel3= new HBox();
    HBox hboxLabel4= new HBox();
    VBox vBoxCenter = new VBox();
    Label titleListCmd= new Label("Lignes de commandes");
    //-------------------------------------------Buttons
    Button AddlCmdButton= new Button("Enregistrer");
    Button DeletelCmdButton= new Button("Supprimer");
    Button ReglementButton= new Button("Reglement");
    Button UpdateLcmdButton= new Button("Modifier Ligne");
    Button QuitterButton= new Button("Quitter");
    HBox  hBoxButton = new HBox();

    Button AddbuttonVboxLeft = new Button("Ajouter");
    Button DeletebuttonVboxLeft= new Button("Supprimer");

    //---------------------------------------------------TableProduits
    TableView<Produit> prodTableView = new TableView<>();
    TableColumn<Produit, String> Designcolumn = new TableColumn<>("Designation");
    TableColumn<Produit, Integer> Qtecolumn = new TableColumn<>("Qte");
    TableColumn<Produit, Double> Prixcolumn= new TableColumn<>("Prix");
    ObservableList<Produit> produitObservableList= FXCollections.observableArrayList();
    VBox vBoxTbale= new VBox();
    //--------------------------------------------------------------------TableLcmd
    TableView<LigneCommande> lCmdTableView = new TableView<>();
    TableColumn<LigneCommande, String> Designcol = new TableColumn<>("Designation");
    TableColumn<LigneCommande, Integer> Qtecol = new TableColumn<>("Qte");
    TableColumn<LigneCommande, Double> Prixcol= new TableColumn<>("Prix");
    TableColumn<LigneCommande, Double> SousTotalcol= new TableColumn<>("Total");
    ObservableList<LigneCommande> lCmdObservableList= FXCollections.observableArrayList();
    VBox vBoxTbalelCmd= new VBox();


    private  void initWindow(){
        window.setScene(scene);
        window.setWidth(1100);
        window.setHeight(650);
        window.setTitle("Detail du vente");
        titleLabel.setAlignment(Pos.CENTER);
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private  void addColumnsToTableView(){
        produitObservableList.setAll(pdao.findAll());
        prodTableView.setItems(produitObservableList);
        prodTableView.getColumns().addAll(Designcolumn,Qtecolumn,Prixcolumn);

        lCmdTableView.getColumns().addAll(Designcol,Prixcol,Qtecol,SousTotalcol);
        lCmdTableView.setItems(lCmdObservableList);

    }

    private void  updateColumns(){

        Designcol.setCellValueFactory(new PropertyValueFactory<>("ProduitDesignation"));
        Designcol.setPrefWidth(150);
        Prixcol.setCellValueFactory(new PropertyValueFactory<>("ProduitPrix"));
        Prixcol.setPrefWidth(150);
        Qtecol.setCellValueFactory(new PropertyValueFactory<>("qte"));
        Qtecol.setPrefWidth(150);
        SousTotalcol.setCellValueFactory(new PropertyValueFactory<>("Stotal"));
        SousTotalcol.setPrefWidth(150);


        Designcolumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        Designcolumn.setPrefWidth(150);
        Qtecolumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
        Qtecolumn.setPrefWidth(150);
        Prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Prixcolumn.setPrefWidth(150);



    }
    private  void addNodesToWindow(){
          hBoxButton.getChildren().addAll(AddlCmdButton,UpdateLcmdButton, DeletelCmdButton, ReglementButton, QuitterButton);
          hBoxButton.setSpacing(10);
          root.setTop(hBoxButton);

          ClientHbox1.getChildren().addAll(NomClientLabel, NomClientTextField);
          ClientHbox2.getChildren().addAll(PrenomClientLabel, PrenomClientTextField);
          DateHbox.getChildren().addAll(DateVenteLabel, DateVenteSaisie);

          DetailVente.getChildren().addAll(titleLabel,ClientHbox1,ClientHbox2,DateHbox);
          vBoxLeft.getChildren().add(DetailVente);

          DesignHbox.getChildren().addAll(DesignLabel, DesignTextField);
          PrixHbox.getChildren().addAll(PrixVenteLabel,PrixTextField);
          QteHBox.getChildren().addAll(QteVenteLabel, QteTextField);

          lCmdDetailVbox.getChildren().addAll(DesignHbox, PrixHbox, QteHBox);
          lCmdDetailHbox.getChildren().addAll(lCmdDetailVbox, AddbuttonVboxLeft, DeletebuttonVboxLeft);
          vBoxLeft.getChildren().add(lCmdDetailHbox);
          vBoxTbale.getChildren().add(prodTableView);
          vBoxLeft.getChildren().add(vBoxTbale);
          root.setLeft(vBoxLeft);
          hboxLabel1.getChildren().addAll(labelHT, HTvaleur);
          hboxLabel2.getChildren().addAll(labelTVA1, TVAval7);
          hboxLabel3.getChildren().addAll(labelTVA2, TVAval20);
          hboxLabel4.getChildren().addAll(labelTotal, Totalval);
          vBoxCenter.getChildren().addAll(titleLabelReg,hboxLabel1,hboxLabel2,hboxLabel3,hboxLabel4);
          vBoxCenter.getChildren().add(titleListCmd);
          vBoxCenter.getChildren().add(lCmdTableView);
          root.setCenter(vBoxCenter);
    }


    private void addStylesToNodes(){
        scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMinWidth(400);
        DetailVente.setSpacing(15);
        DetailVente.setPadding(new Insets(10,10,0,10));
        AddlCmdButton.getStyleClass().add("TopButton");
        UpdateLcmdButton.getStyleClass().add("TopButton");
        DeletelCmdButton.getStyleClass().add("TopButton");
        ReglementButton.getStyleClass().add("TopButton");
        QuitterButton.getStyleClass().add("TopButton");
        lCmdDetailHbox.setSpacing(10);
        lCmdDetailVbox.setSpacing(10);
        lCmdDetailHbox.setPadding(new Insets(10));
        AddbuttonVboxLeft.getStyleClass().add("VboxLeftButton");
        DeletebuttonVboxLeft.getStyleClass().add("VboxLeftButton");
        titleLabelReg.getStyleClass().add("labelTitle");
        titleLabelReg.setMinWidth(640);
        titleListCmd.getStyleClass().add("labelTitle");
        titleListCmd.setMinWidth(640);
        vBoxCenter.setPadding(new Insets(10,10,10,10));
        DetailVente.getStyleClass().add("Box");
        lCmdDetailHbox.getStyleClass().add("Box");
    }

    public void addEvents(){

        NomClientTextField.setOnKeyTyped(event -> {
            if(!this.NomClientTextField.getText().isEmpty()){
                this.clients=new ClientDaoImp().findAll();
                for (Client i:this.clients) {
                    if(i.getNom().contains(NomClientTextField.getText())){
                        System.out.println("in");
                        this.cl=i;
                        break;
                    }
                }
                this.PrenomClientTextField.setText(cl.getPrenom());
            }else cl=null;
        });
        prodTableView.setOnMouseClicked( event -> {
            Produit p =prodTableView.getSelectionModel().getSelectedItem();
            DesignTextField.setText(p.getDesignation());
            PrixTextField.setText(String.valueOf(p.getPrix()));
        });

        AddbuttonVboxLeft.setOnAction(event -> {
            Produit p =prodTableView.getSelectionModel().getSelectedItem();
            Produit prod = new Produit(p.getId(), p.getDesignation(),p.getQte(),p.getPrix(), p.getDate());
            LigneCommande lcmd=new LigneCommande(p,Integer.parseInt(QteTextField.getText()));
            Listcmd.add(lcmd);
            lCmdObservableList.setAll(Listcmd);
            lCmdTableView.setItems(lCmdObservableList);

        });
        DeletebuttonVboxLeft.setOnAction( event ->{
            LigneCommande lcmd = lCmdTableView.getSelectionModel().getSelectedItem();
            Listcmd.remove(lcmd);
            lCmdObservableList.setAll(Listcmd);
            lCmdTableView.setItems(lCmdObservableList);

        });

       AddlCmdButton.setOnAction(event -> {
                    Vente v=new Vente();
                   // Payment pa=new Payment();
                    v.setLcmds(Listcmd);
                    v.setClient(cl);
                 //   pa.setVente(v);
                   // pa.setType(this.paymentbox.getValue());
                    //pa.setMontant(Double.parseDouble(this.paymentamount.getText()));

                    venteDao.create(v);
                    //new PaymentDaoImp().create(pa);
                    System.out.println(v.getId());
        });

        QuitterButton.setOnAction(event -> {
            window.close(); });

        ReglementButton.setOnAction(event -> {
            new FormPaiement();
        });
    }


    public FormVenteWindow() {
        initWindow();
        addStylesToNodes();
        updateColumns();
        addColumnsToTableView();
        addNodesToWindow();
        addEvents();
        window.show();
    }

    }

