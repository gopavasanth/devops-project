pipeline {
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: k8s
spec:
  containers:
    - name: k8s
      image: alpine/k8s:1.28.13
      command:
        - /bin/sh
        - -c
        - cat
      tty: true
"""
        }
    }
    stages {
        stage('Check Cluster Access') {
            steps {
                container('k8s') {
                    sh 'kubectl version'
                    sh 'kubectl get pods -n kube-system'
                    sh 'kubectl get namespaces'
                }
            }
        }
    }
}
