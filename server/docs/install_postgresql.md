# Install PostgreSQL and Script

## Download the PostgreSQL:
1. Access o link: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
	Version: PostgreSQL 9.4.18
	Operation System: (your system)
	
2. Install the downloaded file.


## Database configuration:
1. Open the pgAdmin III program.

2. Open the PostgreSQL server 9.4 (Localhost:5432)

3. Create a role: process
	* Right button on Logins Roles 
	* Click New Login Role...
	* Properties Tab - Role name: process
	* Definition Tab - Password: process 
	* Role privileges Tab - Mark Superuser
	* Click on Ok button
	
4. Create database: processManagement
	* Right button on Databases
	* Click on New Database...
	* Properties Tab - Name: processManagement
	* Properties Tab - Owner: process
	* Click on Ok button

5. Execute insert.sql file
