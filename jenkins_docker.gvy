pipeline {
    agent any
    environment {
        DOCKERHUB_REPO = 'michaelbullock2/michael-apache-app'
        DOCKERHUB_CREDS = credentials('DockerHubCreds-ID')
    }

    stages {
        stage('Source') {
            steps {
                echo 'Login into GitHub'
                git branch: 'main', credentialsId: 'GitHubCred-ID', url: 'https://github.com/michaelbullock-cloudspace/jenkins-docker-auto.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building Docker Image'
                sh 'docker build -t ${DOCKERHUB_REPO}:v${BUILD_NUMBER} .' 
                sh 'docker images'
                
            }
        }
        stage('Docker Login') {
            steps {
                sh 'docker login -u ${DOCKERHUB_CREDS_USR} -p ${DOCKERHUB_CREDS_PSW}'
                
            }
        }
        stage('Deploy') {
            steps {
                echo 'Pushing the image to DockerHub'
                sh 'docker push ${DOCKERHUB_REPO}:v${BUILD_NUMBER}'
                
                
            }
        }
    }
}
