## Local Database Setup

### 1. Install pgAdmin
Download and install pgAdmin from the official PostgreSQL website.

### 2. Create a New Server
Open pgAdmin and create a new server with the following connection settings:

- Host: localhost
- Port: 5433
- Username: postgres
- Password: 12345678

### 3. Create the Database
Create a new database named: WaitForDinner

### 4. Build the Backend
Open the `backend` folder in your IDE or terminal and run:

./gradlew clean build

### 5. Verify the Tables
Return to pgAdmin, refresh the database, and confirm that 18 tables have been created in the `public` schema.
