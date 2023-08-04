import java.text.SimpleDateFormat

def defDateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def defDate = new Date()
def defTimestamp = defDateFormat.format(defDate).toString()


pipeline {

    agent any

    tools {
        maven 'M3'
        jdk 'jdk8.221'
    }

    options {
		buildDiscarder(logRotator(numToKeepStr: '20'))
	}

    stages {

        stage ('Build') {
            steps {
            	script{
            		currentBuild.displayName = "Api-Testing [#${BUILD_NUMBER}]"
            	}
                bat ("mvn -X clean verify")
            }
        }

        stage('Ejecutar Pruebas'){
            steps{
        			script {
        				try {
        					    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE', message: 'Test Suite had a failure') {
                                 bat ("mvn test -Dcucumber.features='src/test/resources/features/' -Dcucumber.filter.tags=\'${ESCENARIO}\' -Dcucumber.plugin=junit:target/site/result.xml -Dcucumber.glue='steps'")
	        			    }
                            catch (ex) {
                                echo 'Finalizo ejecucion con fallos...'
                                error ('Failed')
                                 }
	                         }
	                    }
                    }
            }

        stage ('Reporte') {
        	steps {
        		script {
                     try {
                     	bat ("mvn serenity:aggregate")
	        			echo 'Ejecucion de pruebas sin errores...'
                    	bat ("echo ${defTimestamp}")
                    	publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "${WORKSPACE}/target/site/serenity", reportFiles: 'index.html', reportName: 'Evidencias de Prueba', reportTitles: 'Reporte de Pruebas'])
                        echo 'Reporte realizado con exito'
                    }
                    catch (ex) {
                        echo 'Reporte realizado con Fallos'
                        error ('Failed')
                    }
                }
            }
        }
    }
}
