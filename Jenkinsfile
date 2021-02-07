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
                git url:'http://10.250.10.2:8929/root/hello-selenium-java.git', branch: 'main'
            }
        }
        
        //Probar las pruebas, haciendo los waits en los test
        stage('Test') {
            steps {
                withGradle {
                    sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=${BROWSER} -Pheadless=${HEADLESS_VALUE}'
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=firefox -Pheadless=${HEADLESS_VALUE}'
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=chrome -Pheadless=${HEADLESS_VALUE}'
                    //sh './gradlew clean test -Premote_server=${SERVER} -Pbrowser=opera -Pheadless=${HEADLESS_VALUE}'

                }
            }
            post {
                always {
                    junit 'build/test-results/test/TEST-*.xml'
                    recordIssues(
                        enabledForFailure: true, aggregatingResults: true, 
                        tools: [java(), checkStyle(pattern: 'checkstyle-result.xml', reportEncoding: 'UTF-8')]
                    )

                }
            }
        }  
        
    }
}
