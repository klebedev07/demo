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
        stage("Tag git version") {
            //when { expression {env.BRANCH_NAME == "master"} }
            steps {
            script{
                def latestVersion = sh (returnStdout: true, script: 'git describe --tags --abbrev=0 --match *.*.* 2> /dev/null || echo 1.0.0').trim()
                def (major, minor, patch) = latestVersion.tokenize('.').collect { it.toInteger() }
                def newVersion = "${major}.${minor + 1}.0"
                sh 'echo "$newVersion"'
                sh (script: "git tag -a $newVersion -m 'Tag release newVersion'")

                sshagent(['github-klebedev']) {
                  sh (script: "git push origin $newVersion")
                }

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

