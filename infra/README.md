# Terraform Automation

The infrastructure is set up using Terraform. The Terraform code creates the following resources in AWS:

## Components

- **EKS Cluster**: Managed Kubernetes cluster.
- **S3 Bucket**: Stores Terraform state files securely.
- **DynamoDB Table**: Provides state locking to prevent concurrent modifications.
- **EC2 Instance**: Hosts the Jenkins agent.
- **Cluster Auto-Scaling**: Automatically adjusts the number of nodes based on traffic. Configuration is located in `./autoscaling/cluster-autoscaler-autodiscover.yaml`.
- **VPC**: Virtual Private Cloud for network isolation and management. Configuration is included in `./modules/eks_cluster/vpc.tf`.

## VPC Setup

The VPC setup is defined in `./modules/eks_cluster/vpc.tf` and includes the following:

- **VPC**: Defines the Virtual Private Cloud.
- **Subnets**: Public and private subnets for organizing resources.
- **Internet Gateway**: Allows resources in the public subnet to access the internet.
- **NAT Gateway**: Enables instances in private subnets to access the internet for updates and patches.

## Directory Structure

- **`./modules/`**: Contains reusable Terraform modules.
  - **`dynamodb_table/`**: Module for creating DynamoDB tables.
  - **`ec2_instance/`**: Module for creating EC2 instances.
  - **`eks_cluster/`**: Module for creating EKS clusters.
  - **`s3_bucket/`**: Module for creating S3 buckets.
- **`backend.tf`**: Configuration file for backend settings (e.g., S3 bucket for state files).
- **`main.tf`**: Main Terraform configuration file that ties together modules.
- **`terraform.tfvars`**: File for defining variable values.
- **`KUBECONFIG`**: Kubernetes configuration file for accessing the EKS cluster.

## How to Execute

1. Initialize Terraform:
   ```bash
   terraform init

2. Run the Plan Command:
   ```bash
   terraform plan

3. Apply the Terraform configuration:
   ```bash
   terraform apply

