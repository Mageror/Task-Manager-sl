Task Management System
Setup Instructions
Follow the steps below to set up and run the project on your local machine.

1. Create Project Folder
Create a folder named:

Task_Management
2. Clone the Repository
Clone this GitHub repository inside the Task_Management folder:
git clone <repository_url>

3. Prerequisites
Ensure the following are installed on your system:
Java Development Kit (JDK)
You can check with:
java -version
Java IDE (e.g., IntelliJ IDEA, Eclipse) – optional but recommended

MySQL Server
Check if MySQL is installed and running:
mysql --version

4. Check SQL Port Configuration
Open the file:
DatabaseUtil.java
Ensure the correct MySQL port number is used (usually 3306 for local machines). Modify the line if needed:
String url = "jdbc:mysql://localhost:3306/your_database_name";

6. Compile and Run
Place the mysql-connector-j-9.3.0.jar in the root directory of your project.

On Windows:
javac -cp ".;mysql-connector-j-9.3.0.jar" db/*.java models/*.java services/*.java Main.java
java -cp ".;mysql-connector-j-9.3.0.jar" Main

On macOS/Linux:
javac -cp ".:mysql-connector-j-9.3.0.jar" db/*.java models/*.java services/*.java Main.java
java -cp ".:mysql-connector-j-9.3.0.jar" Main
