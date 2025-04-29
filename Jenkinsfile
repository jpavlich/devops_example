pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('package') {
            steps {
                sh './mvnw package -Dmaven.test.skip'
            }
        }

        stage('deploy') {
            steps {
                sh 'cp ./target/app.war /deploy'

            }
        }
    }
}
