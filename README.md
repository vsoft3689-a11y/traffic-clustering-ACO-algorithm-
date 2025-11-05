# Ant Colony Streaming Cluster(Traffic Data Clustering System)

## 📌 Overview

 The purpose of the Traffic Data Clustering System is to analyze and visualize vehicle
 traffic density using Ant Colony Streaming Clustering Algorithm. The system enables city
 planners and traffic analysts to identify congestion patterns in real time by clustering
 vehicle data points based on proximity, speed, and timestamp. The project aims to help
 improve traffic management and reduce congestion.

## 🚀 Features

The system allows users to upload CSV datasets containing latitude, longitude, speed,
 timestamp, and city data. Using an ant colony–based clustering algorithm, traffic points are
 categorized as High (Red), Medium (Yellow), or Low (Green) density zones. A web-based
 dashboard visualizes these clusters dynamically, providing actionable insights into current
 traffic conditions.

## 🛠️ Technologies Used

- **Frontend:** HTML, CSS, JavaScript,bootstrap,chart.js
- **Backend:** Java, Spring Boot
- **Database:** MySQL


## 🔧 Installation & Setup

### Prerequisites

- Java 8 or later
- MySQL8.0 Database
- Maven
- Any IDE (IntelliJ, Eclipse, VS Code)

### Steps to Set Up Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/vsoft3689-a11y/traffic-clustering-ACO-algorithm-.git
   ```

2. Navigate to the project folder:

   ```bash
   cd traffic-clustering
   ```

3. Configure the database:

   - Open MySQL and create a database named `trafficdb`
   - Update `application.properties` with your database credentials

4. Build and run the project:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. Access the application in the browser:

   ```
   http://localhost:8080
   ```

## 📸 Screenshots

(Add relevant screenshots of your application here)


## 🤝 Contribution Guidelines

1. Fork the repository.
2. Create a new branch (`feature-branch`)
3. Make changes and commit.
4. Push to your fork and create a pull request.


## 📩 Contact

For queries or support, reach out via vsoft3689@gmail.com

---

✨ **Star** the repo if you found it useful!

