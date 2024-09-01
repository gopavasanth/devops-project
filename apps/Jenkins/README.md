# Project Documentation

## Guide

For instructions on installing Jenkins with Helm, refer to the [Jenkins Helm Install Guide](https://octopus.com/blog/jenkins-helm-install-guide).

## Exposing Jenkins Pod

To expose the Jenkins pod using a LoadBalancer, consider the following:

- **Current Approach:** For every service, use a LoadBalancer since native Kubernetes does not support this functionality.
- **Recommended Alternative:** It is recommended to use Ingress for exposing services. Ingress provides a more flexible and reusable way to manage external access to services within a Kubernetes cluster.
