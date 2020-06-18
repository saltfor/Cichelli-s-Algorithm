package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    Pane pane;
    TextArea listkelime=new TextArea();
    TextField textkelime=new TextField();
    Button btnEkle=new Button("EKLE");
    Button btnkelimepuanla=new Button("KELİMELERİ PUANLA");
    Button btnsirala=new Button("PUANA GÖRE SIRALA");
    Button btnislemdevam=new Button("DEVAM");
    TextArea harfpuanlistesi=new TextArea();
    TextArea kelimepuanlistesi=new TextArea();
    int kelimesayisi=0;

    ArrayList<String> kelimeler=new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        pane=new Pane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(pane, 700, 350));
        primaryStage.show();
        listkelime.setLayoutX(10);
        listkelime.setLayoutY(50);
        textkelime.setLayoutX(10);
        textkelime.setLayoutY(10);
        listkelime.setMaxWidth(100);
        listkelime.setMinHeight(250);
        listkelime.setEditable(false);
        harfpuanlistesi.setEditable(false);
        btnEkle.setLayoutX(170);
        btnEkle.setLayoutY(10);
        harfpuanlistesi.setVisible(false);
        harfpuanlistesi.setMaxWidth(80);
        harfpuanlistesi.setLayoutX(150);
        harfpuanlistesi.setLayoutY(50);
        harfpuanlistesi.setMinHeight(250);
        btnkelimepuanla.setVisible(false);
        btnkelimepuanla.setLayoutX(harfpuanlistesi.getLayoutX());
        btnkelimepuanla.setLayoutY(btnEkle.getLayoutY());
        kelimepuanlistesi.setLayoutX(260);
        kelimepuanlistesi.setMaxWidth(150);
        kelimepuanlistesi.setLayoutY(harfpuanlistesi.getLayoutY());
        kelimepuanlistesi.setEditable(false);
        kelimepuanlistesi.setVisible(false);
        kelimepuanlistesi.setMinHeight(250);
        btnEkle.setOnAction(e->{
            kelimesayisi++;
            kelimeler.add(textkelime.getText());
            listkelime.setText(listkelime.getText()+textkelime.getText()+"\n");
            textkelime.requestFocus();
            if(kelimesayisi>9){
                textkelime.setVisible(false);
                btnEkle.setVisible(false);
                harfpuanlistesi.setVisible(true);
                harfpuanla();
                btnkelimepuanla.setVisible(true);
            }
        });
        btnkelimepuanla.setOnAction(e->{
            btnkelimepuanla.setVisible(false);
            kelimepuanlistesi.setVisible(true);
            btnsirala.setVisible(true);
            kelimepuanla();
        });
        btnsirala.setVisible(false);
        btnsirala.setLayoutY(btnEkle.getLayoutY());
        btnsirala.setLayoutX(kelimepuanlistesi.getLayoutX());
        btnsirala.setOnAction(e->{
            btnsirala.setVisible(false);
            kelimepuanlistesi.clear();
            sirala();
            yerlesimyazdir();
            btnislemdevam.setVisible(true);
        });
        btnislemdevam.setVisible(false);
        btnislemdevam.setLayoutX(460);
        btnislemdevam.setLayoutY(btnsirala.getLayoutY());
        btnislemdevam.setOnAction(e->{
            kelimeyerlestir();
        });
        pane.getChildren().addAll(listkelime,textkelime,btnEkle,harfpuanlistesi,btnkelimepuanla,kelimepuanlistesi,btnsirala,btnislemdevam);
    }
    private boolean yerlesirmi(){
        int kutu=kelimevepuankumesi.get(yerlesecek).uzunluk;
        for (int i=0;i<harfvepuankumesi.size();i++){
            if(harfvepuankumesi.get(i).harf==kelimeilkharf(kelimevepuankumesi.get(yerlesecek).kelime)){
                kutu+=harfvepuankumesi.get(i).agirlik;
            }
            if(harfvepuankumesi.get(i).harf==kelimesonharf(kelimevepuankumesi.get(yerlesecek).kelime)){
                kutu+=harfvepuankumesi.get(i).agirlik;
            }
        }
        int sonuc=kutu%(kelimevepuankumesi.size());
        if(txthafizakutucuk.get(sonuc).getText().equals("EMPTY"))
            return true;
        else
            return false;
    }
    private void sonharfabak(){
        char son=kelimesonharf(kelimevepuankumesi.get(yerlesecek).kelime);
        for(int j=0;j<harfvepuankumesi.size();j++){
            if(son==harfvepuankumesi.get(j).harf){
                if(harfvepuankumesi.get(j).agirlik<5){
                    if (harfvepuankumesi.get(j).agirlik<4){
                        for (int k=0;k<4;k++){
                            if (harfvepuankumesi.get(j).agirlik<4){
                                if (yerlesirmi())
                                    break;
                                else {
                                    harfvepuankumesi.get(j).agirlik++;
                                    labelharf.get(j).setText(harfvepuankumesi.get(j).harf + " = " + harfvepuankumesi.get(j).agirlik);
                                }
                            }
                            else
                                break;
                        }
                        if (yerlesirmi()){
                            kelimeyerlestir();
                            break;
                        }
                    }
                }
            }
        }
    }
    private void kelimeyerlestir(){
        int kutu=kelimevepuankumesi.get(yerlesecek).uzunluk;
        for (int i=0;i<harfvepuankumesi.size();i++){
            if(harfvepuankumesi.get(i).harf==kelimeilkharf(kelimevepuankumesi.get(yerlesecek).kelime)){
                kutu+=harfvepuankumesi.get(i).agirlik;
            }
            if(harfvepuankumesi.get(i).harf==kelimesonharf(kelimevepuankumesi.get(yerlesecek).kelime)){
                kutu+=harfvepuankumesi.get(i).agirlik;
            }
        }
        int sonuc=kutu%(kelimevepuankumesi.size());
        if(txthafizakutucuk.get(sonuc).getText().equals("EMPTY")){
            txthafizakutucuk.get(sonuc).setText(kelimevepuankumesi.get(yerlesecek).kelime);
            yerlesecek++;
        }
        else{
            char ilk=kelimeilkharf(kelimevepuankumesi.get(yerlesecek).kelime);
            for(int i=0;i<harfvepuankumesi.size();i++){
                if(ilk==harfvepuankumesi.get(i).harf){
                    if(harfvepuankumesi.get(i).agirlik<5){
                        if (harfvepuankumesi.get(i).agirlik<4){
                            for (int k=0;k<4;k++){
                                if(harfvepuankumesi.get(i).agirlik<4){
                                    if (yerlesirmi())
                                        break;
                                    else {
                                        harfvepuankumesi.get(i).agirlik++;
                                        labelharf.get(i).setText(harfvepuankumesi.get(i).harf + " = " + harfvepuankumesi.get(i).agirlik);
                                    }
                                }
                                else
                                    break;
                            }
                            if (yerlesirmi()){
                                kelimeyerlestir();
                                break;
                            }
                            else{
                                sonharfabak();
                            }
                        }
                        else{
                            sonharfabak();
                        }
                    }
                }
            }
        }
        if (yerlesecek<kelimevepuankumesi.size()==false){
            btnislemdevam.setVisible(false);
            Label lblfinish=new Label("ALGORITHM IS OVER !!!");
            lblfinish.setLayoutX(txthafizakutucuk.get(0).getLayoutX());
            lblfinish.setLayoutY(txthafizakutucuk.get(kelimevepuankumesi.size()-1).getLayoutY()+50);
            pane.getChildren().add(lblfinish);
        }

    }
    int yerlesecek=0;
    ArrayList<Label> labelharf=new ArrayList<>();
    ArrayList<TextField> txthafizakutucuk=new ArrayList<>();
    private  void yerlesimyazdir(){
        for (int i=0;i<harfvepuankumesi.size();i++){
            Label lbl=new Label(harfvepuankumesi.get(i).harf+" = "+harfvepuankumesi.get(i).agirlik);
            lbl.setLayoutX(460);
            lbl.setLayoutY(kelimepuanlistesi.getLayoutY()+(i*20));
            labelharf.add(lbl);
            pane.getChildren().add(lbl);
        }
        for (int i=0;i<kelimevepuankumesi.size();i++){
            TextField txthafiza=new TextField("EMPTY");
            txthafiza.setMinHeight(20);
            txthafiza.setMaxHeight(20);
            txthafiza.setMaxWidth(100);
            txthafiza.setLayoutX(535);
            txthafiza.setEditable(false);
            txthafiza.setLayoutY(kelimepuanlistesi.getLayoutY()+(i*20));
            txthafizakutucuk.add(txthafiza);
            pane.getChildren().add(txthafiza);
        }
        for (int i=0;i<kelimevepuankumesi.size();i++){
            Label lbl=new Label(i+"-");
            lbl.setMinHeight(20);
            lbl.setMaxHeight(100);
            lbl.setLayoutX(520);
            lbl.setLayoutY(kelimepuanlistesi.getLayoutY()+(i*20));
            pane.getChildren().add(lbl);
        }
    }

    private void sirala(){
        kelimevepuan tmp;
        for(int i=0; i<kelimevepuankumesi.size(); i++)
        {
            int sirali=1;
            for(int j=kelimevepuankumesi.size()-1 ; j>i;j--){
                if(kelimevepuankumesi.get(j-1).puan<kelimevepuankumesi.get(j).puan){
                    sirali=0;
                    tmp=kelimevepuankumesi.get(j-1);
                    kelimevepuankumesi.set(j-1,kelimevepuankumesi.get(j));
                    kelimevepuankumesi.set(j,tmp);
                }
            }
            if(sirali==1)
                break;
        }
        kelimelistesiyaz();
    }
    ArrayList<Character> harfler=new ArrayList<>();
    ArrayList<harfvepuan> harfvepuankumesi=new ArrayList<>();
    private void harfpuanla(){
        for(int i=0;i<kelimeler.size();i++){
            ilkvesonharf(kelimeler.get(i));
        }
        for(int i=0;i<harfler.size();i++){

            char aktifharf=harfler.get(i);
            if(aktifharf=='-'){
                continue;
            }
            else{
                int harfpuani=0;
                for(int j=0;j<harfler.size();j++){
                    if(aktifharf==harfler.get(j)){
                        harfpuani++;
                        harfler.set(j,'-');
                    }
                }
                harfvepuan Harfvepuan=new harfvepuan();
                Harfvepuan.harf=aktifharf;
                Harfvepuan.puan=harfpuani;
                harfvepuankumesi.add(Harfvepuan);
            }
        }
        for(int i=0;i<harfvepuankumesi.size();i++){
            harfpuanlistesi.setText(harfpuanlistesi.getText()+harfvepuankumesi.get(i).harf+" = "+harfvepuankumesi.get(i).puan+"\n");
        }
    }
    ArrayList<kelimevepuan> kelimevepuankumesi=new ArrayList<>();
    private void kelimepuanla(){
        for(int i=0;i<kelimeler.size();i++){
            int ilkpuan=0,sonpuan=0;
            for(int j=0;j<harfvepuankumesi.size();j++){
                if(kelimeler.get(i).charAt(0)==harfvepuankumesi.get(j).harf){
                    ilkpuan=harfvepuankumesi.get(j).puan;
                }
            }
            for(int j=0;j<harfvepuankumesi.size();j++){
                if(kelimeler.get(i).charAt(kelimeler.get(i).length()-1)==harfvepuankumesi.get(j).harf){
                    sonpuan=harfvepuankumesi.get(j).puan;
                }
            }
            int kelimepuani=ilkpuan+sonpuan;
            kelimevepuan Kelimevepuan=new kelimevepuan();
            Kelimevepuan.kelime=kelimeler.get(i);
            Kelimevepuan.puan=kelimepuani;
            Kelimevepuan.uzunluk=kelimeler.get(i).length();
            kelimevepuankumesi.add(Kelimevepuan);
        }
        kelimelistesiyaz();
    }
    private void kelimelistesiyaz(){
        for(int i=0;i<kelimevepuankumesi.size();i++){
            kelimepuanlistesi.setText(kelimepuanlistesi.getText()+kelimevepuankumesi.get(i).kelime+" = "+kelimevepuankumesi.get(i).puan+"\n");
        }
    }
    private void ilkvesonharf(String kelime){
        char ilk,son;
        ilk=kelime.charAt(0);
        son=kelime.charAt(kelime.length()-1);
        harfler.add(ilk);
        harfler.add(son);
    }

    public static void main(String[] args) {
        launch(args);
    }
    private char kelimeilkharf(String kelime){
        return kelime.charAt(0);
    }
    private char kelimesonharf(String kelime){
        return kelime.charAt(kelime.length()-1);
    }
}
class harfvepuan{
    char harf;
    int puan;
    int agirlik=0;
}
class kelimevepuan{
    String kelime;
    int puan;
    int uzunluk;
}
