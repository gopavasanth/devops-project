## Guide
https://octopus.com/blog/jenkins-helm-install-guide

## Issues
Jenkins user doens't have permissions to list resources
Fixed: http://adceb6d7ded6941098f35b584329631d-1261963004.us-west-2.elb.amazonaws.com:8080/job/kubectl-version-pipeline/5/console

# TO exposer jenkins pod using LoadBalancer
For everysservice we need to use Loadbalancer since native k8s don't support this re-use funcaiontlity so its recommended to use Ingress 
