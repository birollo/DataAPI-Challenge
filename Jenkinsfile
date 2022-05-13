
def VERSION

pipeline {
  agent any
  tools {
    maven 'maven-3.8.3'
    jdk 'openjdk11'
  }
  stages {
    stage ('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage("Docker") {
        when {
            anyOf {
                branch 'branchName'
            }
        }
        steps {
            script {
                VERSION = """${
                    env.BRANCH_NAME == "master"
                            ? sh(script: '''branch=$(git describe --all)timestamp=$(date +%s)echo ${branch#*release/}-$timestamp''', returnStdout: true).trim()
                            : sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                }"""
                def registry = "https:/tTeamName-docker-local.artifactory.swisscom.com"
                def target = env.BRANCH_NAME == "master" ? 'releases' : 'snapshots'

                    def artifact = "teamName-${target}/DataAPI_Challenge:${VERSION}" //
                    docker.withRegistry(registry, "Featuristic") {
                        def image = docker.build("${artifact}", "-f DataAPI_Challenge/src/docker/Dockerfile DataAPI_Challenge/src/docker")
                        image.push()
                    }

            }
        }
    }
    stage("Deploy") {
        when {
            anyOf {
                branch 'master'
            }
        }
        steps {
            script {
                def environment = env.BRANCH_NAME == "master" ? "prod" : "dev"
                    build(
                        job: "Jenkins viewName",
                        wait: false,
                        parameters: [
                                string(name: "PROJECT", value: "DataAPI_Challenge"),
                                string(name: "ENVIRONMENT", value: "${environment}"),
                                string(name: "VERSION", value: "${VERSION}")
                        ]
                    )
            }
        }
    }
  }
}
