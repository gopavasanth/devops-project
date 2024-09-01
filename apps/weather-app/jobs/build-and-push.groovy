pipeline {
    agent none

    environment {
        DOCKER_CREDENTIALS_ID = 'gopa-dockerhub'
        DOCKER_IMAGE_NAME = 'weather-app'
        DOCKER_IMAGE_TAG = 'latest'
        DOCKER_REPO = 'gopavasanth/weather-app'
    }

    stages {
        stage('Running on gopa-machine') {
            agent { label 'gopa-mac' }
            stages {
                stage('Clone Repository') {
                    steps {
                        script {
                            // Delete the existing directory if it exists
                            sh 'rm -rf weather-app'
                            // Clone the repository
                            sh 'git clone https://github.com/Abhishek-kumar-202063/weather-app'
                        }
                    }
                }

                stage('Build Docker Image') {
                    steps {
                        script {
                            sh "ls -l"
                            sh "pwd"
                            sh "cd weather-app && docker build -t ${DOCKER_REPO}:${DOCKER_IMAGE_TAG} ."
                        }
                    }
                }

                stage('Push Docker Image') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                                sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
                                sh "docker push ${DOCKER_REPO}:${DOCKER_IMAGE_TAG}"
                            }
                        }
                    }
                }
            }
        }
    }
}
