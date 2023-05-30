package main.model;

import main.controller.Scheduler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final int id;
    private ArrayBlockingQueue<Client> queue;
    private AtomicInteger waitingTimePeriod;
    private AtomicInteger totalTime;
    private AtomicInteger numberOfClients;
    private boolean simulationOn;

    public Server(int id) {
        this.id = id;
        queue= new ArrayBlockingQueue<>(100);
        waitingTimePeriod = new AtomicInteger(0);
        totalTime = new AtomicInteger(0);
        numberOfClients = new AtomicInteger(0);
        simulationOn = true;
    }
    public ArrayBlockingQueue<Client> getQueue() {
        return queue;
    }
    public AtomicInteger getWaitingTimePeriod() {
        return waitingTimePeriod;
    }
    public AtomicInteger getTotalTime() {
        return totalTime;
    }
    public AtomicInteger getNumberOfClients() {
        return numberOfClients;
    }
    public void setSimulationOn(boolean simulationOn) {
        this.simulationOn = simulationOn;
    }
    public void addClient(Client client) {
        try {
            queue.add(client);
            waitingTimePeriod.addAndGet(client.getServiceTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        while (simulationOn) {
            if (queue.isEmpty()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            Client currentClient = queue.peek();
            if (currentClient == null) {
                continue;
            }
            totalTime.getAndAdd(Scheduler.currentSimulationT.get() - currentClient.getArrivalTime());
            numberOfClients.getAndIncrement();
            int currentServiceTime = currentClient.getServiceTime();
            while (currentServiceTime > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentServiceTime--;
                currentClient.setServiceTime(currentServiceTime);
                waitingTimePeriod.getAndAdd(-1);
            }
            queue.remove(currentClient);
        }
    }
    public  String outputQueueData(){
        String output="Queue "+ id + ": ";
        for (Client currenyClients: queue) {
            output = output +"(" + currenyClients.getId() + " " + currenyClients.getArrivalTime() + " " + currenyClients.getServiceTime() + ") ";
        }
        output=output+"\n";
        return output;
    }
}
