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
envs['GIT_BRANCH_NAME'] = 'master'
envs['GIT_CREDENTIAL_ID'] = 'git-gitbucket'
envs['SSH_CONFIG_NAME_TEST'] = 'msa-ap-test'
envs['DOCKER_REGISTRY_HOSTNAME'] = '13.112.188.210'
envs['DOCKER_REGISTRY_PORT'] = '5000'

dsl.pipelineJob('msa-pipeline-discovery') {
	wrappers {
/*
		parameters {
			stringParam('GIT_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('GIT_BRANCH_NAME', 'master', "The branch with application")
			stringParam('GIT_CREDENTIAL_ID', 'git-gitbucket', "")
			stringParam('SSH_CONFIG_NAME_TEST', 'msa-ap-test', "Setting name of ssh connection")
			stringParam('DOCKER_REGISTRY_HOSTNAME', '13.112.188.210', "")
			stringParam('DOCKER_REGISTRY_PORT', '5000', "")
		}
*/
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-discovery')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-gateway') {
	wrappers {
/*
		parameters {
			stringParam('GIT_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('GIT_BRANCH_NAME', 'master', "The branch with application")
			stringParam('GIT_CREDENTIAL_ID', 'git-gitbucket', "")
			stringParam('SSH_CONFIG_NAME_TEST', 'msa-ap-test', "Setting name of ssh connection")
			stringParam('DOCKER_REGISTRY_HOSTNAME', '13.112.188.210', "")
			stringParam('DOCKER_REGISTRY_PORT', '5000', "")
		}
*/
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-gateway')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-config') {
	wrappers {
/*
		parameters {
			stringParam('GIT_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('GIT_BRANCH_NAME', 'master', "The branch with application")
			stringParam('GIT_CREDENTIAL_ID', 'git-gitbucket', "")
			stringParam('SSH_CONFIG_NAME_TEST', 'msa-ap-test', "Setting name of ssh connection")
			stringParam('DOCKER_REGISTRY_HOSTNAME', '13.112.188.210', "")
			stringParam('DOCKER_REGISTRY_PORT', '5000', "")
		}
*/
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-config')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-contents') {
	wrappers {
/*
		parameters {
			stringParam('GIT_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('GIT_BRANCH_NAME', 'master', "The branch with application")
			stringParam('GIT_CREDENTIAL_ID', 'git-gitbucket', "")
			stringParam('SSH_CONFIG_NAME_TEST', 'msa-ap-test', "Setting name of ssh connection")
			stringParam('DOCKER_REGISTRY_HOSTNAME', '13.112.188.210', "")
			stringParam('DOCKER_REGISTRY_PORT', '5000', "")
		}
*/
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-contents')}""")
		}
	}
}


dsl.pipelineJob('msa-pipeline-frontend') {
	wrappers {
/*
		parameters {
			stringParam('GIT_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('GIT_BRANCH_NAME', 'master', "The branch with application")
			stringParam('GIT_CREDENTIAL_ID', 'git-gitbucket', "")
			stringParam('SSH_CONFIG_NAME_TEST', 'msa-ap-test', "Setting name of ssh connection")
			stringParam('DOCKER_REGISTRY_HOSTNAME', '13.112.188.210', "")
			stringParam('DOCKER_REGISTRY_PORT', '5000', "")
		}
*/
		environmentVariables {
			environmentVariables(envs)
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-frontend')}""")
		}
	}
}