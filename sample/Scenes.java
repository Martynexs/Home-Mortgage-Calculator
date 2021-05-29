package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import  javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

public class Scenes {

    private TextField textField;
    private TextField textField2;
    private DatePicker datePicker1;
    private DatePicker datePicker2;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private Button buttonCalculate;
    private TableView<TableElement> tableView;
    private DatePicker datePicker3;
    private DatePicker datePicker4;

    private Button buttonRodyti;
    private Label label7;
    private VBox vBox2;


    public Scene  getHomeScene()
    {
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: GRAY");

        VBox vbox1 = new VBox();
        AnchorPane.setTopAnchor(vbox1, 35.0);
        AnchorPane.setBottomAnchor(vbox1, 22.0);
        AnchorPane.setLeftAnchor(vbox1, 14.0);
        vbox1.prefHeight(436.0);
        vbox1.prefWidth(254.0);
        vbox1.setSpacing(10.0);
        vbox1.prefHeight(436.0);
        vbox1.prefWidth(245.0);

        Label label1 = new Label("Būsto paskolos skaičiuoklė");
        label1.setFont(Font.font("System Bold", 20.0));
        label1.setTextFill(Paint.valueOf("WHITE"));

        Label label2 = new Label("Paskolos suma");

        textField = new TextField();
        textField.textProperty().addListener(observable -> isFilled());

        Label label3 = new Label("Paskolos terminas");

        Label label31 = new Label("Nuo:");

        datePicker1 = new DatePicker();
        datePicker1.setEditable(false);
        datePicker1.setValue(LocalDate.now());
        datePicker1.valueProperty().addListener(observable -> isFilled());

        Label label32 = new Label("Iki:");

        datePicker2 = new DatePicker();
        datePicker2.setEditable(false);
        datePicker2.setValue(LocalDate.now());
        datePicker2.valueProperty().addListener(observable -> isFilled());

        Label label4 = new Label("Grąžinimo grafikas");

        radioButton1 = new RadioButton("Anuiteto");
        radioButton2 = new RadioButton("Linijinis");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton1.setOnAction(event -> isFilled());
        radioButton2.setOnAction(event -> isFilled());

        Label label5 = new Label("Metinis palūkanų procentas");

        textField2 = new TextField();
        textField2.textProperty().addListener(observable -> isFilled());

        buttonCalculate = new Button("Skaičiuoti");
        buttonCalculate.setDisable(true);
        buttonCalculate.setOnMouseClicked(mouseEvent -> calculate());

        vbox1.getChildren().addAll(label1, label2, textField, label3, label31, datePicker1, label32, datePicker2, label4, radioButton1, radioButton2, label5, textField2, buttonCalculate);

        vBox2 = new VBox();
        AnchorPane.setTopAnchor(vBox2, 0.0);
        AnchorPane.setBottomAnchor(vBox2, 0.0);
        AnchorPane.setRightAnchor(vBox2, 0.0);
        vBox2.setPrefHeight(493.0);
        vBox2.setPrefWidth(612.0);

        tableView = new TableView<>();
        TableColumn<TableElement, String> tableColumn1 = new TableColumn<>("Data");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<TableElement, String> tableColumn2 = new TableColumn<>("Suma");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("payment"));

        TableColumn<TableElement, String> tableColumn3 = new TableColumn<>("Kreditas");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("credit"));

        TableColumn<TableElement, String> tableColumn4 = new TableColumn<>("Liko sumokėti");
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("left"));
        tableColumn4.setPrefWidth(150);

        TableColumn<TableElement, String> tableColumn5 = new TableColumn<>("Palūkanos");
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("interest"));

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3, tableColumn5, tableColumn4);


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20.0);

        VBox vBox3 = new VBox();
        Label label61 = new Label("Nuo:");

        datePicker3 = new DatePicker();
        datePicker3.setValue(LocalDate.now());
        datePicker3.setEditable(false);
        datePicker3.valueProperty().addListener(observable -> isFilled2());

        Label label62 = new Label("Iki:");

        datePicker4 = new DatePicker();
        datePicker4.setValue(LocalDate.now());
        datePicker4.setEditable(false);
        datePicker4.valueProperty().addListener(observable -> isFilled2());

        vBox3.getChildren().addAll(label61, datePicker3, label62, datePicker4);

        buttonRodyti = new Button("Rodyti");
        buttonRodyti.setOnAction(event -> loadTable());

        Button buttonSapusdinti = new Button("Spausdinti");
        buttonSapusdinti.setOnAction(event -> exportToFile());

        label7 = new Label("Failas sukurtas");
        label7.setVisible(false);

        hBox.getChildren().addAll(vBox3, buttonRodyti, buttonSapusdinti, label7);

        vBox2.getChildren().addAll(tableView, hBox);
        vBox2.setDisable(true);

        root.getChildren().addAll(vbox1, vBox2);


        return new Scene(root, 896.0, 493.0);
    }

    //Patikrinama ar laukai teisingai uzpildyti
    private void isFilled()
    {
        Period pr = Period.between(datePicker1.getValue(), datePicker2.getValue());
        if(!(textField.getText().equals(""))  && isDouble(textField.getText()) && datePicker1.getValue() != null && datePicker2.getValue() != null && pr.getYears()*12+ pr.getMonths() >= 1 && (radioButton1.isSelected() || radioButton2.isSelected()) && !(textField2.getText().equals(""))  && isDouble(textField2.getText()))
        {
            buttonCalculate.setDisable(false);
        }
        else
        {
            buttonCalculate.setDisable(true);
        }
    }

    //Patikrinama ar laukai teisingai uzpildyti
    private void isFilled2()
    {
        Period pr = Period.between(datePicker3.getValue(), datePicker4.getValue());
        if( datePicker3.getValue() != null && datePicker4.getValue() != null && pr.getYears()*12+ pr.getMonths()>= 1)
        {
            buttonRodyti.setDisable(false);
        }
        else
        {
            buttonRodyti.setDisable(true);
        }
    }

    private boolean isDouble(String txf)
    {
        try{
            double number = Double.parseDouble(txf);
            return true;
        }catch (NumberFormatException e)
        {
            return false;
        }
    }

    //Skaiciuojami menesiniai mokesciai
    private void calculate()
    {
        vBox2.setDisable(false);
        if(radioButton1.isSelected())
        {
            Calculator.anuinity(datePicker1.getValue(), datePicker2.getValue(), Double.parseDouble(textField.getText()), Double.parseDouble(textField2.getText()));
        }
        else
        {
            Calculator.liniar(datePicker1.getValue(), datePicker2.getValue(), Double.parseDouble(textField.getText()),  Double.parseDouble(textField2.getText()));
        }
        datePicker3.setValue(Calculator.start);
        datePicker4.setValue(Calculator.end);
        label7.setVisible(false);
    }

    //Sukuriami elementai kurie bus rodomi lenteleje
    private ObservableList<TableElement> getList()
    {
        int offset;
        int end;
        if(datePicker3.getValue().compareTo(Calculator.start) <= 0)
        {
            offset = 0;
        }
        else
        {
            offset = Period.between(Calculator.start, datePicker3.getValue()).getMonths()+Period.between(Calculator.start, datePicker3.getValue()).getYears()*12-1;
        }


        if(datePicker4.getValue().compareTo(Calculator.end) >= 0)
        {
            end = Calculator.months;
        }
        else if(datePicker4.getValue().compareTo(Calculator.start) == 0)
        {
            end = 1;
        }
        else
        {
            end = Calculator.months - (Period.between(datePicker4.getValue(), Calculator.end).getMonths()+Period.between(datePicker4.getValue(), Calculator.end).getYears()*12);
        }

        ObservableList<TableElement> elements =  FXCollections.observableArrayList();
        for(int i=offset; i<end; i++)
        {
            elements.add(new TableElement(Calculator.start.plusMonths(i+1).toString(), Calculator.payments[i], Calculator.credit[i], Calculator.interest[i], Calculator.leftToPay[i]));
        }
        return elements;
    }

    //Uzkraunamos reiksmes i lentele
    private void loadTable()
    {
        tableView.getItems().clear();
        tableView.setItems(getList());
    }

    //Funkcija atspaudina ataskaita txt faile
    private  void exportToFile()
    {

        DecimalFormat df = new DecimalFormat("0.00 €; 0.00 €");
        Writer writer = null;
        try {
            File file = new File("Ataskaita.txt");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Paskolos suma: " + df.format(Calculator.loanSum) + "\n" + "Metine palukanu norma: " + Calculator.yearPercent + " %\n" + "Paskolos terminas: " + Calculator.start + " - " + Calculator.end + "\n");
            writer.write("Bankui sumokesite: " + df.format(Calculator.paymentSum) + "\n");

            writer.write(String.format("%-25s|%-25s|%-25s|%-25s|%-25s|\n\n", "Data", "Suma", "Kreditas", "Palukanos", "Liko sumoketi"));
            for (int i=0; i<Calculator.months; i++)
            {
                writer.write(String.format("%-25s|%-25s|%-25s|%-25s|%-25s|\n",Calculator.start.plusMonths(i+1).toString(), df.format(Calculator.payments[i]), df.format(Calculator.credit[i]), df.format(Calculator.interest[i]), df.format(Calculator.leftToPay[i])));
            }
            label7.setVisible(true);
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        finally {
            try {
                if(writer != null) {
                    writer.flush();
                    writer.close();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
