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

## Issues Encountered and Resolutions

1. **Issue 1: Region Mismatch**:
   - Resolved region mismatch errors during the setup process.

2. **Issue 2: Pod Crash Due to Insufficient Permissions**:
   - Pods crashed due to insufficient permissions to the EKS cluster. Resolved by adjusting permissions.

3. **Issue 3: Deployment Restart**:
   - Restarted the deployment, causing the pod to be recreated.

4. **Issue 4: Misconfiguration of Auto-Discovery Flag**:
   - Autoscaling did not work due to a misconfiguration of the auto-discovery flag. This was fixed by updating the deployment, and nodes successfully scaled up.
