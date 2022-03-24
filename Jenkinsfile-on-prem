pipeline {
  agent {
    node {
      label 'maven'
    }

  }
  stages {
    stage('拉取代码') {
      agent none
      steps {
        container('maven') {
          git(url: 'https://github.com/gongjixiaobai/daydaystudy.git', branch: 'master', credentialsId: 'gongjixiaobai', changelog: true, poll: false)
          sh 'ls'
        }

      }
    }

    stage('项目编译') {
      agent none
      steps {
        container('maven') {
          sh 'mvn clean package -Dmaven.test.skip=true'
        }

      }
    }

    stage('构建镜像') {
      agent none
      steps {
        container('maven') {
          sh 'ls'
          sh 'docker build -t daydaystudy:latest -f Dockerfile .'
        }

      }
    }

    stage('推送镜像') {
      agent none
      steps {
        container('maven') {
          withCredentials([usernamePassword(credentialsId : 'docker-hub' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
            sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
            sh 'docker tag daydaystudy:latest $REGISTRY/$DOCKERHUB_NAMESPACE/daydaystudy:SNAPSHOT-$BUILD_NUMBER'
            sh 'docker push $REGISTRY/$DOCKERHUB_NAMESPACE/daydaystudy:SNAPSHOT-$BUILD_NUMBER'
          }

        }

      }
    }

    stage('部署到开发环境') {
      agent none
      steps {
        input(id: 'deploy-to-dev', message: 'deploy to dev?')
        container('maven') {
          withCredentials([
                        kubeconfigFile(
                            credentialsId: env.KUBECONFIG_CREDENTIAL_ID,
                            variable: 'KUBECONFIG')
                        ]) {
                sh 'envsubst < Kubenetes.yml | kubectl apply -f -'
              }

            }

          }
        }

      }
      environment {
        DOCKER_CREDENTIAL_ID = 'dockerhub-id'
        GITHUB_CREDENTIAL_ID = 'github-id'
        KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
        REGISTRY = 'docker.io'
        DOCKERHUB_NAMESPACE = 'whitechicken'
        GITHUB_ACCOUNT = 'kubesphere'
        APP_NAME = 'devops-java-sample'
      }
      parameters {
        string(name: 'TAG_NAME', defaultValue: '', description: '')
      }
    }
