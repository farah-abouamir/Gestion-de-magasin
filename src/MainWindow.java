import GestionClients.Client;
import GestionClients.ClientListWindow;
import GestionClients.FormClientWindow;
import GestionProduit.FormProduiWindow;
import GestionProduit.ProduitsListWindow;
import GestionVentes.FormVenteWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
    BorderPane root= new BorderPane();
    Scene scene = new Scene(root);
    MenuItem nvProduit =new MenuItem("Nouveau");
    MenuItem ListProduit =new MenuItem("Liste");
    MenuItem nvClient =new MenuItem("Nouveau");
    MenuItem ListClient =new MenuItem("Liste");
    MenuItem nvVente= new MenuItem("Nouveau");
    MenuItem ListeVente= new MenuItem("List");


    private void createMenu(){
        MenuBar menuBar= new MenuBar();
        Menu ProduitsMenu= new Menu("Produits");
        Menu ClientMenu= new Menu("Client");
        Menu VenteMenu= new Menu("Vente");
        Menu PaiementMenu= new Menu("Paiement");

        ProduitsMenu.getItems().addAll(nvProduit,ListProduit);
        ClientMenu.getItems().addAll(nvClient,ListClient);
        VenteMenu.getItems().addAll(nvVente,ListeVente);
        menuBar.getMenus().addAll(ProduitsMenu, ClientMenu, VenteMenu, PaiementMenu);
        root.setTop(menuBar);
        scene.getStylesheets().add("css/styles.css");
        menuBar.getStyleClass().add("menuBar");



    }
    private void addStylesToNodes(){
        scene.getStylesheets().add("css/styles.css");
        root.getStyleClass().add("mainWindow");

    }
    private void addEvents(){

        nvProduit.setOnAction( event->{
            new FormProduiWindow();
        });
        ListProduit.setOnAction( event ->{
            new ProduitsListWindow();
        });

        nvClient.setOnAction( event -> {
            new FormClientWindow();

        });

        ListClient.setOnAction( event ->{
            new ClientListWindow();
        });
        nvVente.setOnAction(event -> {
            new FormVenteWindow();
        });


    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        createMenu();
        addEvents();
        addStylesToNodes();
        window.setScene(scene);
        window.setWidth(1100);
        window.setHeight(650);
        window.setTitle("Gestion de magasin");
        window.show();
    }
}

