pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarLocal') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}
