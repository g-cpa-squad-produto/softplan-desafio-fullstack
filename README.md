# Movies Coming Soon
API to manage users and process by role.


## How this was structured

### Setting up your environment

```bash
# clone to your local machine
git clone https://github.com/bahrbara/movies-coming-soon.git

# step into local repo
cd ungp
```

#### Prerequisites
* NPM
* Docker + Docker Compose

#### Configuration

Next we need to create `.env` file, which contains all the necessary
environment variables that application needs. You can create it by following
command (in folder where you cloned this project):

```bash
cp env.dist .env
```

Then open that file and make necessary changes to it. Note that this `.env` file is ignored on VCS.

#### Folder Structure

After cloning, your project should look like this:

```
movies-coming-soon/
  backend/
  frontend/
  database/
  docker-compose.yml
  README.md
```

At **backend** folder we have a Java EE + SprigBoot application.
At **frontend** folder we have the frontend in Angular 9.

#### Commands

### Build and Run

## Backend 
  backend/complete/ build

## Frontend and Database
docker-compose up --build

## Dump Database
  database/dump.sql
  

##### Built With
* Java - Backend
* Angular 7 - Frontend framework
* Angular Material - Material Design components for Angular