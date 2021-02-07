#!/usr/bin/env groovy
pipeline {
    agent any
    tools {
        jdk 'OpenJDK-15.0.2'
    }
    environment {
        SERVER = 'http://10.250.10.2:4444'
        BROWSER = 'firefox'
        HEADLESS_VALUE = 'false'
    }

    stages {
         stage('Setup') {
            steps {
                git url:'http://10.250.10.2:8929/root/hello-selenium-java.git', branch: 'parametro'
            }
        }
        
        //Probar las pruebas, haciendo los waits en los test
        stage('Test') {
            steps {
                withGradle {
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=${BROWSER} -Pheadless=${HEADLESS_VALUE}'
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=firefox -Pheadless=${HEADLESS_VALUE}'
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=chrome -Pheadless=${HEADLESS_VALUE}'
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=opera -Pheadless=${HEADLESS_VALUE}'
                    sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=${BROWSER} -Pheadless=${HEADLESS_VALUE}'
                    sh './gradlew checkstyleMain -Premote_server=${SERVER} -Pbrowser=${BROWSER} -Pheadless=${HEADLESS_VALUE}'
                    sh './gradlew checkstyleTest -Premote_server=${SERVER} -Pbrowser=${BROWSER} -Pheadless=${HEADLESS_VALUE}'
                    

                }
            }
            post {
                always {
                    junit 'build/test-results/test/TEST-*.xml'
                    recordIssues(
                        enabledForFailure: true, aggregatingResults: true, 
                        tools: [java(), checkStyle(pattern: 'build/reports/checkstyle/*.xml', reportEncoding: 'UTF-8')]
                    )
                    publishHTML (target: [
                        reportDir: 'build/reports/checkstyle/',
                        reportFiles: 'main.html',
                        reportName: "Reporte Checkstyle"
                    ])
                    publishHTML (target: [
                        reportDir: 'build/reports/checkstyle/',
                        reportFiles: 'test.html',
                        reportName: "Reporte Checkstyle"
                    ])

                }
            }
        }  
        
    }
}

