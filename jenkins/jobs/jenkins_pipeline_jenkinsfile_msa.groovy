import javaposse.jobdsl.dsl.DslFactory

/**
 *  This script contains logic that
 *
 *  - for each project from the REPOS env variable generates a Jenkinsfile deployment pipeline
 */


DslFactory dsl = this

String jenkinsfileDir = binding.variables["JENKINSFILE_DIR"] ?: "${WORKSPACE}/jenkins/declarative-pipeline"

Map<String, Object> envs = [:]

envs['GIT_REPOSITORY'] = 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git'
envs['GIT_BRANCH_NAME'] = 'scc'
envs['GIT_CREDENTIAL_ID'] = 'git-gitbucket'
envs['TOOLS_REPOSITORY'] = binding.variables['TOOLS_REPOSITORY']
envs['TOOLS_BRANCH'] = binding.variables['TOOLS_BRANCH']
envs['SSH_CONFIG_NAME_TEST'] = 'msa-ap-test'
envs['SSH_CONFIG_NAME_PROD'] = 'msa-ap-prod'

dsl.pipelineJob('msa-pipeline-config') {
	envs['APP_NAME'] = 'config'

	wrappers {
		parameters {
			stringParam('GIT_REPOSITORY', envs['GIT_REPOSITORY'], "")
			stringParam('GIT_BRANCH_NAME', envs['GIT_BRANCH_NAME'], "")
			stringParam('GIT_CREDENTIAL_ID', envs['GIT_CREDENTIAL_ID'], "")
			stringParam('SSH_CONFIG_NAME_TEST', envs['SSH_CONFIG_NAME_TEST'], "")
			stringParam('SSH_CONFIG_NAME_PROD', envs['SSH_CONFIG_NAME_PROD'], "")
			stringParam('DOCKER_REGISTRY_HOSTNAME', envs['DOCKER_REGISTRY_HOSTNAME'], "")
			stringParam('DOCKER_REGISTRY_PORT', envs['DOCKER_REGISTRY_PORT'], "")
		}
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-msa')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-discovery') {
	envs['APP_NAME'] = 'discovery'

	wrappers {
		parameters {
			stringParam('GIT_REPOSITORY', envs['GIT_REPOSITORY'], "")
			stringParam('GIT_BRANCH_NAME', envs['GIT_BRANCH_NAME'], "")
			stringParam('GIT_CREDENTIAL_ID', envs['GIT_CREDENTIAL_ID'], "")
			stringParam('SSH_CONFIG_NAME_TEST', envs['SSH_CONFIG_NAME_TEST'], "")
			stringParam('SSH_CONFIG_NAME_PROD', envs['SSH_CONFIG_NAME_PROD'], "")
			stringParam('DOCKER_REGISTRY_HOSTNAME', envs['DOCKER_REGISTRY_HOSTNAME'], "")
			stringParam('DOCKER_REGISTRY_PORT', envs['DOCKER_REGISTRY_PORT'], "")
		}
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-msa')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-frontend') {
	envs['APP_NAME'] = 'frontend'

	wrappers {
		parameters {
			stringParam('GIT_REPOSITORY', envs['GIT_REPOSITORY'], "")
			stringParam('GIT_BRANCH_NAME', envs['GIT_BRANCH_NAME'], "")
			stringParam('GIT_CREDENTIAL_ID', envs['GIT_CREDENTIAL_ID'], "")
			stringParam('SSH_CONFIG_NAME_TEST', envs['SSH_CONFIG_NAME_TEST'], "")
			stringParam('SSH_CONFIG_NAME_PROD', envs['SSH_CONFIG_NAME_PROD'], "")
			stringParam('DOCKER_REGISTRY_HOSTNAME', envs['DOCKER_REGISTRY_HOSTNAME'], "")
			stringParam('DOCKER_REGISTRY_PORT', envs['DOCKER_REGISTRY_PORT'], "")
		}
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-msa')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-contents') {
	envs['APP_NAME'] = 'contents'

	wrappers {
		parameters {
			stringParam('GIT_REPOSITORY', envs['GIT_REPOSITORY'], "")
			stringParam('GIT_BRANCH_NAME', envs['GIT_BRANCH_NAME'], "")
			stringParam('GIT_CREDENTIAL_ID', envs['GIT_CREDENTIAL_ID'], "")
			stringParam('SSH_CONFIG_NAME_TEST', envs['SSH_CONFIG_NAME_TEST'], "")
			stringParam('SSH_CONFIG_NAME_PROD', envs['SSH_CONFIG_NAME_PROD'], "")
			stringParam('DOCKER_REGISTRY_HOSTNAME', envs['DOCKER_REGISTRY_HOSTNAME'], "")
			stringParam('DOCKER_REGISTRY_PORT', envs['DOCKER_REGISTRY_PORT'], "")
		}
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-msa')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-gateway') {
	envs['APP_NAME'] = 'gateway'

	wrappers {
		parameters {
			stringParam('GIT_REPOSITORY', envs['GIT_REPOSITORY'], "")
			stringParam('GIT_BRANCH_NAME', envs['GIT_BRANCH_NAME'], "")
			stringParam('GIT_CREDENTIAL_ID', envs['GIT_CREDENTIAL_ID'], "")
			stringParam('SSH_CONFIG_NAME_TEST', envs['SSH_CONFIG_NAME_TEST'], "")
			stringParam('SSH_CONFIG_NAME_PROD', envs['SSH_CONFIG_NAME_PROD'], "")
			stringParam('DOCKER_REGISTRY_HOSTNAME', envs['DOCKER_REGISTRY_HOSTNAME'], "")
			stringParam('DOCKER_REGISTRY_PORT', envs['DOCKER_REGISTRY_PORT'], "")
		}
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-msa')}""")
		}
	}
}
