#!groovy

def appName = "ecash/e-cash-core"
def appVersion = "1.0"

node {
    def containerName = "e-cash-core"

    println "Branch name = " + env.BRANCH_NAME

    stage("Checkout ${appName} Repository") {
      checkout scm
    }

    stage("Maven: mvn clean install") {
      withEnv(["PATH+MAVEN=${tool "mvn-3.5.2"}/bin"]) {
          sh "mvn -B clean verify package"
      }
    }

    if (currentBuild.result == "UNSTABLE" || currentBuild.result == "FAILURE") {
        return this;
    }

    if ( env.BRANCH_NAME == "master" ) {
      withEnv(["PATH+MAVEN=${tool "mvn-3.5.2"}/bin"]) {
          sh "mvn -s auth_settings.xml deploy"
      }
    }
}
