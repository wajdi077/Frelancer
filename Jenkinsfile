pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'wma2025wma/wma-app:latest'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        MYSQL_IMAGE = 'mysql:8.0'
        MYSQL_CONTAINER_NAME = 'db'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', credentialsId: 'git-hub-credentials', url: 'https://github.com/wajdi077/Frelancer.git'
            }
        }

        stage('Make Gradle Wrapper Executable') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build with Gradle') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test with Gradle') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        /*
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', DOCKER_CREDENTIALS_ID) {
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }
        */

        stage('Run MySQL Container') {
            steps {
                script {
                    // Stop and remove MySQL container if already running
                    sh "docker stop ${MYSQL_CONTAINER_NAME} || true && docker rm ${MYSQL_CONTAINER_NAME} || true"

                    // Run MySQL container
                    sh """
                        docker run -d --name ${MYSQL_CONTAINER_NAME} -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mydb -p 3306:3306 ${MYSQL_IMAGE}
                    """
                }
            }
        }

        stage('Run New Application Container') {
            steps {
                script {
                    // Stop and remove the previous application container if it's running
                    sh "docker stop my-container || true && docker rm my-container || true"

                    // Run the new application container
                    sh "docker run -d --name my-container -p 8080:8080 --link ${MYSQL_CONTAINER_NAME}:mysql ${DOCKER_IMAGE}"
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
