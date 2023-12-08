pipeline {
    agent any

    environment {
            MAVEN_HOME = tool 'Maven'
            PATH = "$MAVEN_HOME/bin:$PATH"
        }

    stages {
        stage('Continuous-Build') {
            steps {
                script {
                    // Clean and install using Maven
                    sh 'mvn clean install'
                }
            }
        }
    }

    post {
        success {
            echo 'Build successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}