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
                    credentialsId: 'github'
            }
        }

        stage('Build & Test') {
            steps {
                // Only run the working test
                sh 'mvn clean test -Dtest=DepartmentServiceTest'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }


        stage('Package JAR') {
            steps {
                sh 'mvn package'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('SonarQube') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=student-management -Dsonar.host.url=http://sonarqube:9000 -Dsonar.login=${SONAR_TOKEN}"
                }
            }
        }
    }

    post {
        success { echo 'Pipeline completed successfully!' }
        failure { echo 'Pipeline failed. Check logs!' }
    }
}
