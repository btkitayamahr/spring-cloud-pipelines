import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

factory.job('msa-pipeline-seed') {
	scm {
		git {
			remote {
				url('${TOOLS_REPOSITORY}')
				branch('${TOOLS_BRANCH}')
			}
			extensions {
				submoduleOptions {
					recursive()
				}
			}
		}
	}
	wrappers {
		parameters {
			stringParam('GIT_CREDENTIAL_ID', 'git-gitbucket', 'ID of the credentials used to push tags to git repo')
			stringParam('TOOLS_REPOSITORY', 'https://github.com/btkitayamahr/spring-cloud-pipelines.git', "The repository with pipeline functions")
			stringParam('TOOLS_BRANCH', 'develop', "The branch with pipeline functions")
			stringParam('CONFIG_REPOSITORY', 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git', "The repository with config")
			stringParam('CONFIG_BRANCH', 'scc', "The branch with config")
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
