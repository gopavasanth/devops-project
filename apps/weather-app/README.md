# Weather App Deployment

This document describes the deployment of the Weather App on Kubernetes.

## Overview

The Weather App is a web application deployed on Kubernetes with the following configurations:

### Deployment

- **Replicas:** The deployment is configured with 2 replicas to ensure high availability and load balancing.
- **Container Image:** The app uses the Docker image `gopavasanth/weather-app:latest`.
- **Port Exposure:** The application container exposes port 3000.
- **Environment Variables:** Set `ENVIRONMENT` to "production" to configure the app for production use.
- **Resource Management:** Resource requests and limits are defined to allocate CPU and memory resources efficiently.

### Service

- **Type:** The service is of type `LoadBalancer`, which provisions an external load balancer to expose the app to the internet.
- **Port Configuration:** The service listens on port 80 and forwards traffic to the application’s container port 3000.
- **Annotations:** Configured to be internet-facing using the appropriate annotations for AWS load balancer.

## How It Works

1. **Deployment Configuration:** The `Deployment` resource ensures that two instances of the Weather App are running. It manages the application’s lifecycle, including scaling and updates.
2. **Service Exposure:** The `Service` resource of type `LoadBalancer` creates an external load balancer, allowing access to the app from outside the Kubernetes cluster. It handles routing traffic to the appropriate pods running the app.

## Deployment Instructions

1. **Apply the Deployment:**
   ```bash
   kubectl apply -f weather-app/deployment.yaml

2. **Apply the Service:** 
    ```bash
    kubectl apply -f weather-app/service.yaml

3. **Verify Deployment and Service: Check the status of the deployed pods and the LoadBalancer service:**
    ```bash
    kubectl get pods -n apps
    kubectl get services -n apps

