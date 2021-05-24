package GestionProduit;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormProduiWindow {
    ProdAddHandler addHandler= new ProdAddHandler(this);
    VBox root = new VBox();
    HBox ButtonsBox = new HBox();
    Scene scene =new Scene( root);
    Stage window = new Stage();
    Label titleLabel= new Label("Nouveau produit");
    Label ProdDesignLabel= new Label("Designation :");
    TextField ProdDesignTextField = new TextField();

    Label ProdPrixLabel= new Label("Prix :");
    TextField ProdPrixTextField = new TextField();

    Label ProdQteLabel= new Label("Quantité :");
    TextField ProdQteTextField = new TextField();


    Label ProdDateLabel= new Label("Date :");
    DatePicker ProdDateSaisie = new DatePicker();

    Button ProdAddButton = new Button("Ajouter");
    Button ProdCancelButton = new Button("Annuler");

    private  void initWindow(){
        window.setScene(scene);
        window.setWidth(700);
        window.setHeight(450);
        window.setTitle("Nouveau produit");
        titleLabel.setAlignment(Pos.CENTER);
        window.initModality(Modality.APPLICATION_MODAL);

    }
    private  void addNodesToWindow(){
        root.getChildren().add(titleLabel);
        root.getChildren().addAll(ProdDesignLabel,ProdDesignTextField);
        root.getChildren().addAll(ProdPrixLabel,ProdPrixTextField);
        root.getChildren().addAll(ProdQteLabel,ProdQteTextField);
        root.getChildren().addAll(ProdDateLabel,ProdDateSaisie);
        root.getChildren().addAll(ProdAddButton, ProdCancelButton);
        ButtonsBox.getChildren().addAll(ProdAddButton, ProdCancelButton);
        root.getChildren().add(ButtonsBox);
    }
    private void addStylesToNodes(){
       scene.getStylesheets().add("css/styles.css");
        titleLabel.getStyleClass().add("labelTitle");
        titleLabel.setMinWidth(window.getWidth());
        ProdDesignLabel.getStyleClass().add("LabelForm");
        ProdPrixLabel.getStyleClass().add("LabelForm");
        ProdQteLabel.getStyleClass().add("LabelForm");
        ProdDateLabel.getStyleClass().add("LabelForm");
        root.setSpacing(15);
        ButtonsBox.setSpacing(25);

    }

    private void addEvents(){
        ProdCancelButton.setOnAction( event ->{
            window.close();
        });
        ProdAddButton.setOnAction( event->{
            //Layer : DdataAccessLayer DAO
            addHandler.addClick();

        });

     window.setOnCloseRequest( event ->{
         event.consume();
     });
    }

    public FormProduiWindow() {
        initWindow();
        addStylesToNodes();
        addNodesToWindow();
        addEvents();
        window.show();
    }
}