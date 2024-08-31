## Apps

Apps Installed on Kubernetes Cluster:
- **Jenkins**: Installed using Helm.
- **Weather App**: Deployed using Kubernetes deployments and services. Fetches and displays real-time weather information based on cities.
- **Static Website**: Deployed using Kubernetes deployments and services. Provides a simple static website.

Kubernetes Configuration:
- **Namespaces**: Applications are deployed in the `apps` namespace. Jenkins is deployed in the `jenkins` namespace.
- **Deployments and Services**: Kubernetes deployments and services are created for each application to manage and expose them.
- **Load Balancers**: Configured to make services accessible from the internet.
- **Autoscaling**: The cluster is configured for auto-scaling to adjust the number of nodes based on traffic. The configuration can be found in `./autoscaling/cluster-autoscaler-autodiscover.yaml`.
- **Heavy Pods Deployment**: Deployed and tested heavy pods using the configuration located in `./autoscaling/deployment-heavy-pods.yaml`.
