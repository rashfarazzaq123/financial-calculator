package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.json.simple.*;
import java.io.*;

import java.text.DecimalFormat;

public class Main extends Application {
    private static DecimalFormat df = new DecimalFormat("0.00");
    private TextField tx;
    private static JSONObject financeCalData;

    public static void main(String[] args) {
        financeCalData = new JSONObject();

        JSONObject fixedDepositCalData = new JSONObject();
        JSONObject savingCalData = new JSONObject();
        JSONObject loanCalData = new JSONObject();
        JSONObject morgageCalData = new JSONObject();

        financeCalData.put("fixedDepositCalData", fixedDepositCalData);
        financeCalData.put("savingCalData", savingCalData);
        financeCalData.put("loanCalData", loanCalData);
        financeCalData.put("morgageCalData", morgageCalData);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox(10);
        root.setStyle("-fx-background-image: url(https://www.hindustantimes.com/rf/image_size_960x540/HT/p2/2017/02/01/" +
                "Pictures/_b99fc474-e856-11e6-93cc-bb55973994db.jpg)");
        root.setAlignment(Pos.CENTER);

        Button fixedDepositCalculatorButton = new Button("Fixed deposit calculator");
        Button savingCalulatorButton = new Button("Saving calulator");
        Button loanCalculatorButton = new Button("Loan calculator");
        Button morgageCalulatorButton = new Button("Mortgage calulator");
        Button close = new Button("Close");

        fixedDepositCalculatorButton.setStyle("-fx-background-radius: 25em;-fx-font-weight: bold;");
        savingCalulatorButton.setStyle("-fx-background-radius: 25em;-fx-font-weight: bold;");
        loanCalculatorButton.setStyle("-fx-background-radius: 25em;-fx-font-weight: bold;");
        morgageCalulatorButton.setStyle("-fx-background-radius: 25em;-fx-font-weight: bold;");
        close.setStyle("-fx-background-radius: 25em;-fx-font-weight: bold;");

        root.getChildren().addAll(fixedDepositCalculatorButton, savingCalulatorButton, loanCalculatorButton,
                morgageCalulatorButton, close);

        Stage Stage = popUpNumberKeyBoard(primaryStage, root);
        close.setOnAction(event -> {
            primaryStage.close();
            Stage.close();
        });

        fixedDepositCalculatorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Group root = new Group();
                Scene secondScene = new Scene(root, 450, 300);
                TabPane tabPane = new TabPane();
                BorderPane mainPane = new BorderPane();

                mainPane.setCenter(tabPane);
                mainPane.prefHeightProperty().bind(secondScene.heightProperty());
                mainPane.prefWidthProperty().bind(secondScene.widthProperty());

                root.getChildren().add(mainPane);

                Stage newWindow = new Stage();
                newWindow.setTitle("fixed diposit calculater");
                newWindow.setScene(secondScene);

                addFvTabFixedDipositCal(tabPane, newWindow);
                addInterestTabFixedDipositCal(tabPane, newWindow);
                addPresentValueForFixedDepositCal(tabPane,newWindow);
                addTimePeriodForFixedDepositCal(tabPane,newWindow);
                addHelpTabForFixedDepositCal(tabPane,newWindow);

                newWindow.show();
                primaryStage.close();

            }

            private void addHelpTabForFixedDepositCal(TabPane tabPane,Stage newWindow) {
                Tab helpTab = new Tab("Help");
                helpTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                Pane helpPane = new Pane();
                Label helpText = new Label("we are here to solve your calcultions");
                helpText.setStyle("-fx-font-weight: bold;-fx-font: 20px Tahoma;");
                Label fixedDepositCalInstruction = new Label(
                        "The fixed deposite calculater is an application which is capability with solving \nfianancial " +
                        "calculations in fixed deposits such as ,\n *  Future value \n" +
                        "*  Present value\n* Time period to acheive the future value\n *  Interset rate \n The appliction " +
                        "providing you with four main tabs for the  usebility\n User can use relevant tabs for thier calculations " +
                        " in this calculater.\n The application was provided textfields to enter the amounts that user " +
                        "wishes.\n Enter the amounts through the vitual keybord and click the calculate button. \n" +
                        "The application is setup with dispalying the calculated results in the final texfield with\n" +
                        " the label after entering the amounts .\n If user wishes to go back please enter the back button.");
                 fixedDepositCalInstruction.setLayoutY(50);
                helpPane.getChildren().addAll(helpText,fixedDepositCalInstruction);
                helpPane.setLayoutY(150);
                helpTab.setContent(helpPane);
                tabPane.getTabs().add(helpTab);
            }

            private void addTimePeriodForFixedDepositCal(TabPane tabPane,Stage newWindow ) {
                Tab timePeriodTab = new Tab("Time period");
                GridPane timePeriodPane = new GridPane();
                timePeriodTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                timePeriodPane.setAlignment(Pos.CENTER);

                Label numberOfPeriodLabel = new Label("N(# of periods)");
                timePeriodPane.add(numberOfPeriodLabel, 0, 1);
                TextField numberOfPeriodTextField = new TextField();
                timePeriodPane.add(numberOfPeriodTextField, 1, 1);
                numberOfPeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfPeriodTextField);

                Label interestLabel = new Label("I/Y(interest)");
                timePeriodPane.add(interestLabel, 0, 2);
                TextField intersetTextField = new TextField();
                timePeriodPane.add(intersetTextField, 1, 2);
                intersetTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = intersetTextField);

                Label principalAmountLabel = new Label("Principle amount");
                timePeriodPane.add(principalAmountLabel, 0, 3);
                TextField principalAmountTextField = new TextField();
                timePeriodPane.add(principalAmountTextField, 1, 3);
                principalAmountTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = principalAmountTextField);

                Label futureValueLabel = new Label("Future value");
                timePeriodPane.add(futureValueLabel, 0, 4);
                TextField futureValueTextField = new TextField();
                timePeriodPane.add(futureValueTextField, 1, 4);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label timePeriodLabel = new Label("Time period");
                timePeriodPane.add(timePeriodLabel, 0, 7);
                TextField timePeriodTextField = new TextField();
                timePeriodPane.add(timePeriodTextField, 1, 7);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(numberOfPeriodTextField.getText());
                            double r = Double.parseDouble(intersetTextField.getText());
                            double p = Double.parseDouble(principalAmountTextField.getText());
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double test1 = Math.log10((a / p));
                            double test2 = n * (Math.log10(1 + ((r / 100) / n)));
                            double timePeriod = test1 / test2;
                            timePeriodTextField.setText(String.valueOf(df.format(timePeriod)));

                            JSONObject timePeriodData = new JSONObject();
                            timePeriodData.put("number of period", n);
                            timePeriodData.put("interset rate", r);
                            timePeriodData.put("present value",p);
                            timePeriodData.put("future value",a);
                            timePeriodData.put("time period",timePeriod);

                            JSONObject fixedDepositCalData = (JSONObject) financeCalData.get("fixedDepositCalData");
                            fixedDepositCalData.put("timePeriodData", timePeriodData);

                            persistData();
                        } catch (Exception e) {
                           // System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });

                timePeriodPane.add(calculateButton, 1, 8);
                timePeriodPane.add(backButton, 0, 8);
                timePeriodPane.setVgap(5);
                timePeriodPane.setHgap(5);

                timePeriodTab.setContent(timePeriodPane);
                tabPane.getTabs().add(timePeriodTab);
            }

            private void addPresentValueForFixedDepositCal(TabPane tabPane,Stage newWindow) {
                Tab presentValueTab = new Tab("Present value");
                presentValueTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");

                GridPane presentValPane = new GridPane();
                presentValPane.setAlignment(Pos.CENTER);
                Label numberOfMonthLabel = new Label("Nmonth)");
                presentValPane.add(numberOfMonthLabel, 0, 2);
                TextField numberOfMonthTextField = new TextField();
                presentValPane.add(numberOfMonthTextField, 1, 2);
                numberOfMonthTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfMonthTextField);

                Label interestRateLabel = new Label("Interest rate");
                presentValPane.add(interestRateLabel, 0, 3);
                TextField interestRateTextField = new TextField();
                presentValPane.add(interestRateTextField, 1, 3);
                interestRateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = interestRateTextField);

                Label timePeriodLabel = new Label("Time period");
                presentValPane.add(timePeriodLabel, 0, 4);
                TextField timePeriodTextField = new TextField();
                presentValPane.add(timePeriodTextField, 1, 4);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Label futureValueLabel = new Label("Future values");
                presentValPane.add(futureValueLabel, 0, 5);
                TextField futureValueTextField = new TextField();
                presentValPane.add(futureValueTextField, 1, 5);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label presentValueLabel = new Label("Present value");
                presentValPane.add(presentValueLabel, 0, 10);
                TextField presentValueTextField = new TextField();
                presentValPane.add(presentValueTextField, 1, 10);
                presentValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = presentValueTextField);

                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double n = Double.parseDouble(numberOfMonthTextField.getText());
                            double r = Double.parseDouble(interestRateTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double p = a / Math.pow((1 + ((r / 100) / n)), (n * t));
                            presentValueTextField.setText(String.valueOf(df.format(p)));

                            JSONObject presentValueData = new JSONObject();
                            presentValueData.put("futureValue", a);
                            presentValueData.put("nPeriod", n);
                            presentValueData.put("interestRate",r);
                            presentValueData.put("timePeriod",t);
                            presentValueData.put("presentValue",p);

                            JSONObject fixedDepositCalData = (JSONObject) financeCalData.get("fixedDepositCalData");
                            fixedDepositCalData.put("presentValueData", presentValueData);

                            persistData();
                        } catch (Exception e) {
                            //System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });
                presentValPane.add(calculateButton, 1, 13);
                presentValPane.add(backButton, 0, 13);

                presentValueTab.setContent(presentValPane);
                presentValPane.setVgap(5);
                presentValPane.setHgap(5);

                presentValueTab.setContent(presentValPane);
                tabPane.getTabs().add(presentValueTab);
            }

            private void addInterestTabFixedDipositCal(TabPane tabPane, Stage newWindow) {
                Tab interest = new Tab("Interest rate");
                interest.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                GridPane interestPane = new GridPane();
                interestPane.setAlignment(Pos.CENTER);

                Label futureValueLabel = new Label("Future value");
                interestPane.add(futureValueLabel, 0, 1);
                TextField futureValueTextField = new TextField();
                interestPane.add(futureValueTextField, 1, 1);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label numberOfPeriodLabel = new Label("N(# of periods)");
                interestPane.add(numberOfPeriodLabel, 0, 2);
                TextField numberOfPeriodTextField = new TextField();
                interestPane.add(numberOfPeriodTextField, 1, 2);
                numberOfPeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfPeriodTextField);

                Label presentValueLabel = new Label("Present value");
                interestPane.add(presentValueLabel, 0, 4);
                TextField presentValueTextField = new TextField();
                interestPane.add(presentValueTextField, 1, 4);
                presentValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = presentValueTextField);

                Label timePeriodLabel = new Label("Time period");
                interestPane.add(timePeriodLabel, 0, 3);
                TextField timePeriodTextFeild = new TextField();
                interestPane.add(timePeriodTextFeild, 1, 3);
                timePeriodTextFeild.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextFeild);

                Label interestLabel = new Label("Interest");
                interestPane.add(interestLabel, 0, 6);
                TextField interestTextField = new TextField();
                interestPane.add(interestTextField, 1, 6);
                interestTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = interestTextField);

                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");

                interestPane.add(backButton, 0, 7);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double n = Double.parseDouble(numberOfPeriodTextField.getText());
                            double t = Double.parseDouble(timePeriodTextFeild.getText());
                            double p = Double.parseDouble(presentValueTextField.getText());
                            double r = n * (Math.pow((a / p), (1 / (n * t))) - 1);
                            interestTextField.setText(String.valueOf(df.format(r)));

                            JSONObject interestData = new JSONObject();
                            interestData.put("futureValue", a);
                            interestData.put("nPeriod", n);
                            interestData.put("timePeriod",t);
                            interestData.put("presentValue",p);
                            interestData.put("intersetRate",r);

                            JSONObject fixedDepositCalData = (JSONObject) financeCalData.get("fixedDepositCalData");
                            fixedDepositCalData.put("interestData", interestData);

                            persistData();
                        } catch (Exception e) {
                        //   System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });

                interestPane.add(calculateButton, 1, 7);
                interestPane.setVgap(5);
                interestPane.setHgap(5);

                interest.setContent(interestPane);
                tabPane.getTabs().add(interest);
            }

            private void addFvTabFixedDipositCal(TabPane tabPane, Stage newWindow) {
                Tab fvTab = new Tab(" FV");
                GridPane FVPane = new GridPane();
                fvTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                FVPane.setAlignment(Pos.CENTER);

                Label numberOfPeriodLabel = new Label("Nperiods");
                FVPane.add(numberOfPeriodLabel, 3, 3);
                TextField numberOfPeriodTextField = new TextField();
                numberOfPeriodTextField.setId("Nperiods");
                FVPane.add(numberOfPeriodTextField, 4, 3);
                numberOfPeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfPeriodTextField);

                Label capitalLabel = new Label("Capital");
                FVPane.add(capitalLabel, 3, 4);
                TextField capitalTextField = new TextField();
                FVPane.add(capitalTextField, 4, 4);
                capitalTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = capitalTextField);

                Label interestRateLabel = new Label("Interest rate");
                FVPane.add(interestRateLabel, 3, 5);
                TextField interestRateTextField = new TextField();
                FVPane.add(interestRateTextField, 4, 5);
                interestRateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = interestRateTextField);

                Label timePeriodLabel = new Label("Time period");
                FVPane.add(timePeriodLabel, 3, 6);
                TextField timePeriodTextField = new TextField();
                FVPane.add(timePeriodTextField, 4, 6);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Label fvLabel = new Label("Fv");
                FVPane.add(fvLabel, 3, 10);
                TextField fvTextField = new TextField();
                FVPane.add(fvTextField, 4, 10);
                fvTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = fvTextField);

                Button calculaterButtern = new Button("Calculate");
                calculaterButtern.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculaterButtern.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(numberOfPeriodTextField.getText());
                            double p = Double.parseDouble(capitalTextField.getText());
                            double r = Double.parseDouble(interestRateTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double fv = p * Math.pow((1 + ((r / 100) / n)), (n * t));
                            fvTextField.setText(String.valueOf(df.format(fv)));

                            JSONObject fvData = new JSONObject();
                            fvData.put("Nperiods", n);
                            fvData.put("Capital", p);
                            fvData.put("interest",r);
                            fvData.put("timePeriod",t);
                            fvData.put("futureValue",fv);

                            JSONObject fixedDepositCalData = (JSONObject) financeCalData.get("fixedDepositCalData");
                            fixedDepositCalData.put("fvData", fvData);

                            persistData();
                        } catch (Exception e) {
                           // System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });

                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });

                FVPane.add(calculaterButtern, 4, 11);
                FVPane.add(backButton, 3, 11);
                FVPane.setVgap(5);
                FVPane.setHgap(5);

                fvTab.setContent(FVPane);
                tabPane.getTabs().add(fvTab);
            }
        });

        savingCalulatorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Group root = new Group();
                Scene secondScene = new Scene(root, 450, 300);

                TabPane tabPane = new TabPane();
                BorderPane mainPane = new BorderPane();
                mainPane.setCenter(tabPane);
                mainPane.prefHeightProperty().bind(secondScene.heightProperty());
                mainPane.prefWidthProperty().bind(secondScene.widthProperty());

                root.getChildren().add(mainPane);
                Stage newWindow = new Stage();
                newWindow.setTitle("savingCal Stage");
                newWindow.setScene(secondScene);

                addFvTabForSavingCal(tabPane, newWindow);
                addPrincipleAmountForSavingCal(tabPane, newWindow);
                addTimePeriodSavingCalTab(tabPane, newWindow);
                addPmtTabSavingCal(tabPane, newWindow);

                newWindow.show();
                primaryStage.close();

                addHelpTabForSavingCal(tabPane);
            }

            private void addHelpTabForSavingCal(TabPane tabPane) {
                Tab helpTab = new Tab("helpTab");
                helpTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                Pane helpPane = new Pane();
                Label helpText = new Label("we are here to solve your calcultions");
                helpText.setStyle("-fx-font-weight: bold;-fx-font: 20px Tahoma;");
                Label savingCalInstruction = new Label(
                        "The saving calculater is an application which is capability with solving\n fianancial " +
                        "calculations in fixed deposits such as ,\n*   Future value \n" +
                        "*   Present value\n *  Time period to acheive the future value \n*   PMT \n The appliction " +
                        "providing you with two main tabs for the  usebility.\n User can use relevant tabs for their calculations " +
                        " in this calculater.\n The application was provided textfields to enter the amounts that user " +
                        "wishes.\n Enter the amounts through the vitual keybord and click the calculate button \n" +
                        "The application is setup with dispalying the calculated results in the \nfinal texfield with" +
                        " the label after entering the amounts .\n If user wishes to go back please enter the back button.");
                savingCalInstruction.setLayoutY(25);
                helpPane.getChildren().addAll(helpText,savingCalInstruction);
                helpPane.setLayoutY(150);
                helpTab.setContent(helpPane);
                tabPane.getTabs().add(helpTab);
            }

            private void addTimePeriodSavingCalTab(TabPane tabPane, Stage newWindow) {
                Tab timeTab = new Tab("Time period");
                timeTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                GridPane timePeriodPane = new GridPane();
                timePeriodPane.setVgap(5);
                timePeriodPane.setHgap(5);

                timePeriodPane.setAlignment(Pos.CENTER);

                Label presentValueLabel = new Label("Present value");
                timePeriodPane.add(presentValueLabel, 0, 1);
                TextField presentValueTextField = new TextField();
                timePeriodPane.add(presentValueTextField, 1, 1);
                presentValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = presentValueTextField);

                Label numberOfPeriodLabel = new Label("N(# of periods)");
                timePeriodPane.add(numberOfPeriodLabel, 0, 2);
                TextField numberOfPeriodTextField = new TextField();
                timePeriodPane.add(numberOfPeriodTextField, 1, 2);
                numberOfPeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfPeriodTextField);

                Label interestLabel = new Label("I/Y(interest)");
                timePeriodPane.add(interestLabel, 0, 3);
                TextField interestTextField = new TextField();
                timePeriodPane.add(interestTextField, 1, 3);
                interestTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = interestTextField);

                Label pmtLabel = new Label("Pmt");
                timePeriodPane.add(pmtLabel, 0, 4);
                TextField pmtTextField = new TextField();
                timePeriodPane.add(pmtTextField, 1, 4);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Label totalFutureValueLabel = new Label("Total future value ");
                timePeriodPane.add(totalFutureValueLabel, 0, 5);
                TextField totalFutureValueTextField = new TextField();
                timePeriodPane.add(totalFutureValueTextField, 1, 5);
                totalFutureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = totalFutureValueTextField);

                Label timePeriodLabel = new Label("Time period");
                timePeriodPane.add(timePeriodLabel, 0, 7);
                TextField timePeriodTextField = new TextField();
                timePeriodPane.add(timePeriodTextField, 1, 7);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                timePeriodPane.add(calculateButton, 1, 8);
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {

                            double p = Double.parseDouble(presentValueTextField.getText());
                            double n = Double.parseDouble(numberOfPeriodTextField.getText());
                            double r = Double.parseDouble(interestTextField.getText());
                            double pmt = Double.parseDouble(pmtTextField.getText());
                            double fv = Double.parseDouble(totalFutureValueTextField.getText());
                            double y=(r/100)/n;
                            double m=(fv*y)+pmt;
                            double o=(y*p)+pmt;
                            double x= m/o;
                            double z=Math.log10(x) /Math.log10(1+y);
                            double t=z/n;


                           // double t = Math.log10((1 + (((r / 100) * fv) / pmt) / (Math.log10(1 + (r / 100)))* n));

                            timePeriodTextField.setText(String.valueOf(df.format(t)));
                            JSONObject timePeriodData = new JSONObject();
                            timePeriodData.put("presentValue", p);
                            timePeriodData.put("nPeriod", n);
                            timePeriodData.put("interestRate",r);
                            timePeriodData.put("pmt",pmt);
                            timePeriodData.put("futureValue",fv);
                            timePeriodData.put("timePeriod",t);

                            JSONObject  savingCalData= (JSONObject) financeCalData.get("savingCalData");
                            savingCalData.put("timePeriodData", timePeriodData);

                            persistData();
                        } catch (Exception e) {
                           // System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });
                timePeriodPane.add(backButton, 0, 8);

                timeTab.setContent(timePeriodPane);
                tabPane.getTabs().add(timeTab);
            }

            private void addPmtTabSavingCal(TabPane tabPane, Stage newWindow) {
                Tab pmtTab = new Tab("pmt");
                pmtTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                GridPane pmtPane = new GridPane();
                pmtPane.setVgap(5);
                pmtPane.setHgap(5);

                pmtPane.setAlignment(Pos.CENTER);

                Label numberOfPeriodLabel = new Label("N of months");
                pmtPane.add(numberOfPeriodLabel, 0, 1);
                TextField numberOfPeriodTextField = new TextField();
                pmtPane.add(numberOfPeriodTextField, 1, 1);
                numberOfPeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfPeriodTextField);

                Label rateLabel = new Label("Rate");
                pmtPane.add(rateLabel, 0, 2);
                TextField rateTextField = new TextField();
                pmtPane.add(rateTextField, 1, 2);
                rateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = rateTextField);

                Label timePeriodLabel = new Label("Time period");
                pmtPane.add(timePeriodLabel, 0, 3);
                TextField timePeriodTextField = new TextField();
                pmtPane.add(timePeriodTextField, 1, 3);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Label futureValueLabel = new Label("total Future values");
                pmtPane.add(futureValueLabel, 0, 4);
                TextField futureValueTextField = new TextField();
                pmtPane.add(futureValueTextField, 1, 4);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label presentValueLabel = new Label("Present value");
                pmtPane.add(presentValueLabel, 0, 5);
                TextField presentValueTextField = new TextField();
                pmtPane.add(presentValueTextField, 1, 5);
                presentValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = presentValueTextField);

                Label pmtLabel = new Label("Pmt");
                pmtPane.add(pmtLabel, 0, 6);
                TextField pmtTextField = new TextField();
                pmtPane.add(pmtTextField, 1, 6);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(numberOfPeriodTextField.getText());
                            double r = Double.parseDouble(rateTextField.getText());
                            double fv = Double.parseDouble(futureValueTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double p= Double.parseDouble(presentValueTextField.getText());
                            double y=(r/100)/n;
                            double z=Math.pow((1+y),(n*t));
                            double up=fv-(p*z);
                            double down=(z-1)/y;
                            double pmt=up/down;

                            pmtTextField.setText(String.valueOf(df.format(pmt)));

                            JSONObject pmtData = new JSONObject();
                            pmtData.put("numberOFmonth", n);
                            pmtData.put("interest", r);
                            pmtData.put("futureValue",fv);
                            pmtData.put("timePeriod",t);
                            pmtData.put("pmt",pmt);

                            JSONObject  savingCalData= (JSONObject) financeCalData.get("savingCalData");
                            savingCalData.put("pmtData", pmtData);

                            persistData();
                        } catch (Exception e) {
                         //   System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });

                pmtPane.add(calculateButton, 1, 7);
                pmtPane.add(backButton, 0, 7);
                pmtTab.setContent(pmtPane);
                tabPane.getTabs().add(pmtTab);
            }

            private void addPrincipleAmountForSavingCal(TabPane tabPane, Stage newWindow) {

                Tab principleAmountTab = new Tab("Principle amount");
                principleAmountTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");

                GridPane principalAmount = new GridPane();
                principalAmount.setVgap(5);
                principalAmount.setHgap(5);

                principalAmount.setAlignment(Pos.CENTER);
                Label rateLabel = new Label("Rate");
                principalAmount.add(rateLabel, 0, 1);
                TextField rateTextField = new TextField();
                principalAmount.add(rateTextField, 1, 1);
                rateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = rateTextField);

                Label nPeriodLabel = new Label("N(# of periods)");
                principalAmount.add(nPeriodLabel, 0, 2);
                TextField nPeriodTextField = new TextField();
                principalAmount.add(nPeriodTextField, 1, 2);
                nPeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = nPeriodTextField);

                Label timePeriodLabel = new Label("Time period");
                principalAmount.add(timePeriodLabel, 0, 3);
                TextField timePeriodTextField = new TextField();
                principalAmount.add(timePeriodTextField, 1, 3);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Label pmtLabel = new Label("pmt");
                principalAmount.add(pmtLabel, 0, 4);
                TextField pmtTextField = new TextField();
                principalAmount.add(pmtTextField, 1, 4);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Label futureValueLabel = new Label("total Future value");
                principalAmount.add(futureValueLabel, 0, 5);
                TextField futureValueTextField = new TextField();
                principalAmount.add(futureValueTextField, 1, 5);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label presentValueLabel = new Label("Present value");
                principalAmount.add(presentValueLabel, 0, 9);
                TextField presentValueTextField = new TextField();
                principalAmount.add(presentValueTextField, 1, 9);
                presentValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = presentValueTextField);

                Button calculaterButton = new Button("Calculate");
                calculaterButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculaterButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double r = Double.parseDouble(rateTextField.getText());
                            double n = Double.parseDouble(nPeriodTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double pmt = Double.parseDouble(pmtTextField.getText());
                            double fv = Double.parseDouble(futureValueTextField.getText());
                            double y=(r/100)/n;
                            double z=Math.pow((1+y),(n*t));
                             double A=(pmt*(z-1))/y;
                             double presentVal=(fv-A)/z;
                            presentValueTextField.setText(String.valueOf(df.format(presentVal)));

                            JSONObject presentValueData = new JSONObject();
                            presentValueData.put("interestRate", r);
                            presentValueData.put("numberOFmonth", n);
                            presentValueData.put("timePeriod",t);
                            presentValueData.put("futureValue",fv);
                            presentValueData.put("presentValue",presentVal);

                            JSONObject  savingCalData= (JSONObject) financeCalData.get("savingCalData");
                            savingCalData.put("presentValueData", presentValueData);

                            persistData();
                        } catch (Exception e) {
                           // System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                principalAmount.add(calculaterButton, 1, 10);
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });
                principalAmount.add(backButton, 0, 10);

                principleAmountTab.setContent(principalAmount);
                tabPane.getTabs().add(principleAmountTab);
            }

            private void addFvTabForSavingCal(TabPane tabPane, Stage newWindow) {
                Tab fvTab = new Tab("total fv");
                fvTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                GridPane fvGridPane = new GridPane();
                fvGridPane.setVgap(5);
                fvGridPane.setHgap(5);
                fvGridPane.setAlignment(Pos.CENTER);
                fvGridPane.setStyle("-fx-background-image: url(https://www.hindustantimes.com/rf/image_size_960x540/HT/" +
                        "p2/2017/02/01/Pictures/_b99fc474-e856-11e6-93cc-bb55973994db.jpg");

                Label nPeriodsLabel = new Label("Nperiods");
                TextField nPeriodsTextField = new TextField();
                nPeriodsTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = nPeriodsTextField);
                fvGridPane.add(nPeriodsLabel, 0, 1);
                fvGridPane.add(nPeriodsTextField, 1, 1);

                Label presentValueLabel = new Label("Present value");
                TextField presentValueTextField = new TextField();
                presentValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = presentValueTextField);
                fvGridPane.add(presentValueLabel, 0, 2);
                fvGridPane.add(presentValueTextField, 1, 2);

                Label interestRateLabel = new Label("Interest rate");
                TextField interestRateTextField = new TextField();
                interestRateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = interestRateTextField);
                fvGridPane.add(interestRateLabel, 0, 3);
                fvGridPane.add(interestRateTextField, 1, 3);

                Label timePeriodLabel = new Label("Time period");
                TextField timePeriodTextField = new TextField();
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);
                fvGridPane.add(timePeriodLabel, 0, 4);
                fvGridPane.add(timePeriodTextField, 1, 4);

                Label pmtLabel = new Label("pmt");
                TextField pmtTextField = new TextField();
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);
                fvGridPane.add(pmtLabel, 0, 5);
                fvGridPane.add(pmtTextField, 1, 5);

                Label futureValueLabel = new Label("total Future value");
                TextField futureValueTextField = new TextField();
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);
                fvGridPane.add(futureValueLabel, 0, 9);
                fvGridPane.add(futureValueTextField, 1, 9);

                Button calculaterButtern = new Button("Calculate");
                calculaterButtern.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculaterButtern.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(nPeriodsTextField.getText());
                            double r = Double.parseDouble(interestRateTextField.getText());
                            double p = Double.parseDouble(presentValueTextField.getText());
                            double pmt = Double.parseDouble(pmtTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double InterestWithFutureValue = p * Math.pow((1 + ((r / 100) / n)), (n * t));
                            double futureValueOfSeries = (pmt * ((Math.pow((1 + ((r / 100) / n)), (n * t)) - 1) / ((r / 100) / n)));
                            double totalFv = InterestWithFutureValue + futureValueOfSeries;
                            futureValueTextField.setText(String.valueOf(df.format(totalFv)));

                            JSONObject futureValueData = new JSONObject();
                            futureValueData.put("numberOFmonth", n);
                            futureValueData.put("intersetRate", r);
                            futureValueData.put("presentValue",p);
                            futureValueData.put("pmt",pmt);
                            futureValueData.put("totalFutureVal",totalFv);

                            JSONObject  savingCalData= (JSONObject) financeCalData.get("savingCalData");
                            savingCalData.put("futureValueData", futureValueData);

                            persistData();
                        } catch (Exception e) {
                          //  System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });

                fvGridPane.add(calculaterButtern, 1, 10);
                fvGridPane.add(backButton, 0, 10);
                fvTab.setContent(fvGridPane);
                tabPane.getTabs().addAll(fvTab);
            }
        });

        loanCalculatorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                TabPane tabPane = new TabPane();
                Group root = new Group();
                BorderPane mainPane = new BorderPane();
                Scene secondScene = new Scene(root, 450, 300);

                mainPane.setCenter(tabPane);
                mainPane.prefHeightProperty().bind(secondScene.heightProperty());
                mainPane.prefWidthProperty().bind(secondScene.widthProperty());
                mainPane.setCenter(tabPane);
                mainPane.prefHeightProperty().bind(secondScene.heightProperty());
                mainPane.prefWidthProperty().bind(secondScene.widthProperty());

                root.getChildren().add(mainPane);

                Stage newWindow = new Stage();
                newWindow.setTitle("loanCalStage Stage");
                newWindow.setScene(secondScene);

                addPmtTabForLoanCal(tabPane, newWindow);
                addTimePeriodTabForLoanCal(tabPane, newWindow);
                addHelpTabForLoanCal(tabPane);

                newWindow.show();
                primaryStage.close();
            }

            private void addHelpTabForLoanCal(TabPane tabPane) {
                Tab helpTab = new Tab("HelpTab");
                helpTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                Pane helpPane = new Pane();
                Label helpText = new Label("we are here to solve your calcultions");
                helpText.setStyle("-fx-font-weight: bold;-fx-font: 20px Tahoma;");

                Label loanCalculaterInstructions = new Label(
                        "The loan calculater is an application which is capability with solving\n fianancial " +
                        "calculations aout your loan amounts such as , \n* Time period to acheive the future value" +
                        "\n* the PMT \n The appliction " +
                        "providing you with two main tabs for the  usebility.\n User can use relevant tabs for thier calculations " +
                        " in this calculater'\n The application was provided textfields to enter the amounts that user " +
                        "wishes.\n Enter the amounts through the vitual keybord and click the calculate button \n" +
                        "The application is setup with dispalying the calculated results in the\n final texfield with" +
                        " the label after entering the amounts .\n If user wishes to go back please enter the back button.");

                loanCalculaterInstructions.setLayoutY(27);
                helpPane.getChildren().addAll(helpText,loanCalculaterInstructions);
                helpPane.setLayoutY(150);
                helpTab.setContent(helpPane);
                tabPane.getTabs().add(helpTab);
            }

            private void addTimePeriodTabForLoanCal(TabPane tabPane, Stage newWindow) {
                Tab timePeriodTab = new Tab("Time period");
                GridPane timePeriodPane = new GridPane();
                timePeriodPane.setVgap(5);
                timePeriodPane.setHgap(5);
                timePeriodTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                timePeriodPane.setAlignment(Pos.CENTER);
                Label numberOfPeriodLabel = new Label("N of months");
                timePeriodPane.add(numberOfPeriodLabel, 0, 1);
                TextField numberOfMonthsTextField = new TextField();
                timePeriodPane.add(numberOfMonthsTextField, 1, 1);
                numberOfMonthsTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfMonthsTextField);

                Label rateLabel = new Label("Rate");
                timePeriodPane.add(rateLabel, 0, 2);
                TextField rateTextField = new TextField();
                timePeriodPane.add(rateTextField, 1, 2);
                rateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = rateTextField);

                Label pmtLabel = new Label("pmt");
                timePeriodPane.add(pmtLabel, 0, 3);
                TextField pmtTextField = new TextField();
                timePeriodPane.add(pmtTextField, 1, 3);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Label futureValueLabel = new Label("Future value");
                timePeriodPane.add(futureValueLabel, 0, 4);
                TextField futureValueTextField = new TextField();
                timePeriodPane.add(futureValueTextField, 1, 4);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label loanAmountLabel = new Label("Loan amount");
                timePeriodPane.add(loanAmountLabel, 0, 5);
                TextField loanAmountTextField = new TextField();
                timePeriodPane.add(loanAmountTextField, 1, 5);
                loanAmountTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = loanAmountTextField);

                Label timePeriodLabel = new Label("Time period");
                timePeriodPane.add(timePeriodLabel, 0, 8);
                TextField timePeriodTextField = new TextField();
                timePeriodPane.add(timePeriodTextField, 1, 8);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(numberOfMonthsTextField.getText());
                            double r = Double.parseDouble(rateTextField.getText());
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double pmt = Double.parseDouble(pmtTextField.getText());
                            double loan = Double.parseDouble(loanAmountTextField.getText());
                            double timePeriod = Math.log((1 - (((r / 100) * loan) / pmt)) / (n * (Math.log(1 + (r / 100)))));

                            timePeriodTextField.setText(String.valueOf(df.format(timePeriod)));

                            JSONObject timePeriodData = new JSONObject();
                            timePeriodData.put("numberOFmonth", n);
                            timePeriodData.put("intersetRate", r);
                            timePeriodData.put("futureVal",a);
                            timePeriodData.put("pmt",pmt);
                            timePeriodData.put("loan",loan);
                            timePeriodData.put("timePeriod",timePeriod);

                            JSONObject  loanCalData= (JSONObject) financeCalData.get("loanCalData");
                            loanCalData.put("timePeriodData", timePeriodData);

                            persistData();
                        } catch (Exception e) {
                          //  System.out.println("please fill every text boxses to get the answer");
                        }
                    }

                });
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });
                timePeriodPane.add(backButton, 0, 9);
                timePeriodPane.add(calculateButton, 1, 9);

                timePeriodTab.setContent(timePeriodPane);
                tabPane.getTabs().addAll(timePeriodTab);

            }

            private void addPmtTabForLoanCal(TabPane tabPane, Stage newWindow) {
                Tab pmtTab = new Tab("pmt");
                pmtTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                GridPane pmtPane = new GridPane();
                pmtPane.setVgap(5);
                pmtPane.setHgap(5);

                pmtPane.setAlignment(Pos.CENTER);

                Label numberOfMonthLabel = new Label("N of months");
                pmtPane.add(numberOfMonthLabel, 0, 1);
                TextField numberOfMonthTextField = new TextField();
                pmtPane.add(numberOfMonthTextField, 1, 1);
                numberOfMonthTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfMonthTextField);

                Label rateLabel = new Label("Rate");
                pmtPane.add(rateLabel, 0, 2);
                TextField rateTextField = new TextField();
                pmtPane.add(rateTextField, 1, 2);
                rateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = rateTextField);

                Label timePeriodLabel = new Label("Time period");
                pmtPane.add(timePeriodLabel, 0, 3);
                TextField timePeriodTextField = new TextField();
                pmtPane.add(timePeriodTextField, 1, 3);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Label futureValueLabel = new Label("Future values");
                pmtPane.add(futureValueLabel, 0, 4);
                TextField futureValueTextField = new TextField();
                pmtPane.add(futureValueTextField, 1, 4);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label pmtLabel = new Label("pmtTab");
                pmtPane.add(pmtLabel, 0, 7);
                TextField pmtTextField = new TextField();
                pmtPane.add(pmtTextField, 1, 7);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                        try {
                            double n = Double.parseDouble(numberOfMonthTextField.getText());
                            double r = Double.parseDouble(rateTextField.getText());
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double test1 = a * ((r / 100) / n) * (Math.pow((1 + ((r / 100) / n)), (n * t)));
                            double test2 = (Math.pow((1 + ((r / 100) / n)), (n * t)) - 1);
                            double pmt = test1 / test2;
                            pmtTextField.setText(String.valueOf(df.format(pmt)));

                            JSONObject pmtData = new JSONObject();
                            pmtData.put("numberOfmonth", n);
                            pmtData.put("intersetRate", r);
                            pmtData.put("futureVal",a);
                            pmtData.put("timrPeriod",t);
                            pmtData.put("pmt",pmt);

                            JSONObject  loanCalData= (JSONObject) financeCalData.get("loanCalData");
                            loanCalData.put("pmtData", pmtData);

                            persistData();
                        } catch (Exception e) {
                           // System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });

                pmtPane.add(calculateButton, 1, 8);
                pmtPane.add(backButton, 0, 8);
                pmtTab.setContent(pmtPane);
                tabPane.getTabs().addAll(pmtTab);
            }
        });

        morgageCalulatorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TabPane tabPane = new TabPane();
                Group root = new Group();
                BorderPane mainPane = new BorderPane();
                Scene secondScene = new Scene(root, 450, 300);

                mainPane.setCenter(tabPane);
                mainPane.prefHeightProperty().bind(secondScene.heightProperty());
                mainPane.prefWidthProperty().bind(secondScene.widthProperty());
                root.getChildren().add(mainPane);

                Stage newWindow = new Stage();
                newWindow.setTitle("mortgageCal Stage");
                newWindow.setScene(secondScene);

                addPmtForMotgageCal(tabPane, newWindow);
                addTimePeriodMorgatgeCal(tabPane, newWindow);
                addHelpTabForMotgageCal(tabPane);

                newWindow.show();
                primaryStage.close();
            }

            private void addHelpTabForMotgageCal(TabPane tabPane) {
                Tab helpTab = new Tab("HelpTab");
                helpTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                Pane helpPane = new Pane();
                Label helpText = new Label("we are here to solve your calcultions");
                helpText.setStyle("-fx-font-weight: bold;-fx-font: 20px Tahoma;");

                Label mortgageCalculaterInstructions = new Label(
                        "The mortageg calculater is an application which is capability with solving\n fianancial " +
                        "calculations in mortgage such as ,\n*   Time period to acheive the future value\n*  PMT\n The appliction " +
                        "providing you with two main tabs for the  usebility\n User can use relevant tabs for their calculations " +
                        " in this calculater\n The application was provided textfields to enter the amounts that user " +
                        "wishes\n Enter the amounts through the vitual keybord and click the calculate button .\n" +
                        "The application is setup with dispalying the calculated results in the final\n texfield with" +
                        " the label after entering the amounts .\n If user wishes to go back please enter the back button.");
                mortgageCalculaterInstructions.setLayoutY(27);

                helpPane.getChildren().addAll(helpText,mortgageCalculaterInstructions);
                helpPane.setLayoutY(150);
                helpTab.setContent(helpPane);
                tabPane.getTabs().add(helpTab);
            }

            private void addTimePeriodMorgatgeCal(TabPane tabPane, Stage newWindow) {
                Tab timePeriodTab = new Tab("Time period");
                timePeriodTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");


                GridPane timePeriodPane = new GridPane();
                timePeriodPane.setVgap(5);
                timePeriodPane.setHgap(5);

                timePeriodPane.setAlignment(Pos.CENTER);
                Label numberOfMonthsLabel = new Label("N of months");
                timePeriodPane.add(numberOfMonthsLabel, 0, 1);
                TextField numberOfMothsTextField = new TextField();
                timePeriodPane.add(numberOfMothsTextField, 1, 1);
                numberOfMothsTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numberOfMothsTextField);

                Label rateLabel = new Label("Rate");
                timePeriodPane.add(rateLabel, 0, 2);
                TextField rateTextField = new TextField();
                timePeriodPane.add(rateTextField, 1, 2);
                rateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = rateTextField);


                Label pmtLabel = new Label("pmt");
                timePeriodPane.add(pmtLabel, 0, 3);
                TextField pmtTextField = new TextField();
                timePeriodPane.add(pmtTextField, 1, 3);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Label futureValueLabel = new Label("Future value");
                timePeriodPane.add(futureValueLabel, 0, 4);
                TextField futureValueTextField = new TextField();
                timePeriodPane.add(futureValueTextField, 1, 4);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label timePeriodLabel = new Label("Time period");
                TextField timePeriodTextFiled = new TextField();
                timePeriodPane.add(timePeriodLabel, 0, 7);
                timePeriodPane.add(timePeriodTextFiled, 1, 7);
                timePeriodTextFiled.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextFiled);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color:#bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(numberOfMothsTextField.getText());
                            double r = Double.parseDouble(rateTextField.getText());
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double pmt = Double.parseDouble(pmtTextField.getText());
                            double timePeriod = Math.log((1 - (((r / 100) * a) / pmt)) / (n * (Math.log(1 + (r / 100)))));
                            timePeriodTextFiled.setText(String.valueOf(df.format(timePeriod)));

                            JSONObject timePeriodData = new JSONObject();
                            timePeriodData.put("numberOFmonth", n);
                            timePeriodData.put("intersetRate", r);
                            timePeriodData.put("futureVal",a);
                            timePeriodData.put("pmt",pmt);
                            timePeriodData.put("timePeriod",timePeriod);

                            JSONObject  morgageCalData= (JSONObject) financeCalData.get("loanCalData");
                            morgageCalData.put("timePeriodData", timePeriodData);

                            persistData();
                        } catch (Exception e) {
                            //System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });
                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });
                timePeriodPane.add(calculateButton, 1, 8);
                timePeriodPane.add(backButton, 0, 8);

                timePeriodTab.setContent(timePeriodPane);
                tabPane.getTabs().addAll(timePeriodTab);
            }

            private void addPmtForMotgageCal(TabPane tabPane, Stage newWindow) {
                Tab pmtTab = new Tab("pmt");
                pmtTab.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
                GridPane pmtPane = new GridPane();
                pmtPane.setVgap(5);
                pmtPane.setHgap(5);

                pmtPane.setAlignment(Pos.CENTER);

                Label numberOfMonthsLabel = new Label("N of months");
                pmtPane.add(numberOfMonthsLabel, 0, 1);
                TextField numerOfMOnthsTextField = new TextField();
                pmtPane.add(numerOfMOnthsTextField, 1, 1);
                numerOfMOnthsTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = numerOfMOnthsTextField);

                Label rateLabel = new Label("Rate");
                pmtPane.add(rateLabel, 0, 2);
                TextField rateTextField = new TextField();
                pmtPane.add(rateTextField, 1, 2);
                rateTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = rateTextField);

                Label timePeriodLabel = new Label("Time period");
                pmtPane.add(timePeriodLabel, 0, 3);
                TextField timePeriodTextField = new TextField();
                pmtPane.add(timePeriodTextField, 1, 3);
                timePeriodTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = timePeriodTextField);

                Label futureValueLabel = new Label("Future values");
                pmtPane.add(futureValueLabel, 0, 4);
                TextField futureValueTextField = new TextField();
                pmtPane.add(futureValueTextField, 1, 4);
                futureValueTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = futureValueTextField);

                Label pmtLabel = new Label("pmt");
                pmtPane.add(pmtLabel, 0, 7);
                TextField pmtTextField = new TextField();
                pmtPane.add(pmtTextField, 1, 7);
                pmtTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> tx = pmtTextField);

                Button calculateButton = new Button("Calculate");
                calculateButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                calculateButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            double n = Double.parseDouble(numerOfMOnthsTextField.getText());
                            double r = Double.parseDouble(rateTextField.getText());
                            double a = Double.parseDouble(futureValueTextField.getText());
                            double t = Double.parseDouble(timePeriodTextField.getText());
                            double test1 = a * ((r / 100) / n) * (Math.pow((1 + ((r / 100) / n)), (n * t)));
                            double test2 = (Math.pow((1 + ((r / 100) / n)), (n * t)) - 1);
                            double pmt = test1 / test2;
                            pmtTextField.setText(String.valueOf(df.format(pmt)));

                            JSONObject pmtData = new JSONObject();
                            pmtData.put("numberOfmonth", n);
                            pmtData.put("intersetRate", r);
                            pmtData.put("futureVal",a);
                            pmtData.put("timrPeriod",t);
                            pmtData.put("pmt",pmt);

                            JSONObject morgageCalData= (JSONObject) financeCalData.get("morgageCalData");
                            morgageCalData.put("pmtData", pmtData);

                            persistData();
                        } catch (Exception e) {
                           // System.out.println("please fill every text boxses to get the answer");
                        }
                    }
                });

                Button backButton = new Button("Back");
                backButton.setStyle("-fx-background-color: #bfb18a;-fx-background-radius: 25em;-fx-font-weight: bold;");
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.show();
                        newWindow.close();
                    }
                });
                pmtPane.add(calculateButton, 1, 8);
                pmtPane.add(backButton, 0, 8);

                pmtTab.setContent(pmtPane);
                tabPane.getTabs().addAll(pmtTab);
            }

        });
    }

    private void persistData() {
        try (FileWriter file = new FileWriter("c:\\Users\\rashfa\\financialCalculator\\data.json")) {
            file.write(financeCalData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage popUpNumberKeyBoard(Stage primaryStage, VBox root) {
        FlowPane keyboardFloepane = new FlowPane();
        keyboardFloepane.setStyle("-fx-background-color: #bfb18a;-fx-font-weight: bold;");
        Stage keyboardStage = new Stage();
        Scene scene1 = new Scene(keyboardFloepane, 180, 90);
        keyboardStage.setScene(scene1);
        Scene scene = new Scene(root, 450, 250);
        primaryStage.setScene(scene);

        Button buttern0 = new Button("0");
        Button buttern1 = new Button("1");
        Button buttern2 = new Button("2");
        Button buttern3 = new Button("3");
        Button buttern4 = new Button("4");
        Button buttern5 = new Button("5");
        Button buttern6 = new Button("6");
        Button buttern7 = new Button("7");
        Button buttern8 = new Button("8");
        Button buttern9 = new Button("9");
        Button decimalPoint = new Button(".");
        Button minusPoint = new Button("-");

        buttern0.setOnAction(event -> tx.setText(tx.getText() + "0"));
        buttern1.setOnAction(event -> tx.setText(tx.getText() + "1"));
        buttern2.setOnAction(event -> tx.setText(tx.getText() + "2"));
        buttern3.setOnAction(event -> tx.setText(tx.getText() + "3"));
        buttern4.setOnAction(event -> tx.setText(tx.getText() + "4"));
        buttern5.setOnAction(event -> tx.setText(tx.getText() + "5"));
        buttern6.setOnAction(event -> tx.setText(tx.getText() + "6"));
        buttern7.setOnAction(event -> tx.setText(tx.getText() + "7"));
        buttern8.setOnAction(event -> tx.setText(tx.getText() + "8"));
        buttern9.setOnAction(event -> tx.setText(tx.getText() + "9"));
        decimalPoint.setOnAction(event -> tx.setText(tx.getText() + "."));
        minusPoint.setOnAction(event -> tx.setText(tx.getText() + "-"));

        Button delete = new Button("delete");
        delete.setMinWidth(12);
        keyboardFloepane.setAlignment(Pos.CENTER);
        delete.setOnAction(event ->
                tx.setText(tx.getText().substring(0, tx.getText().length() == 0 ? 0 : tx.getText().length() - 1)));

        keyboardFloepane.getChildren().addAll(buttern0, buttern1, buttern2, buttern3, buttern4, buttern5, buttern6,
                buttern7, buttern8, buttern9, decimalPoint,minusPoint, delete);

        primaryStage.show();
        keyboardStage.setX(150);
        keyboardStage.setY(200);
        keyboardStage.setWidth(300);
        keyboardStage.setHeight(100);
        keyboardStage.show();
        keyboardStage.setTitle("keyboard");
        return keyboardStage;
    }
}


