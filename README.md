# Monitoring-System
## 1. Demo
Demo: [Java Networking - Monitoring System](https://youtu.be/_I9pZBO4aQM)

## 2. Basic Information
The program monitors the change of a directory on remote computers. The program fully implements (100%) the functions of the required topic as follows:
* **Client (100%):**
  * Allow connection, and disconnection from Server. Listen when the Server disconnects unexpectedly.
  * Allow input of IP Server information if localhost is not used.
  * Exchange information, send directory tree information and listen for folder changes to the Server.
  * Graphical interface
* **Server (100%):**
  * Turn on Server at Port 8080 and listen for many connections, create Thread to manage clients in parallel. Listen for connection and disconnection events from the Client and for notifications.
  * Monitor multiple clients at the same time.
  * Select Client, select the folder on the Client's machine by Server to monitor (the directory list is sent from the Client to the Server continuously).
  * Can change directory to monitor.
  * All information changes (add, edit, delete) are notified to the interface.
  * Graphical interface.

## 3. Program layout and implementation
The program is written on IDE: **NetBeans 16**, **JDK 15** and does not install any additional libraries. Includes 2 Source code (**Client** and **Server**).
Some classes in the program:
* **DataSend:** Shared class for Client and Server. Standardize the information to be sent with an object of properties to exchange information.
* **FolderInfo:** Normalizes the change of Folder into an object consisting of properties (time, action, ...) to send. It is a component in DataSend.
* **ServerThread and ClientHandler:** ServerThread is the place to receive Client connections and create a separate **ClientHandler** thread for each client.

## 4. References
* [1] 	V. SRJ, "How to watch a folder/directory for changes using Java?," 23 12 2020. [Online]. Available: https://fullstackdeveloper.guru/2020/12/23/how-to-watch-a-folder-directory-or-changes-using-java/. [Accessed 10 5 2023].
* [2] 	N. V. Khiết, "Multithread," Đại Học Khoa Học Tự Nhiên.
* [3] 	N. V. Khiết, “Networking,” Đại Học Khoa Học Tự Nhiên.


