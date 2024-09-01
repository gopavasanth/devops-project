# Deploying Cluster Autoscaler on EKS

## Steps

1. **Create IAM Policy and Role**:
   - Created an IAM policy and role.
   - Configured trust relationships for the IAM role to allow permissions for the service account to assume this role.
   - Annotated the role to the service account created in the Kubernetes cluster.

2. **Deploy Cluster Autoscaler**:
   - Deployed the Cluster Autoscaler following the instructions from the [Cluster Autoscaler AWS Guide](https://github.com/kubernetes/autoscaler/blob/master/cluster-autoscaler/cloudprovider/aws/README.md).

3. **Alternative Deployment Using `eksctl`**:
   - The deployment can also be performed using the following `eksctl` command:
     ```bash
     eksctl create iamserviceaccount \
         --name cluster-autoscaler \
         --namespace kube-system \
         --cluster eksworkshop-eksctl \
         --attach-policy-arn "arn:aws:iam::${ACCOUNT_ID}:policy/k8s-asg-policy" \
         --approve \
         --override-existing-serviceaccounts
     ```

4. **Testing Autoscaling**:
   - Tested autoscaling by deploying heavy pods and observed that the cluster scaled up when heavy pods were deployed and scaled down when they were removed.
