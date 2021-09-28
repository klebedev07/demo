def newVersion
def dockerImage

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
               script {
                def latestVersion = sh (returnStdout: true, script: 'git describe --tags --abbrev=0 --match "*.*.*" 2> /dev/null || echo 1.0.0').trim()
                def (major, minor, patch) = latestVersion.tokenize('.').collect { it.toInteger() }
                newVersion = "${major}.${minor + 1}.0"
                sh (script: "git tag -a $newVersion -m 'Tag release newVersion'")
                sshagent(['github-klebedev']) {
                   sh (script: "git push origin $newVersion")
                }
              }
            }
        }
        stage('Build Docker image') {
            steps {
                sh 'echo "build docker"'
                script {
                   dockerImage = docker.build "klebede/test-repository" + ":demo-$newVersion"
                }
            }
        }
        stage('Push Docker image') {
           environment {
              registryCredential = 'docker-hub'
          }
            steps {
                sh 'echo "push docker"'
                script {
                    docker.withRegistry( '', registryCredential ) {
                         dockerImage.push()
                    }
                }
            }
        }
    }
}

