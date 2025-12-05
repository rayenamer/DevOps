pipeline {
    agent any

    tools {
        maven 'MAVEN'  // Nom Maven dans Jenkins
        jdk 'JDK17'    // Nom du JDK configuré
    }

    stages {
        stage('📥 1) Git Clone') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/rayenamer/DevOps'
                sh 'echo "✅ Code source récupéré"'
            }
        }

        stage('🏗️ 2) Compile') {
            steps {
                sh 'mvn clean compile'
                sh 'echo "✅ Compilation réussie"'
            }
        }

        stage('🧪 3) Test') {
            steps {
                script {
                    try {
                        echo "🧪 Running Maven tests with H2 in-memory database..."
                        sh 'mvn test'
                        echo "✅ Tests réussis"
                        currentBuild.result = 'SUCCESS'
                    } catch (Exception e) {
                        echo "❌ Tests échoués"
                        currentBuild.result = 'FAILURE'
                        error "Erreur pendant les tests"
                    } finally {
                        // Publish test results
                        junit 'target/surefire-reports/*.xml'
                    }
                }
            }
        }

        stage('📦 4) Build JAR') {
            when {
                expression { currentBuild.result == 'SUCCESS' }
            }
            steps {
                sh 'mvn package -DskipTests'
                sh '''
                    echo "=== ARTEFACTS ==="
                    ls -la target/*.jar
                    echo "=== TAILLE ==="
                    du -h target/*.jar
                '''
                archiveArtifacts 'target/*.jar'
                sh 'echo "✅ JAR archivé dans Jenkins"'
            }
        }

        stage('🔍 5) SonarQube Analysis') {
            when {
                expression { currentBuild.result == 'SUCCESS' }
            }
            steps {
                withCredentials([string(credentialsId: 'jenkins_sonar', variable: 'SONAR_TOKEN')]) {
                    sh '''
                        echo "🔍 Analyse SonarQube"
                        mvn sonar:sonar -Dsonar.projectKey=Devops \
                            -Dsonar.host.url=http://192.168.132.129:9000 \
                            -Dsonar.login=$SONAR_TOKEN
                    '''
                }
            }
        }
    }

    post {
        always {
            echo "📊 Statut: ${currentBuild.result ?: 'UNKNOWN'}"
        }
        success {
            echo '🎉 SUCCÈS! JAR disponible et analysé par SonarQube.'
        }
        failure {
            echo '❌ ÉCHEC du pipeline.'
        }
    }
}