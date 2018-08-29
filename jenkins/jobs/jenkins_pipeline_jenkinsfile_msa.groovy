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
envs['HOSTNAME_TEST'] = '13.115.229.114'
envs['DOCKER_REGISTRY_HOSTNAME'] = '13.112.188.210'
envs['DOCKER_REGISTRY_PORT'] = '5000'

dsl.pipelineJob('msa-pipeline-config') {
	envs['APP_NAME'] = 'config'
	envs['DOCKER_COMPOSE_NAME'] = 'docker-compose-config.yml'
/*
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -p 8888:8888 -e SPRING_CLOUD_CONFIG_LABEL=scc -e INFO_CONFIG_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' --net=msa_blank ' + imageTag
*/
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
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-msa2')}""")
		}
	}
}

dsl.pipelineJob('msa-pipeline-discovery') {
	envs['APP_NAME'] = 'discovery'
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -p 9000:9000 -e SPRING_CLOUD_CONFIG_LABEL=scc -e INFO_CONFIG_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' -e INFO_EUREKA_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' --net=msa_blank ' + imageTag

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
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -p 9110:9110 -e SPRING_CLOUD_CONFIG_LABEL=scc -e INFO_CONFIG_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' -e INFO_EUREKA_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' --net=msa_blank ' + imageTag

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
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -p 9120:9120 -e SPRING_CLOUD_CONFIG_LABEL=scc -e INFO_CONFIG_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' -e INFO_EUREKA_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' --net=msa_blank ' + imageTag

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
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -p 9010:9010 -e SPRING_CLOUD_CONFIG_LABEL=scc -e INFO_CONFIG_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' -e INFO_EUREKA_HOSTNAME=' + envs['HOSTNAME_TEST'] + ' --net=msa_blank ' + imageTag

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
