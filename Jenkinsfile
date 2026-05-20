pipeline {
    agent any

    parameters {
        string(name: 'GIT_BRANCH',             defaultValue: 'main', description: 'Branch de GitLab que dispara el build')
        string(name: 'GIT_COMMIT',             defaultValue: '',       description: 'SHA del commit desde GitLab')
        string(name: 'GITLAB_USER',            defaultValue: '',       description: 'Usuario de GitLab que disparó el pipeline')
        string(name: 'GITLAB_PIPELINE_ID',     defaultValue: '',       description: 'ID del pipeline en GitLab')
        string(name: 'GITLAB_PROJECT_URL',     defaultValue: '',       description: 'URL del proyecto en GitLab')
        string(name: 'GITLAB_PIPELINE_SOURCE', defaultValue: '',       description: 'Origen del pipeline (push, merge_request_event, etc)')
        string(name: 'GITLAB_MR_IID',          defaultValue: '',       description: 'IID del Merge Request')
        string(name: 'GITLAB_MR_TITLE',        defaultValue: '',       description: 'Título del Merge Request')
        string(name: 'GITLAB_SOURCE_BRANCH',   defaultValue: '',       description: 'Branch origen del MR')
        string(name: 'GITLAB_TARGET_BRANCH',   defaultValue: '',       description: 'Branch destino del MR')
    }

    environment {
        JAVA_HOME = "${env.JAVA_HOME ?: '/usr/lib/jvm/java-21-amazon-corretto.x86_64'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"

        // Service Configuration
        PROJECT_NAME = "ce-tr-catalogo-admin-b"
        SERVICE_DISPLAY_NAME = "SAT Trámites - ce-tr-catalogo-admin-b"
        SERVICE_TYPE = "rest-api"
        SERVICE_PORT = "8080"

        // Deployment configuration
        ENVIRONMENT = "development"
        NAMESPACE = "vucem3-apps"

        // Java and Maven configuration
        JAVA_VERSION = "${env.JAVA_VERSION ?: '21'}"
        MAVEN_REPO_PATH = "${env.MAVEN_REPO_PATH ?: '/var/lib/jenkins/.m2/repository'}"
        MAVEN_OPTS = "${env.MAVEN_OPTS ?: "-Dmaven.repo.local=${MAVEN_REPO_PATH} -Xmx2048m"}"
        MAVEN_CLI_OPTS = "${env.MAVEN_CLI_OPTS ?: "-B -V --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true -s ${WORKSPACE}/.m2/settings.xml"}"

        // Nexus Maven Repository URLs
        NEXUS_PUBLIC_URL = "${env.NEXUS_PUBLIC_URL}"
        NEXUS_RELEASES_URL = "${env.NEXUS_RELEASES_URL}"
        NEXUS_SNAPSHOTS_URL = "${env.NEXUS_SNAPSHOTS_URL}"

        // Docker configuration
        DOCKER_BUILDKIT = "${env.DOCKER_BUILDKIT ?: '1'}"

        // Build information - initialized here, updated in Extract POM Info stage
        BUILD_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT.take(8)}"
        BUILD_TIMESTAMP = sh(script: "date -u +'%Y-%m-%dT%H:%M:%SZ'", returnStdout: true).trim()
        POM_VERSION = ""

        // Nexus credentials
        NEXUS_CREDENTIALS = credentials('nexus-credentials')
        NEXUS_USERNAME = "${NEXUS_CREDENTIALS_USR}"
        NEXUS_PASSWORD = "${NEXUS_CREDENTIALS_PSW}"

        // Docker Registry
        DOCKER_REGISTRY_HOST = "${env.DOCKER_REGISTRY_HOST}"
        DOCKER_REGISTRY = "${DOCKER_REGISTRY_HOST}"
        DOCKER_IMAGE_NAME = "${env.DOCKER_IMAGE_NAME ?: "nexus.vucem.ultrasist.org/repository/docker-hosted/${PROJECT_NAME}"}"

        // SonarQube configuration
        SONAR_PROJECT_KEY = "${PROJECT_NAME}"
        SONAR_PROJECT_NAME = "${SERVICE_DISPLAY_NAME}"
        SONAR_HOST_URL = "${env.SONAR_HOST_URL}"
        SONAR_TOKEN = "${env.SONAR_TOKEN}"

        // Security scanning
        TRIVY_CACHE_DIR = "${env.TRIVY_CACHE_DIR ?: '.trivy-cache'}"
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '5'))
        timeout(time: 60, unit: 'MINUTES')
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Validate Project') {
            steps {
                script {
                    echo "Validating ${SERVICE_DISPLAY_NAME} project structure..."
                    dir('CODIGO') {
                        sh '''
                            test -f pom.xml || (echo "pom.xml not found" && exit 1)
                            test -f Dockerfile || (echo "Dockerfile not found" && exit 1)
                            echo "Project validation completed"
                        '''
                    }
                }
            }
        }

        stage('Setup Tools') {
            steps {
                script {
                    sh '''
                        echo "Checking Java..."
                        java -version || echo "Java not found"

                        echo "Checking Maven..."
                        mvn -version || echo "Maven not found"

                        echo "Checking Docker..."
                        docker --version || echo "Docker not found"
                    '''
                }
            }
        }

        stage('Setup Maven') {
            steps {
                script {
                    sh '''
                        mkdir -p ${WORKSPACE}/.m2
                        cat > ${WORKSPACE}/.m2/settings.xml <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>nexus-releases</id>
      <username>${NEXUS_USERNAME}</username>
      <password>${NEXUS_PASSWORD}</password>
    </server>
    <server>
      <id>nexus-snapshots</id>
      <username>${NEXUS_USERNAME}</username>
      <password>${NEXUS_PASSWORD}</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>nexus</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo.maven.apache.org/maven2</url>
          <releases><enabled>true</enabled></releases>
          <snapshots><enabled>false</enabled></snapshots>
        </repository>
        <repository>
          <id>nexus-releases</id>
          <url>${NEXUS_RELEASES_URL}</url>
          <releases><enabled>true</enabled></releases>
          <snapshots><enabled>false</enabled></snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>nexus</activeProfile>
  </activeProfiles>
</settings>
EOF
                        echo "Maven settings.xml configured"
                    '''
                }
            }
        }

        stage('Extract POM Info') {
            steps {
                script {
                    echo "Extracting information from pom.xml..."
                    dir('CODIGO') {
                        def pomVersion = sh(
                            script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout -Dmaven.repo.local=${MAVEN_REPO_PATH}',
                            returnStdout: true
                        ).trim()

                        if (pomVersion && pomVersion != '' && !pomVersion.contains('ERROR')) {
                            env.POM_VERSION = pomVersion
                            env.BUILD_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT.take(8)}"
                        } else {
                            env.POM_VERSION = "unknown"
                            env.BUILD_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT.take(8)}"
                        }

                        echo "POM Version: ${env.POM_VERSION}"
                        echo "Build Version: ${env.BUILD_VERSION}"
                    }
                }
            }
        }

        stage('Build: Compile') {
            steps {
                script {
                    echo "Compiling ${SERVICE_DISPLAY_NAME}..."
                    dir('CODIGO') {
                        sh '''
                            echo "Cleaning cached resolution failures..."
                            find ${MAVEN_REPO_PATH}/mx/gob/sat -name "*.lastUpdated" -delete 2>/dev/null || true
                            find ${MAVEN_REPO_PATH}/mx/gob/sat -name "_remote.repositories" -delete 2>/dev/null || true

                            mvn ${MAVEN_CLI_OPTS} -U clean compile \
                                -Duser.home=${WORKSPACE} \
                                -Dmaven.repo.local=${MAVEN_REPO_PATH} \
                                -Dmaven.test.skip=true

                            echo "Compilation completed"
                        '''
                    }
                }
            }
        }

        stage('Test: Unit') {
            steps {
                script {
                    echo "Running unit tests..."
                    dir('CODIGO') {
                        sh '''
                            if [ -d "src/test/java" ]; then
                                mvn ${MAVEN_CLI_OPTS} test \
                                    -Duser.home=${WORKSPACE} \
                                    -Dmaven.repo.local=${MAVEN_REPO_PATH} \
                                    -DfailIfNoTests=false || true
                            else
                                echo "No test directory found, skipping tests"
                            fi
                        '''
                    }
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'CODIGO/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Quality: SonarQube') {
            when {
                expression {
                    // Only run if SonarQube is configured in Jenkins
                    return env.SONAR_HOST_URL != null && env.SONAR_TOKEN != null
                }
            }
            steps {
                script {
                    echo "🔍 Running SonarQube analysis..."
                    echo "  • SonarQube URL: ${SONAR_HOST_URL}"
                    echo "  • Project Key: ${SONAR_PROJECT_KEY}"
                    dir('CODIGO') {
                        try {
                            sh '''
                                # Find Maven executable
                                find_maven() {
                                    # Set JAVA_HOME if not set
                                    if [ -z "$JAVA_HOME" ] && command -v java >/dev/null 2>&1; then
                                        export JAVA_HOME=$(dirname "$(dirname "$(readlink -f "$(which java)")")" 2>/dev/null || echo "")
                                    fi

                                    # Set MAVEN_HOME if installed in /opt/maven
                                    if [ -d /opt/maven ]; then
                                        export MAVEN_HOME=/opt/maven
                                        export PATH=$MAVEN_HOME/bin:$PATH
                                    fi

                                    if command -v mvn >/dev/null 2>&1; then echo "mvn"; return 0; fi
                                    for MVN_PATH in /usr/bin/mvn /usr/local/bin/mvn /usr/share/maven/bin/mvn /opt/maven/bin/mvn; do
                                        if [ -x "$MVN_PATH" ]; then echo "$MVN_PATH"; return 0; fi
                                    done
                                    echo "mvn"  # Fallback
                                }

                                MVN_CMD=$(find_maven)
                                echo "Using Maven: $MVN_CMD"
                                echo "JAVA_HOME: ${JAVA_HOME:-Not set}"

                                mkdir -p ${MAVEN_REPO_PATH}

                                # Build sonar command with conditional test directory
                                SONAR_OPTS="-Duser.home=${WORKSPACE}"
                                SONAR_OPTS="$SONAR_OPTS -Dmaven.repo.local=${MAVEN_REPO_PATH}"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.host.url=${SONAR_HOST_URL}"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.token=${SONAR_TOKEN}"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.projectKey=${SONAR_PROJECT_KEY}"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.projectName='${SONAR_PROJECT_NAME}'"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.java.source=${JAVA_VERSION}"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.java.target=${JAVA_VERSION}"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.sources=src/main/java"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.java.binaries=target/classes"
                                SONAR_OPTS="$SONAR_OPTS -Dsonar.qualitygate.wait=false"

                                # Add test directory only if it exists
                                if [ -d "src/test/java" ]; then
                                    SONAR_OPTS="$SONAR_OPTS -Dsonar.tests=src/test/java"
                                fi

                                eval "$MVN_CMD ${MAVEN_CLI_OPTS} sonar:sonar $SONAR_OPTS"

                                echo "✅ SonarQube analysis completed"
                            '''
                        } catch (Exception e) {
                            echo "⚠️  SonarQube analysis failed: ${e.message}"
                            echo "ℹ️  Continuing pipeline without SonarQube analysis"
                        }
                    }
                }
            }
        }

        stage('Package: JAR') {
            steps {
                script {
                    echo "Packaging ${SERVICE_DISPLAY_NAME}..."
                    dir('CODIGO') {
                        sh '''
                            mvn ${MAVEN_CLI_OPTS} package \
                                -Duser.home=${WORKSPACE} \
                                -Dmaven.repo.local=${MAVEN_REPO_PATH} \
                                -DskipTests=true

                            JAR_FILE=$(find target -name "*.jar" -not -name "*-sources.jar" | head -1)
                            test -f "$JAR_FILE" || (echo "JAR file not found" && exit 1)
                            echo "JAR file created: $JAR_FILE"
                            ls -lh "$JAR_FILE"
                        '''
                    }
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'CODIGO/target/*.jar', fingerprint: true
                }
            }
        }

        stage('Build: Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    dir('CODIGO') {
                        sh """
                            docker build \
                                --build-arg APP_VERSION=${env.BUILD_VERSION} \
                                --build-arg BUILD_TIME=${BUILD_TIMESTAMP} \
                                -t ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION} \
                                -t ${DOCKER_IMAGE_NAME}:latest \
                                .

                            echo "Docker image built successfully"
                            docker images ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}
                        """
                    }
                }
            }
        }

        stage('Security: Trivy Scan') {
            steps {
                script {
                    echo "Scanning Docker image with Trivy..."
                    try {
                        sh """
                            mkdir -p ${TRIVY_CACHE_DIR}

                            trivy image \
                                --cache-dir ${TRIVY_CACHE_DIR} \
                                --format table \
                                --exit-code 0 \
                                --severity HIGH,CRITICAL \
                                ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}

                            trivy image \
                                --cache-dir ${TRIVY_CACHE_DIR} \
                                --format json \
                                --output trivy-image-report.json \
                                --exit-code 0 \
                                ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}

                            echo "Trivy scan completed"
                        """
                    } catch (Exception e) {
                        echo "Trivy scan failed: ${e.message}"
                        sh 'echo "{}" > trivy-image-report.json'
                    }
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'trivy-image-report.json', allowEmptyArchive: true
                }
            }
        }

        stage('Publish: Docker Image') {
            steps {
                script {
                    echo "========================================="
                    echo "PUSHING DOCKER IMAGE TO REGISTRY"
                    echo "========================================="

                    withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                        sh """
                            set +x
                            echo "Logging into Docker registry..."
                            echo "Registry: ${DOCKER_REGISTRY}"
                            docker login -u \$NEXUS_USER -p \$NEXUS_PASS ${DOCKER_REGISTRY}

                            echo "Pushing Docker image: ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}"
                            docker push ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}

                            echo "Pushing Docker image: ${DOCKER_IMAGE_NAME}:latest"
                            docker push ${DOCKER_IMAGE_NAME}:latest

                            echo "Docker images pushed successfully"
                            docker logout ${DOCKER_REGISTRY}
                        """
                    }
                }
            }
        }

        stage('Deploy: Development') {
            steps {
                script {
                    echo "Deploying to Rancher (vucem3-tramites-dev)..."

                    withCredentials([file(credentialsId: 'rancher-kubeconfig-tramites', variable: 'KUBECONFIG')]) {
                        dir('CODIGO') {
                            sh """
                                set -e

                                export ENVIRONMENT=${ENVIRONMENT}
                                export DOCKER_IMAGE=${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}
                                export VERSION=${env.BUILD_VERSION}
                                export NAMESPACE=${NAMESPACE}

                                echo "Deployment configuration:"
                                echo "  Cluster: Rancher vucem3-tramites-dev"
                                echo "  Namespace: \$NAMESPACE"
                                echo "  Image: \$DOCKER_IMAGE"

                                if [ -f "k8s-deployment.yaml" ]; then
                                    envsubst '\${DOCKER_IMAGE}' < k8s-deployment.yaml | kubectl apply -f -

                                    kubectl rollout status deployment/${PROJECT_NAME} -n \$NAMESPACE --timeout=600s
                                    kubectl get pods -n \$NAMESPACE -l app=${PROJECT_NAME}
                                else
                                    echo "k8s-deployment.yaml not found, skipping deployment"
                                fi

                                echo "Deployment completed"
                            """
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                echo "Pipeline execution finished"
            }
        }
        success {
            script {
                echo ""
                echo "========================================================"
                echo "PIPELINE COMPLETED SUCCESSFULLY!"
                echo "========================================================"
                echo "Service: ${PROJECT_NAME}"
                echo "Build Version: ${env.BUILD_VERSION}"
                echo "Docker Image: ${DOCKER_IMAGE_NAME}:${env.BUILD_VERSION}"
                echo "========================================================"
            }
        }
        failure {
            script {
                echo "Pipeline failed. Please check the logs."
            }
        }
    }
}
