import javaposse.jobdsl.dsl.DslFactory

/**
 *  This script contains logic that
 *
 *  - for each project from the REPOS env variable generates a Jenkinsfile deployment pipeline
 */


DslFactory dsl = this

String jenkinsfileDir = binding.variables["JENKINSFILE_DIR"] ?: "${WORKSPACE}/jenkins/declarative-pipeline"

dsl.pipelineJob('msa-pipline-discovery-build') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-discovery-build')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-discovery-deploy') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-discovery-deploy')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-gateway-build') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-gateway-build')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-gateway-deploy') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-gateway-deploy')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-contents-build') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-contents-build')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-contents-deploy') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-contents-deploy')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-frontend-build') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-frontend-build')}""")
		}
	}
}

dsl.pipelineJob('msa-pipline-frontend-deploy') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-frontend-deploy')}""")
		}
	}
}