node {
    docker.image('maven:3-alpine').inside('-v $HOME/.m2:/root/.m2') {
        stage('Pull repository') {
            checkout scm
        }
        stage('Build') {
            sh 'mvn -B -DskipTests clean package'
        }
        stage('Test') {
            sh 'mvn test'
        }
    }
}