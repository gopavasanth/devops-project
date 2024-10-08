# DevOps Project

Welcome to the DevOps project repository. This document provides an overview of the project setup and deployment processes.

## Repository

Clone the Git repository using the following command:

```bash
git clone git@github.com:gopavasanth/devops-project.git
```

## Architecture
[Here](architecture/) is about the AWS EKS and EC2 architecture.

## Infrastucture
[Here](infra/) is the terraform code to provision Kubernetes cluster infrastructure.

## Cluster autoscaller
[Here](autoscalling/) is the configuration details of cluster auto scaler.

## Kubernetes deployment
[Here](k8s-deployment) are the steps to implement a canary deployment strategy in k8s, to implement istio service mesh and implement logging and monitoring solution

## Ansible Automation
[Here](ansible/) is about ansible playbooks to setup and manage configurations through code.

## Set up a CICD server in k8s
[Here](apps/Jenkins/) is about CI/CD server setup using Helm and configuring slave nodes.

## Apps delpoyed on K8s cluster
[Here](apps/) is about the apps deployed in AWS EKS cluster.
