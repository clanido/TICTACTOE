# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
- Name: Rayyan Akhtar Setiawan
- Student ID: 5026251005
- Class: Q Programming Fundamental

## Project Description
This project is a simple Tic-Tac-Toe game built using Java, Java Swing GUI, and
simple Object-Oriented Programming (no inheritance, interface, or abstract class).
The player (X) takes turns against the computer (O) on a 3x3 board. The application
requires the user to log in, lets the user play the game, records game statistics in a
single-table database, and displays the Top 5 scorers.

## Features
- Login using a MySQL database (username + password check, error dialog on failure).
- Play Tic-Tac-Toe using a Swing GUI (9 JButtons as the board).
- Win / lose / draw detection with invalid-move prevention.
- Record wins, losses, draws, and score after every completed game.
- Display personal statistics (re-fetched live from the database).
- Display Top 5 scorers using a `JTable`, ordered by score then wins.
- Full navigation between Login, Main Menu, Game, Statistics, and Top 5 windows.

## Scoring Rule
| Result | Score Change |
|--------|--------------|
| Win    | +10 points   |
| Draw   | +3 points    |
| Lose   | +0 points    |

## Database
**Database used:** MySQL (default). PostgreSQL / SQL Server variants are included
as comments in `database/schema.sql` — only one table (`players`) is used, as required.

Table: `players`
| Column   | Type          | Notes              |
|----------|---------------|--------------------|
| id       | INT           | Primary key        |
| username | VARCHAR(50)   | Unique             |
| password | VARCHAR(100)  |                    |
| wins     | INT           | Default 0          |
| losses   | INT           | Default 0          |
| draws    | INT           | Default 0          |
| score    | INT           | Default 0          |

## How to Create the Database
1. Install MySQL (or PostgreSQL / SQL Server if you prefer).
2. Run the script in `database/schema.sql` (uncomment the relevant block if not using MySQL).
3. This creates the `game_project` database, the `players` table, and 5 sample users
   (`student1` ... `student5`, password `12345`).

## How to Run the Program
1. Make sure JDK 17+ is installed.
2. Download the JDBC driver matching your database and add it to the classpath:
   - MySQL: `mysql-connector-j-x.x.x.jar`
   - PostgreSQL: `postgresql-x.x.x.jar`
   - SQL Server: `mssql-jdbc-x.x.x.jar`
3. Open `src/DatabaseManager.java` and set `URL`, `USER`, and `PASSWORD` to match
   your local database configuration.
4. Compile:
   ```
   javac -d out src/*.java
   ```
5. Run (replace the driver jar path with your own):
   ```
   java -cp "out;lib/mysql-connector-j-9.7.0.jar" Main
   ```
   (On Windows, use `;` instead of `:` to separate classpath entries.)
6. The Login Window will appear. Log in with one of the sample users
   (e.g. `student1` / `12345`).

## Class Explanation
- **Main** – Starts the program and opens the Login Window.
- **DatabaseManager** – Holds the JDBC connection settings and opens connections.
- **Player** – Model class storing id, username, wins, losses, draws, and score.
- **PlayerService** – Handles login, fetching a player by id, updating statistics
  after each game, and retrieving the Top 5 scorers — all SQL lives here.
- **GameLogic** – Pure game logic: move validation, win/draw checking, and the
  computer's (random) move. Independent of the GUI.
- **LoginFrame** – Swing window for username/password input and the login button.
- **MainMenuFrame** – Swing window with navigation to Game, Statistics, Top 5, and Exit.
- **GameFrame** – Swing window with the 3x3 button board; wires button clicks to
  `GameLogic` and updates the database through `PlayerService` when the game ends.
- **StatisticsFrame** – Swing window showing the logged-in player's current stats,
  re-fetched live from the database.
- **TopScorersFrame** – Swing window showing the Top 5 scorers in a `JTable`.

## Parts Completed by the Student
- Database connection configuration (`DatabaseManager`).
- `PlayerService.login()`, `updateStatistics()`, and `getTopFiveScorers()` queries.
- `LoginFrame` button event handling and error dialog.
- `MainMenuFrame` navigation between windows.
- `GameLogic` valid-move checking, winner checking, draw checking, and computer move.
- `GameFrame` button wiring to `GameLogic` and the statistics update call.
- `StatisticsFrame` live data display.
- `TopScorersFrame` JTable population from the database.

## Screenshots
<img width="1920" height="1080" alt="Screenshot (61)" src="https://github.com/user-attachments/assets/8c64d0cb-61a8-4ca0-83dc-98949322db9c" />

## GitHub Repository
*(Add your repository link here.)*

## Video Link
*(Add your YouTube demonstration video link here.)*
