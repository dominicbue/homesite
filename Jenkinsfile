pipeline {
    agent any

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