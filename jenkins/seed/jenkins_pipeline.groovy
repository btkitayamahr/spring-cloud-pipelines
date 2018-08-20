import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

// remove::start[CF]
String repos = 'https://github.com/marcingrzejszczak/github-analytics,https://github.com/marcingrzejszczak/github-webhook'
// remove::end[CF]

// remove::start[K8S]
String kubernetesRepos = 'https://github.com/marcingrzejszczak/github-analytics-kubernetes,https://github.com/marcingrzejszczak/github-webhook-kubernetes'
// remove::end[K8S]


factory.job('msa-pipeline-seed') {
	scm {
		git {
			remote {
				github('btkitayamahr/spring-cloud-pipelines')
			}
			branch('${TOOLS_BRANCH}')
			extensions {
				submoduleOptions {
					recursive()
				}
			}
		}
	}
	wrappers {
		parameters {
			// Common
			stringParam('REPOS', kubernetesRepos,
				"Provide a comma separated list of repos. If you want the project name to be different then repo name, " +
					"first provide the name and separate the url with \$ sign")
			stringParam('GIT_CREDENTIAL_ID', 'git', 'ID of the credentials used to push tags to git repo')
			stringParam('GIT_SSH_CREDENTIAL_ID', 'gitSsh', 'ID of the ssh credentials used to push tags to git repo')
			booleanParam('GIT_USE_SSH_KEY', false, 'Should ssh key be used for git')
			stringParam('JDK_VERSION', 'jdk8', 'ID of Git installation')
			stringParam('M2_SETTINGS_REPO_ID', 'artifactory-local', "Name of the server ID in Maven's settings.xml")
			stringParam('REPO_WITH_BINARIES_FOR_UPLOAD', 'http://artifactory:8081/artifactory/libs-release-local', "Address to hosted JARs")
			stringParam('REPO_WITH_BINARIES_CREDENTIAL_ID', 'repo-with-binaries', "Credential ID of repo with binaries")
			stringParam('GIT_EMAIL', 'email@example.com', "Email used to tag the repo")
			stringParam('GIT_NAME', 'Pivo Tal', "Name used to tag the repo")
			stringParam('TOOLS_REPOSITORY', 'https://github.com/spring-cloud/spring-cloud-pipelines/archive/master.tar.gz', "The URL to tarball containing pipeline functions repository. Also git repository is supported (for backward compatibility)")
			stringParam('TOOLS_BRANCH', 'master', "The branch with pipeline functions")
			booleanParam('AUTO_DEPLOY_TO_STAGE', false, 'Should deployment to stage be automatic')
			booleanParam('AUTO_DEPLOY_TO_PROD', false, 'Should deployment to prod be automatic')
			booleanParam('API_COMPATIBILITY_STEP_REQUIRED', true, 'Should api compatibility step be present')
			booleanParam('DB_ROLLBACK_STEP_REQUIRED', true, 'Should DB rollback step be present')
			booleanParam('DEPLOY_TO_STAGE_STEP_REQUIRED', true, 'Should deploy to stage step be present')
			stringParam('PAAS_TYPE', 'k8s', "Which PAAS do you want to choose")
			booleanParam('KUBERNETES_MINIKUBE', true, 'Will you connect to Minikube?')
			stringParam('MYSQL_ROOT_CREDENTIAL_ID', "mysql-root", 'Credential ID of MYSQL root user')
			stringParam('MYSQL_CREDENTIAL_ID', "mysql", 'Credential ID of MYSQL user')
			stringParam('DOCKER_REGISTRY_CREDENTIAL_ID', 'docker-registry', "Credential ID of docker registry")
			stringParam('DOCKER_SERVER_ID', 'docker-repo', "Docker Server ID")
			stringParam('DOCKER_EMAIL', 'change@me.com', "Email used to connect to Docker registry")
			stringParam('DOCKER_REGISTRY_ORGANIZATION', 'scpipelines', 'URL to Kubernetes cluster for test env')
			stringParam('DOCKER_REGISTRY_URL', 'https://index.docker.io/v1/', 'URL to the docker registry')
			stringParam('PIPELINE_DESCRIPTOR', '', "The name of the pipeline descriptor. If none is set then `sc-pipelines.yml` will be assumed")

			stringParam('PAAS_TEST_API_URL', '192.168.99.100:8443', 'URL to Kubernetes cluster for test env')
			stringParam('PAAS_STAGE_API_URL', '192.168.99.100:8443', 'URL to Kubernetes cluster for stage env')
			stringParam('PAAS_PROD_API_URL', '192.168.99.100:8443', 'URL to Kubernetes cluster for prod env')
			stringParam('PAAS_TEST_CA_PATH', '/usr/share/jenkins/cert/ca.crt', "Certificate Authority location for test env")
			stringParam('PAAS_STAGE_CA_PATH', '/usr/share/jenkins/cert/ca.crt', "Certificate Authority location for stage env")
			stringParam('PAAS_PROD_CA_PATH', '/usr/share/jenkins/cert/ca.crt', "Certificate Authority location for prod env")
			stringParam('PAAS_TEST_CLIENT_CERT_PATH', '/usr/share/jenkins/cert/apiserver.crt', "Client Certificate location for test env")
			stringParam('PAAS_STAGE_CLIENT_CERT_PATH', '/usr/share/jenkins/cert/apiserver.crt', "Client Certificate location for stage env")
			stringParam('PAAS_PROD_CLIENT_CERT_PATH', '/usr/share/jenkins/cert/apiserver.crt', "Client Certificate location for prod env")
			stringParam('PAAS_TEST_CLIENT_KEY_PATH', '/usr/share/jenkins/cert/apiserver.key', "Client Key location for test env")
			stringParam('PAAS_STAGE_CLIENT_KEY_PATH', '/usr/share/jenkins/cert/apiserver.key', "Client Key location for stage env")
			stringParam('PAAS_PROD_CLIENT_KEY_PATH', '/usr/share/jenkins/cert/apiserver.key', "Client Key location for prod env")
			stringParam('PAAS_TEST_CLIENT_TOKEN_PATH', '', "Path to the file containing the token for test env")
			stringParam('PAAS_STAGE_CLIENT_TOKEN_PATH', '', "Path to the file containing the token for stage env")
			stringParam('PAAS_PROD_CLIENT_TOKEN_PATH', '', "Path to the file containing the token for prod env")
			stringParam('PAAS_TEST_CLIENT_TOKEN_ID', '', "ID of the credentials containing a token used by Kubectl for test env. Takes precedence over client key")
			stringParam('PAAS_STAGE_CLIENT_TOKEN_ID', '', "ID of the credentials containing a token used by Kubectl for stage env. Takes precedence over client key")
			stringParam('PAAS_PROD_CLIENT_TOKEN_ID', '', "ID of the credentials containing a token used by Kubectl for prod env. Takes precedence over client key")
			stringParam('PAAS_TEST_CLUSTER_NAME', 'minikube', "Name of the cluster for test env")
			stringParam('PAAS_STAGE_CLUSTER_NAME', 'minikube', "Name of the cluster for stage env")
			stringParam('PAAS_PROD_CLUSTER_NAME', 'minikube', "Name of the cluster for prod env")
			stringParam('PAAS_TEST_CLUSTER_USERNAME', 'minikube', "Username for the cluster for test env")
			stringParam('PAAS_STAGE_CLUSTER_USERNAME', 'minikube', "Username for the cluster for stage env")
			stringParam('PAAS_PROD_CLUSTER_USERNAME', 'minikube', "Username for the cluster for prod env")
			stringParam('PAAS_TEST_SYSTEM_NAME', 'minikube', "Name for the system for test env")
			stringParam('PAAS_STAGE_SYSTEM_NAME', 'minikube', "Name for the system for stage env")
			stringParam('PAAS_PROD_SYSTEM_NAME', 'minikube', "Name for the system for prod env")
			stringParam('PAAS_TEST_NAMESPACE', 'sc-pipelines-test', 'Namespace for the test env')
			stringParam('PAAS_STAGE_NAMESPACE', 'sc-pipelines-stage', 'Namespace for the stage env')
			stringParam('PAAS_PROD_NAMESPACE', 'sc-pipelines-prod', 'Namespace for the prod env')
		}
	}
	steps {
		gradle("clean build -x test")
		dsl {
			external('jenkins/jobs/jenkins_pipeline_jenkinsfile_msa.groovy')
			removeAction('DISABLE')
			removeViewAction('DELETE')
			ignoreExisting(false)
			lookupStrategy('SEED_JOB')
			additionalClasspath([
				'jenkins/src/main/groovy', 'jenkins/src/main/resources', 'jenkins/build/lib/*.*'
			].join("\n"))
		}
	}
}
