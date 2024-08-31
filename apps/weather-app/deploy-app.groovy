pipeline {
    agent none

    environment {
        DOCKER_IMAGE_NAME = 'gopavasanth/weather-app'
        DOCKER_IMAGE_TAG = 'latest'
        DOCKER_REPO = 'gopavasanth/weather-app'
    }

    stages {
        stage('Deploy to Kubernetes') {
            agent { label 'gopa-mac' }

            stages {
                stage('Apply Deployment') {
                    steps {
                        script {
                            sh "kubectl get ns"

                            // Apply the deployment YAML
                            writeFile file: 'weather-app-deployment.yaml', text: """
                            apiVersion: apps/v1
                            kind: Deployment
                            metadata:
                              name: weather-app
                              namespace: apps
                              labels:
                                app: weather-app
                            spec:
                              replicas: 2
                              selector:
                                matchLabels:
                                  app: weather-app
                              template:
                                metadata:
                                  labels:
                                    app: weather-app
                                spec:
                                  containers:
                                  - name: weather-app
                                    image: ${DOCKER_REPO}:${DOCKER_IMAGE_TAG}
                                    ports:
                                    - containerPort: 3000
                                    env:
                                    - name: ENVIRONMENT
                                      value: "production"
                                    resources:
                                      limits:
                                        memory: "1024Mi"
                                        cpu: "1000m"
                                      requests:
                                        memory: "512Mi"
                                        cpu: "500m"
                            """
                            sh "kubectl apply -f weather-app-deployment.yaml"
                        }
                    }
                }

                stage('Expose Service') {
                    steps {
                        script {
                            // Apply the service YAML
                            writeFile file: 'weather-app-service.yaml', text: """
                            apiVersion: v1
                            kind: Service
                            metadata:
                              annotations: 
                                service.beta.kubernetes.io/aws-load-balancer-scheme: 'internet-facing'
                              name: weather-app-service
                              namespace: apps
                            spec:
                              selector:
                                app: weather-app
                              ports:
                                - protocol: TCP
                                  port: 80
                                  targetPort: 3000
                              type: LoadBalancer
                            """
                            sh "kubectl apply -f weather-app-service.yaml"
                        }
                    }
                }
            }
        }
    }
}
