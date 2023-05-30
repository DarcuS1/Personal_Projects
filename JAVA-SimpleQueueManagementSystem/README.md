Queue Simulation Documentation:
This documentation provides an overview of a queue simulation program that models a queuing system with multiple servers and clients. The program aims to evaluate the performance of the queuing system by analyzing key metrics such as average waiting time and the number of clients served by each server.

Project Objective: <a name="project-objective"></a>
The objective of this assignment is to create a simulation program that models a queuing system with multiple servers and clients. The program will use Java programming language and implement multi-threading to simulate the concurrent behavior of the system. The simulation program will be used to evaluate the performance of the queuing system by analyzing key metrics such as the average waiting time and the number of clients served by each server.

Problem Analysis: <a name="problem-analysis"></a>
The program will model a queuing system with multiple servers and clients. Clients will arrive at the system randomly and have a service time that is also randomly generated. The program will use multi-threading to simulate the concurrent behavior of the system.

Design: <a name="design"></a>
The program will be designed using Java programming language and multi-threading techniques to simulate the concurrent behavior of the system. The system will consist of multiple Server objects, each with a queue of Client objects. The Server class will have attributes such as ID, queue, waiting time period, total time, number of clients served, and simulation status.

The Client class represents a client in the queuing system and has attributes such as ID, arrival time, and service time. It also provides methods to access and manipulate these attributes.

The Controller class handles the user interface and communication between the user interface and the simulation logic. It allows the user to input simulation parameters and displays the simulation results.

The Scheduler class implements the Runnable interface and is responsible for scheduling client requests to servers, keeping track of statistics, and updating the simulation data.

Implementation: <a name="implementation"></a>
The implementation involves creating instances of the Server and Client classes, running the simulation in a separate thread, and analyzing the simulation results. The Server class manages the queue of clients and serves them based on their arrival and service times.
