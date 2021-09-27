pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build Docker image') {
            steps {
                sh 'echo "build docker"'
            }
        }
        stage('Push Docker image') {
//             environment {
//                 DOCKER_HUB_LOGIN = credentials('docker-hub')
//             }
            steps {
//                 sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
                sh 'echo "push docker"'
            }
        }
    }
}