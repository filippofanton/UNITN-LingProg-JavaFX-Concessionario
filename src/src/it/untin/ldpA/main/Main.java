package it.untin.ldpA.main;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main extends Application {

    Stage mainWindow = null;
    Stage stage = null;
    Persone persone = null;
    Automobili automobili = null;

    BigLabel lblPers, lblAuto;

    //per la finestra di inserimento
    TextField txt1,txt2,txt3;



    BorderPane prepareScene(){
        persone = new Persone();
        automobili = new Automobili();

        BorderPane root = new BorderPane();
        VBox vbPers = new VBox();
        VBox vbAuto = new VBox();

        lblPers = new BigLabel();
        lblAuto = new BigLabel();

        MyButton mescolaPers = new MyButton("Mescola", false, new EventMescolaP());
        MyButton contaPers = new MyButton("Conta", false, new EventContaP());
        MyButton ordinaPers = new MyButton("Ordina", false, new EventOrdinaP());
        MyButton ordina2Pers = new MyButton("Ordina per anno", false, new EventOrdinaP2());
        MyButton newPers = new MyButton("Aggiungi persona", false, new EventNewP());
        MyButton mescolaAuto = new MyButton("Mescola", false, new EventMescolaA());;
        MyButton contaAuto = new MyButton("Conta", false, new EventContaA());
        MyButton ordinaAuto = new MyButton("Ordina", false, new EventOrdinaA());
        MyButton ordina2Auto = new MyButton("Ordina per prezzo", false, new EventOrdinaA2());
        MyButton newAuto = new MyButton("Aggiungi auto", false, new EventNewA());

        vbPers.getChildren().addAll(lblPers, mescolaPers, ordinaPers, contaPers, ordina2Pers, newPers);
        vbAuto.getChildren().addAll(lblAuto, mescolaAuto, ordinaAuto, contaAuto, ordina2Auto, newAuto);
        root.setLeft(vbPers);
        root.setRight(vbAuto);

        return root;
    }

    // Classe LABEL CUSTOM
    class BigLabel extends Label {
        public BigLabel(){
            this.setPrefSize(300.0,500.0);
            this.setStyle("-fx-background-color: white;");
            this.setStyle("-fx-border-color: black");
        }
        public void setTesto(String testo){
            System.out.println(testo);
            this.setText(testo);
        }
    }

    // Classe BOTTONE CUSTOM
    class MyButton extends Button{
        public MyButton(String txt, boolean disabled, EventHandler e){
            this.setText(txt);
            this.setDisabled(disabled);
            this.setEventHandler(ActionEvent.ACTION, e);
        }
    }

// CLASSI EVENTHANDLER
    // MESCOLA
    class EventMescolaP implements EventHandler{
        public void handle(Event t){
            List<Persona> temp = new ArrayList<Persona>();
            temp.addAll(persone);
            Collections.shuffle(temp);
            persone.clear();
            persone.addAll(temp);
            lblPers.setTesto(persone.toString());
        }
    }
    class EventMescolaA implements EventHandler{
        public void handle(Event t){
            List<Auto> temp = new ArrayList<Auto>();
            temp.addAll(automobili);
            Collections.shuffle(temp);
            automobili.clear();
            automobili.addAll(temp);
            lblAuto.setTesto(automobili.toString());
        }
    }
    // CONTA
    class EventContaP implements EventHandler{
        public void handle(Event t){
            lblPers.setTesto("Numero di persone nel DB: " + persone.size());
        }
    }
    class EventContaA implements EventHandler{
        public void handle(Event t){
            lblAuto.setTesto("Numero di auto nel DB: " + automobili.size());
        }
    }
    // ORDINA 1
    class EventOrdinaA implements EventHandler{
        public void handle(Event t){
            List<Auto> temp = new ArrayList<Auto>();
            temp.addAll(automobili);
            temp.sort((a1, a2) -> a1.field2.compareTo(a2.field2));
            automobili.clear();
            automobili.addAll(temp);
            lblAuto.setTesto(automobili.toString());
        }
    }
    class EventOrdinaP implements EventHandler{
        public void handle(Event t){
            List<Persona> temp = new ArrayList<>();
            temp.addAll(persone);
            temp.sort((p1, p2) -> p1.field2.compareTo(p2.field2));
            persone.clear();
            persone.addAll(temp);
            lblPers.setTesto(persone.toString());
        }
    }
    // ORDINA 2
    class EventOrdinaA2 implements EventHandler{
        public void handle(Event t){
            List<Auto> temp = new ArrayList<Auto>();
            temp.addAll(automobili);
            temp.sort((a1, a2) -> a1.field3-a2.field3);
            automobili.clear();
            automobili.addAll(temp);
            lblAuto.setTesto(automobili.toString());
        }
    }
    class EventOrdinaP2 implements EventHandler{
        public void handle(Event t){
            List<Persona> temp = new ArrayList<>();
            temp.addAll(persone);
            temp.sort((p1, p2) -> p1.field3-p2.field3);
            persone.clear();
            persone.addAll(temp);
            lblPers.setTesto(persone.toString());
        }
    }
    // NEW RECORD FINESTRA
    class EventNewP implements EventHandler{
        @Override
        public void handle(Event event) {
            VBox vb = new VBox();
            TilePane tp = new TilePane();
            tp.setPrefColumns(2);
            txt1 = new TextField();
            txt2 = new TextField();
            txt3 = new TextField();
            tp.getChildren().add(new Text("Nome:"));
            tp.getChildren().add(txt1);
            tp.getChildren().addAll(new Text("Cognome:"), txt2);
            tp.getChildren().addAll(new Text("Anno:"), txt3);

            MyButton btAdd = new MyButton("Controlla e inserisci", false, new EventInserisciP());
            vb.getChildren().addAll(tp,btAdd);

            Scene sceneNew = new Scene(vb, 350,250);
            stage = new Stage();
            stage.setTitle("Aggiungi una persona");
            stage.initOwner(mainWindow);
            stage.initModality(Modality.WINDOW_MODAL); //Blocca le azioni nella finestra principale
            stage.setScene(sceneNew);
            stage.showAndWait();
        }
    }
    class EventNewA implements EventHandler{
        @Override
        public void handle(Event event) {
            VBox vb = new VBox();
            TilePane tp = new TilePane();
            tp.setPrefColumns(2);
            txt1 = new TextField();
            txt2 = new TextField();
            txt3 = new TextField();
            tp.getChildren().add(new Text("Marca:"));
            tp.getChildren().add(txt1);
            tp.getChildren().addAll(new Text("Modello:"), txt2);
            tp.getChildren().addAll(new Text("Prezzo:"), txt3);

            MyButton btAdd = new MyButton("Controlla e inserisci", false, new EventInserisciA());
            vb.getChildren().addAll(tp,btAdd);

            Scene sceneNew = new Scene(vb, 350,250);
            stage = new Stage();
            stage.setTitle("Aggiungi un automobile");
            stage.initOwner(mainWindow);
            stage.initModality(Modality.WINDOW_MODAL); //Blocca le azioni nella finestra principale
            stage.setScene(sceneNew);
            stage.showAndWait();
        }
    }
    // NEW RECORD INSERISCI
    class EventInserisciP implements  EventHandler{
        @Override
        public void handle(Event event) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            try{
                if(txt1.getText().isEmpty() || txt2.getText().isEmpty() || txt3.getText().isEmpty()){
                    a.setContentText("I campi non possono essere vuoti");
                    a.show();
                    System.out.println("I campi non possono essere vuoti");
                }else{
                    String n = txt1.getText();
                    String c = txt2.getText();
                    int y = Integer.parseInt(txt3.getText());
                    boolean ret = persone.add(new Persona(n,c,y));
                    if(!ret){
                        System.out.println("La persona è già presente");
                        a.setContentText("La persona è già presente");
                        a.show();
                    }else{
                        lblPers.setTesto(persone.toString());
                        stage.close();
                    }
                }
            }catch (Exception e){
                a.setContentText("Errore nella conversione dell'anno");
                a.show();
                System.out.println("Errore nella conversione dell'anno");
            }
        }
    }
    class EventInserisciA implements  EventHandler{
        @Override
        public void handle(Event event) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            try{
                if(txt1.getText().isEmpty() || txt2.getText().isEmpty() || txt3.getText().isEmpty()){
                    a.setContentText("I campi non possono essere vuoti");
                    a.show();
                    System.out.println("I campi non possono essere vuoti");
                }else{
                    String ma = txt1.getText();
                    String mo = txt2.getText();
                    int cash = Integer.parseInt(txt3.getText());
                    boolean ret = automobili.add(new Auto(ma,mo,cash));
                    if(!ret){
                        System.out.println("L'auto è già presente");
                        a.setContentText("L'auto è già presente");
                        a.show();
                    }else{
                        lblAuto.setTesto(automobili.toString());
                        stage.close();
                    }
                }
            }catch (Exception e){
                a.setContentText("Errore nella conversione del prezzo");
                a.show();
                System.out.println("Errore nella conversione del prezzo");
            }
        }
    }

    // start() e main()
    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(this.prepareScene(),600,800);
        mainWindow = primaryStage;
        primaryStage.setTitle("Concessionario");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
