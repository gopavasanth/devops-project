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

## Disaster recovery plan in case of infrastructure failures

Designing an Amazon Elastic Kubernetes Service (EKS) cluster for disaster recovery involves implementing strategies and configurations that ensure the availability and resilience of the cluster in case of a disaster or failure. Here are some steps to consider when designing an EKS cluster for disaster recovery:

1. **Use multiple availability zones:** When creating an EKS cluster, We should launch worker nodes across multiple availability zones. This provides redundancy and helps ensure that the failure of a single availability zone does not result in a complete cluster outage.

2. **Implement automatic scaling:** Configure EKS cluster to automatically scale in response to changes in workload demand. This ensures that your cluster can handle fluctuations in traffic and can automatically recover from failures without manual intervention.

3. **Use multiple clusters:** Consider using multiple EKS clusters to ensure redundancy and minimize the impact of failures. We can create a primary cluster and a secondary cluster in a different region, which can take over in the event of a disaster.

4. **Implement data replication:** Implement data replication to ensure that critical data is available in multiple locations. Use data replication solutions such as Amazon S3, Amazon RDS, or Amazon DynamoDB to replicate data across multiple availability zones.

5. **Back up your EKS cluster:** Back up EKS cluster data regularly using tools like velero and store backups in a different region than the primary cluster. This ensures that we have access to critical data and can restore your cluster in the event of a disaster.

6. **Implement monitoring and alerting:** Implement monitoring and alerting tools to monitor EKS cluster and alert you to any issues. Use tools such as Amazon CloudWatch and AWS CloudTrail to monitor and analyze cluster logs, metrics, and events.

7. **Test your disaster recovery plan:** Test disaster recovery plan regularly to ensure that it works and to identify any gaps or weaknesses. Conduct simulated disaster recovery scenarios to test plan and ensure that your team is prepared to handle a disaster.
