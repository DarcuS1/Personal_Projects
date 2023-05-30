package main.controller;

import main.model.Client;
import main.model.Server;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler implements  Runnable{
    private ArrayList<Server> queues;
    private ArrayList<Client> clients;
    private int simulationTime;
    private int minArrivalT;
    private int maxArrivalT;
    private int minServiceT;
    private int maxServiceT;
    private int noOfQs;
    private int noOfClients;
    private double averageWaitingT;
    private double averageServiceT;
    public static AtomicInteger currentSimulationT;
    private HelloController controlCenter;

    public Scheduler(int numberOFQueues, int numberOFClients, int simulationT, int minArrTime, int maxArrTime, int minSerTime, int maxSerTime) {
        try {
            this.simulationTime = simulationT;
            this.minArrivalT = minArrTime;
            this.maxArrivalT = maxArrTime;
            this.minServiceT = minSerTime;
            this.maxServiceT = maxSerTime;
            this.noOfQs = numberOFQueues;
            this.noOfClients = numberOFClients;

            clients = Client.generateClients(numberOFClients, minArrTime, maxArrTime, minSerTime, maxSerTime);
            queues = new ArrayList<Server>(0);
            currentSimulationT = new AtomicInteger(1);
            averageServiceT = getAvgSerTime();
            averageWaitingT = 0.0;

            for (int i = 0; i < numberOFQueues; i++) {
                Server newQueue = new Server(i);
                queues.add(newQueue);
                Thread thread = new Thread(newQueue);
                thread.start();
            }
        }catch(Exception e)
        {
            System.out.println("scheduler construct problem");
        }
    }
    @Override
    public void run() {
        try{
        int totalWaitT = 0;
        int numberOfClientsServiced = 0;
        int maximmClientsAtaT = 0;
        int highestCountTimeUnit = 1;
        while (currentSimulationT.get() <= simulationTime) {
            int iterator = 0;
            while (iterator < clients.size()) {
                if (clients.get(iterator).getArrivalTime() <= currentSimulationT.get()) {
                    Server bestTQueue = getMinTimeQue();
                    bestTQueue.addClient(clients.get(iterator));
                    clients.remove(clients.get(iterator));
                } else {
                    break;
                }
            }
            int totalClientsServicedLoad = 0;
            for (Server queue: queues) {
                totalClientsServicedLoad+= queue.getQueue().size();
            }
            if (totalClientsServicedLoad > maximmClientsAtaT) {
                maximmClientsAtaT = totalClientsServicedLoad;
                highestCountTimeUnit = currentSimulationT.get();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controlCenter.outputSimData(currentSimulationT, queues, clients);
            controlCenter.writeUpdateInFile(currentSimulationT, queues, clients);
            currentSimulationT.getAndIncrement();
        }
        for (Server queue: queues) {
            queue.setSimulationOn(false);
            totalWaitT +=  queue.getTotalTime().get();
            numberOfClientsServiced += queue.getNumberOfClients().get();
        }
        double averageWaitingTime = ((double)totalWaitT / (double)numberOfClientsServiced);
        controlCenter.simResultDisplay(averageWaitingTime, highestCountTimeUnit, averageServiceT, maximmClientsAtaT);
        controlCenter.writeFinalFeedBackInFile(averageWaitingTime, highestCountTimeUnit, averageServiceT, maximmClientsAtaT);
        }catch(Exception e)
        {
            System.out.println("run problem");
            System.out.println(e.getMessage());
        }
    }
    public void giveControlData(HelloController controlCenter) {
        this.controlCenter = controlCenter;
    }
    private Server getMinTimeQue() {
        Server bestTQueue = queues.get(0);
        for (Server queue: queues) {
            if (queue.getWaitingTimePeriod().get() < bestTQueue.getWaitingTimePeriod().get()) {
                bestTQueue = queue;
            }
        }
        return bestTQueue;
    }
    private double getAvgSerTime() {
        int totalServiceTime = 0;
        for (Client client: clients) {
            totalServiceTime = totalServiceTime + client.getServiceTime();
        }
        double result = (double)totalServiceTime / (double)clients.size();
        return result;
    }
}
