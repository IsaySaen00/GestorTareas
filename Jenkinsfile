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

        stage('SonarQube Analysis') {
            steps {
                sh """
                mvn sonar:sonar \
                -Dsonar.host.url=http://host.docker.internal:9000 \
                -Dsonar.login=$SONAR_TOKEN
                """
            }
        }
        stage('Quality Gate'){
            steps{
                script{
                    timeout(time: 2, unit: 'MINUTES'){
                        def qg = waitForQualityGate()
                        if(qg.status != 'OK'){
                            error "Pipeline aborted due to Quality Gate failure: ${qg.status}"
                        }
                    }
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
