package main.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.model.Client;
import main.model.InputSelection;
import main.model.Server;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    private int simulationTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int numberQueues;
    private int numberClients;
    private Scheduler scheduler;
    private InputSelection currentTestOption;

    private Label welcomeText;
    int lock1=0,lock2=0,lock3=0, lockUserIn=0;
    @FXML
    private TextField numberOfClients;

    private File outputFile;

    private FileWriter fileWriter;

    @FXML
    private TextField numberOfQueues;
    @FXML
    private TextField tSim;
    @FXML
    private TextField tArrMin;
    @FXML
    private TextField tArrMax;
    @FXML
    private TextField tSerMin;
    @FXML
    private TextField tSerMax;
    @FXML
    private TextFlow scrollPane;
    @FXML
    private TextArea finalData;
    @FXML
    protected void onFirstButtonClick(MouseEvent mouseEvent) {
        try {
            if(lock1==0) {
                lock2=1;
                lock3=1;
                lockUserIn=1;
                currentTestOption = InputSelection.Test1;
                Text newtext = new Text("");
                Platform.runLater(() -> {
                    scrollPane.getChildren().clear();
                });
                finalData.setText("");
                getGivenInputData();
            }

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
        }
    }

    @FXML
    protected void onUserInput(MouseEvent mouseEvent){
      try {
          if (lockUserIn == 0) {
              lock2 = 1;
              lock3 = 1;
              lock1 = 1;
              numberClients = Integer.parseInt(numberOfClients.getText());
              numberQueues = Integer.parseInt(numberOfQueues.getText());
              simulationTime = Integer.parseInt(tSim.getText());
              minArrivalTime = Integer.parseInt(tArrMin.getText());
              maxArrivalTime = Integer.parseInt(tArrMax.getText());
              minServiceTime = Integer.parseInt(tSerMin.getText());
              maxServiceTime = Integer.parseInt(tSerMax.getText());
              Text newtext = new Text("");
              Platform.runLater(() -> {
                  scrollPane.getChildren().clear();
              });
              finalData.setText("");
              scheduler = new Scheduler(numberQueues, numberClients, simulationTime, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
              scheduler.giveControlData(this);
              Thread mainThread = new Thread(scheduler);
              mainThread.start();
          }
      }catch(Exception e){
          System.out.println("USER INPUT PROBLEM");
          System.out.println(e.getMessage());
          finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
      }
    }
    @FXML
    protected void onSecondButtonClick(MouseEvent mouseEvent) {
        try {
            if(lock2==0) {
                lock1=1;
                lock3=1;
                lockUserIn=1;
                currentTestOption = InputSelection.Test2;
                Text newtext = new Text("");
                Platform.runLater(() -> {
                    scrollPane.getChildren().clear();
                });
                finalData.setText("");
                getGivenInputData();

                    outputFile = new File("result.txt");

                    if(outputFile.createNewFile()) {
                        System.out.println("File created\n");
                    } else {
                        System.out.println("File already exists\n");
                    }

                    fileWriter = new FileWriter("result.txt");
                    fileWriter.write("LOG OF EVENTS\n\n");


            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
        }
    }
    @FXML
    protected void onThirdButtonClick(MouseEvent mouseEvent) {
        try {
            if(lock3==0) {
                lock2=1;
                lock1=1;
                lockUserIn=1;
                currentTestOption = InputSelection.Test3;
                Text newtext = new Text("");
                Platform.runLater(() -> {
                    scrollPane.getChildren().clear();
                });
                finalData.setText("");
                getGivenInputData();

                outputFile = new File("result.txt");

                if(outputFile.createNewFile()) {
                    System.out.println("File created\n");
                } else {
                    System.out.println("File already exists\n");
                }

                fileWriter = new FileWriter("result.txt");
                fileWriter.write("LOG OF EVENTS\n\n");
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
        }
    }
    public void outputSimData(AtomicInteger simulationTime, ArrayList<Server> queues, ArrayList<Client> clients) {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Text newtext=new Text("Time: " + (simulationTime.get()) + "\n");
                    Platform.runLater(() -> {scrollPane.getChildren().add(newtext);});
                    Text newtext2=new Text("Waiting client list: " + Client.printAllClientsData(clients) + "\n");
                    Platform.runLater(() -> { scrollPane.getChildren().add(newtext2);});
                    for (int i = 0; i < queues.size(); i++) {
                        Text newtext3 = new Text(queues.get(i).outputQueueData() + "\n");
                        Platform.runLater(() -> {
                            scrollPane.getChildren().add(newtext3);
                        });
                    }
                   scrollPane.getChildren().clear();
                }
            });




/*
            Text newtext=new Text("Time: " + (simulationTime.get()) + "\n");
            Platform.runLater(() -> {scrollPane.getChildren().add(newtext);});
            Text newtext2=new Text("Waiting client list: " + Client.printAllClientsData(clients) + "\n");
            Platform.runLater(() -> { scrollPane.getChildren().add(newtext2);});
            for (int i = 0; i < queues.size(); i++) {
                Text newtext3 = new Text(queues.get(i).outputQueueData() + "\n");
                Platform.runLater(() -> {
                    scrollPane.getChildren().add(newtext3);
                });
            }

 */

        }catch(Exception e){
            System.out.println("update data problem");
            System.out.println(e.getMessage());
            finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
        }
    }
    public void simResultDisplay(double averageWaitingTime, int highestTickTime, double averageServiceTime , int noOfMAxClients) {
       try{
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        finalData.appendText("Average waiting time: " + decimalFormat.format(averageWaitingTime)+ "\n") ;
        finalData.appendText("Peak time : " + highestTickTime + "\n");
        finalData.appendText("Average service time: " + decimalFormat.format(averageServiceTime)+ "\n");
        lock1=0;
        lock2=0;
        lock3=0;
        lockUserIn=0;
       }catch(Exception e){
           System.out.println("simResult problem");
           finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
       }

    }
    void getGivenInputData() {
        if (currentTestOption.equals(InputSelection.Test1)) {
            simulationTime = 60;
            minArrivalTime = 2;
            maxArrivalTime = 30;
            minServiceTime = 2;
            maxServiceTime = 4;
            numberClients = 4;
            numberQueues = 2;
        } else if (currentTestOption.equals(InputSelection.Test2)) {
            simulationTime = 60;
            minArrivalTime = 2;
            maxArrivalTime = 40;
            minServiceTime = 1;
            maxServiceTime = 7;
            numberClients = 50;
            numberQueues = 5;
        } else if (currentTestOption.equals(InputSelection.Test3)) {
            simulationTime = 200;
            minArrivalTime = 10;
            maxArrivalTime = 100;
            minServiceTime = 3;
            maxServiceTime = 9;
            numberClients = 1000;
            numberQueues = 20;
        }
        printDefault();
        scheduler = new Scheduler(numberQueues, numberClients, simulationTime, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
        scheduler.giveControlData(this);
        Thread mainThread = new Thread(scheduler);
        mainThread.start();
    }
    private void printDefault() {
        try {
            for (int i = 0; i < numberQueues; i++) {
                Text newtext3=new Text("Queue " + i + ": closed\n");
                Platform.runLater(() -> {scrollPane.getChildren().add(newtext3);});
            }
        }catch(Exception e){
            System.out.println("printDefault problem");
            finalData.setText("USER INPUT PROBLEM\n" +e.getMessage());
        }
    }

     void writeUpdateInFile(AtomicInteger simulationTime, ArrayList<Server> queues, ArrayList<Client> clients) {
        try {
            fileWriter.write("Time: " + (simulationTime.get()) + "\n");

            fileWriter.write("Waiting client list: " + Client.printAllClientsData(clients) + "\n");

            for (int i = 0; i < queues.size(); i++) {
                fileWriter.write(queues.get(i).outputQueueData() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Could not write in file\n");
        }
    }

    void writeFinalFeedBackInFile(double averageWaitingTime, int highestTickTime, double averageServiceTime, int noOfMAxClients) {
        try {
            fileWriter.write("Average waiting time: " + averageWaitingTime + "\n");

            fileWriter.write("Peak hour: " + highestTickTime + "\n");

            fileWriter.write("Average service time: " + averageServiceTime + "\n");

            fileWriter.write("\n\n");
        } catch (Exception e) {
            System.out.println("Could not write in file\n");
        }
    }
}