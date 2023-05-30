package main.model;


import java.util.ArrayList;

public class Client implements Comparable<Client>{
    private final int id;
    private final int arrivalTime;
    private int serviceTime;
    public Client(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    public int getId() {
        return id;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
    @Override
    public int compareTo(Client myClient) {
        return this.getArrivalTime() - myClient.getArrivalTime();
    }
    public static int generateRandomNumbers(int minNumber, int maxNumber){
        return (int) (Math.random()*(maxNumber-minNumber)+minNumber);
    }
    public static ArrayList<Client> generateClients(int clientNumber, int arriivalTimeMin, int arrivalTimeMax, int serviceTimeMin, int serviceTimeMax){
        ArrayList<Client> newClients= new ArrayList<Client>();
        for(int i=0;i<clientNumber;i++){
            int tempArrivalTime=generateRandomNumbers(arriivalTimeMin,arrivalTimeMax);
            int tempServiceTime=generateRandomNumbers(serviceTimeMin,serviceTimeMax);
            Client newClient= new Client(i,tempArrivalTime,tempServiceTime);
            newClients.add(newClient);
        }
        newClients.sort(Client::compareTo);
        return newClients;
    }
    public static String printAllClientsData(ArrayList<Client> clients){
        String output = "";
        for(Client currentClient: clients){
            output= output+ "("+ currentClient.getId()+ " "+ currentClient.getArrivalTime() +" " + currentClient.getServiceTime()+ ")"+ "\n";
        }
        output=output+"\n";
        return output;
    }
}
