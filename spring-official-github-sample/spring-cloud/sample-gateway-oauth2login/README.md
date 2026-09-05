= OpenID Connect via Spring Cloud Gateway

== Run the Sample

## Running the Application

### 1. Build the Sample

```bash
./gradlew clean build
```

### 2. Run the Gateway

Open a new terminal and run:

```bash
./gradlew -b gateway/build.gradle bootRun
```

### 3. Run the Resource Server

Open another terminal and run:

```bash
./gradlew -b resource-server/build.gradle bootRun
```

### 5. Access the Application

Open the application in your browser:

`http://localhost:8080`

Log in to UAA using the following credentials:

```text
Username: user1
Password: password
```