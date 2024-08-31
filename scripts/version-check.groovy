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
      image: bitnami/kubectl:latest
      command:
        - /bin/sh
        - -c
        - cat
      tty: true
    - name: docker
      image: docker:latest
      command:
        - /bin/sh
        - -c
        - cat
      tty: true
      env:
        - name: DOCKER_HOST
          value: https://localhost:2376
        - name: DOCKER_TLS_VERIFY
          value: "1"
        - name: DOCKER_CERT_PATH
          value: /etc/docker/ssl
    - name: dind
      image: docker:latest
      securityContext:
        privileged: true
      command:
        - dockerd-entrypoint.sh
      args:
        - --host=tcp://0.0.0.0:2376
        - --tlsverify
        - --tlscacert=/etc/docker/ssl/ca.pem
        - --tlscert=/etc/docker/ssl/server-cert.pem
        - --tlskey=/etc/docker/ssl/server-key.pem
      volumeMounts:
        - name: docker-graph-storage
          mountPath: /var/lib/docker
        - name: docker-certs
          mountPath: /etc/docker/ssl
  volumes:
    - name: docker-graph-storage
      emptyDir: {}
    - name: docker-certs
      secret:
        secretName: docker-certs
"""
        }
    }
    stages {
        stage('Check Docker version') {
            steps {
                container('docker') {
                    sh 'docker version'
                    sh 'docker ps'
                }
            }
        }

        stage('Check K8s version') {
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
