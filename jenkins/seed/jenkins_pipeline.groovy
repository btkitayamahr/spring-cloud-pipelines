import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

factory.job('msa-pipeline-seed') {
	scm {
		git {
			remote {
//				github('btkitayamahr/spring-cloud-pipelines')
//			branch('${TOOLS_BRANCH}')
				git('${TOOLS_REPOSITORY}', '${TOOLS_BRANCH}')
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
			stringParam('TOOLS_REPOSITORY', 'https://btkitayamahr:btkitayamahr@terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verifica
tion.git', "The repository with pipeline functions")
			stringParam('TOOLS_BRANCH', 'master', "The branch with pipeline functions")
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
