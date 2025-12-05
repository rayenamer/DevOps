pipeline {
    agent any

    tools {
        maven 'MAVEN'
        jdk 'JDK17'
    }

    environment {
    SONAR_TOKEN = credentials('sonar-token')
}


    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rayenamer/DevOps',
                    credentialsId: 'github'  // ID of the credential you added in Jenkins
            }
        }


        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=student-management -Dsonar.host.url=http://sonarqube:9000 -Dsonar.login=${SONAR_TOKEN}"
                }
            }
        }
    }
}

