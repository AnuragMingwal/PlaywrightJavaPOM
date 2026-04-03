pipeline {
    agent any

    tools {
        maven 'Maven_3'
        jdk 'JDK_17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/AnuragMingwal/PlaywrightJavaPOM.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Install Playwright Browsers') {
            steps {
                sh 'mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
            }
        }

        stage('Run Smoke Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng_smoke.xml'
            }
        }

        stage('Run Regression Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml'
            }
        }

        stage('Publish Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {

        always {
            archiveArtifacts artifacts: 'target/surefire-reports/*', fingerprint: true
        }

        success {
            echo 'All tests passed!'
        }

        failure {
            echo 'Some tests failed. Check reports.'
        }
    }
}