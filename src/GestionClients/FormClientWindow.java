package GestionClients;

import GestionProduit.ProdAddHandler;
import com.sun.security.ntlm.Client;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormClientWindow {

        ClientAddHandler addHandler= new ClientAddHandler(this);
        VBox root = new VBox();
        HBox ButtonsBox = new HBox();
        Scene scene =new Scene( root);
        Stage window = new Stage();
        Label titleLabel= new Label("Nouveau client");
        Label ClientNomLabel= new Label("Nom :");
        TextField ClientNomTextField = new TextField();

        Label ClientPrenomLabel= new Label("Prenom:");
        TextField ClientPrenomTextField = new TextField();

        Label ClientTeleLabel= new Label("Telephone:");
        TextField ClientTeleTextField = new TextField();

        Label ClientAdresseLabel= new Label("Adresse:");
        TextField ClientAdresseTextField = new TextField();

        Button ClientAddButton = new Button("Ajouter");
        Button ClientCancelButton = new Button("Annuler");

        private  void initWindow(){
            window.setScene(scene);
            window.setWidth(700);
            window.setHeight(450);
            window.setTitle("Nouveau Client");
            titleLabel.setAlignment(Pos.CENTER);
            window.initModality(Modality.APPLICATION_MODAL);

        }
        private  void addNodesToWindow(){
            root.getChildren().add(titleLabel);
            root.getChildren().addAll(ClientNomLabel, ClientNomTextField);
            root.getChildren().addAll(ClientPrenomLabel,ClientPrenomTextField);
            root.getChildren().addAll(ClientTeleLabel, ClientTeleTextField);
            root.getChildren().addAll(ClientAdresseLabel, ClientAdresseTextField);
            root.getChildren().addAll(ClientAddButton, ClientCancelButton);
            ButtonsBox.getChildren().addAll(ClientAddButton, ClientCancelButton);
            root.getChildren().add(ButtonsBox);
        }
        private void addStylesToNodes(){
            scene.getStylesheets().add("css/styles.css");
            titleLabel.getStyleClass().add("labelTitle");
            titleLabel.setMinWidth(window.getWidth());
            ClientNomLabel.getStyleClass().add("LabelForm");
            ClientPrenomLabel.getStyleClass().add("LabelForm");
            ClientTeleLabel.getStyleClass().add("LabelForm");
            ClientAdresseLabel.getStyleClass().add("LabelForm");
            root.setSpacing(15);
            ButtonsBox.setSpacing(25);

        }

        private void addEvents(){
            ClientCancelButton.setOnAction( event ->{
                window.close();
            });
            ClientAddButton.setOnAction(event->{

                addHandler.addClick();

            });

            window.setOnCloseRequest( event ->{
                event.consume();
            });

        }

        public FormClientWindow() {
            initWindow();
            addStylesToNodes();
            addNodesToWindow();
            addEvents();
            window.show();
        }
    }

