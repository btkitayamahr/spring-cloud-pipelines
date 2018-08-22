import javaposse.jobdsl.dsl.DslFactory

/**
 *  This script contains logic that
 *
 *  - for each project from the REPOS env variable generates a Jenkinsfile deployment pipeline
 */


DslFactory dsl = this

String jenkinsfileDir = binding.variables["JENKINSFILE_DIR"] ?: "${WORKSPACE}/jenkins/declarative-pipeline"

dsl.pipelineJob('msa-pipeline-discovery') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
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
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-gateway')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-gateway') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-gateway')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-contents') {
	wrappers {
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
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
		parameters {
			stringParam('APP_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The URL with application repository. Also git repository")
			stringParam('APP_BRANCH', 'master', "The branch with application")
		}
	}
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-frontend')}""")
		}
	}
}