pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
        }
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv('SonarLocal') {
                    sh """
                    mvn sonar:sonar \
                    -Dsonar.login=$SONAR_TOKEN
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build'){
            steps{
                sh 'docker build -t tareas-app:latest'
            }
        }
    }
}
