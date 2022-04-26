Project name: CloudCanvas
Team members: Logan Mann, Naif Alassaf

Overview:

CloudCanvas is a shared canvas application that allows the user to draw artwork on one of a number of shared canvases hosted by the service. As the user draws on the canvas the application updates the state of the canvas on the server. Multiple users can draw on a canvas at the same time and create artwork together.

Instructions to Build and Run:

We currently have a database and backend service deployed on AWS.
To run the client against the live backend service in the cloud, simply clone the project, navigate to the finalprojectclient directory, and run ```./run_frontent_awsbackend.sh```. This shell script will set the appropriate environment variables to connect to the backend on AWS, build and run the client.

Alternatively if you'd like to run a full-stack local deployment, we have provided a docker-compose file to create a local postgres instance, a run_backend.sh script to deploy a local backend instance against the local postgres database, and a run_frontend_localbackend.sh script to run a client against a local backend.

To run a full-stack local deployment, you must have docker/docker-compose installed. To do so follow these steps:

1. Execute ```docker-compose up``` from the root project directory to spin up a local postgres instance.
2. In a separate terminal, navigate to the 'finalprojectbackend' directory from the root project directory, and run ```./run_backend.sh``` to build and run a local backend against the local database you just created.

3. Finally, in a separate terminal, navigate to the 'finalprojectclient' directory from the root project directory, and run ```./run_frontend_localbackend.sh```. This will launch a client application against the local backend service you just started.

