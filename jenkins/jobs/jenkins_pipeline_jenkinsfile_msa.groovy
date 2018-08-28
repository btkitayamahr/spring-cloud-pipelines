import javaposse.jobdsl.dsl.DslFactory

/**
 *  This script contains logic that
 *
 *  - for each project from the REPOS env variable generates a Jenkinsfile deployment pipeline
 */


DslFactory dsl = this

String jenkinsfileDir = binding.variables["JENKINSFILE_DIR"] ?: "${WORKSPACE}/jenkins/declarative-pipeline"
dockerRegistryName = '13.112.188.210'
dockerRegistryPort = '5000'

Map<String, Object> envs = [:]
envs['GIT_REPOSITORY'] = 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git'
envs['GIT_BRANCH_NAME'] = 'master'
envs['GIT_CREDENTIAL_ID'] = 'git-gitbucket'
envs['SSH_CONFIG_NAME_TEST'] = 'msa-ap-test'
envs['SSH_CONFIG_NAME_PROD'] = 'msa-ap-prod'
envs['DOCKER_REGISTRY_HOSTNAME'] = '13.112.188.210'
envs['DOCKER_REGISTRY_PORT'] = '5000'


dsl.pipelineJob('msa-pipeline-discovery') {
	envs['APP_NAME'] = 'discovery'
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' --net=msa_blank -p 9000:9000 ' + imageTag
	wrappers {
		parameters {
			stringParam('GIT_REPOSITORY', envs['GIT_REPOSITORY'], "")
			stringParam('GIT_BRANCH_NAME', envs['GIT_BRANCH_NAME'], "")
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
	String urlEureka = 'EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://54.238.143.77:9000/eureka/'
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -e ' + urlEureka + ' --net=msa_blank -p 9010:9010 ' + imageTag
	wrappers {
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

dsl.pipelineJob('msa-pipeline-config') {
	envs['APP_NAME'] = 'config'
	String imageTag = envs['DOCKER_REGISTRY_HOSTNAME'] + ':' + envs['DOCKER_REGISTRY_PORT'] + '/app/' + envs['APP_NAME']
	String urlEureka = 'EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://54.238.143.77:9000/eureka/'
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -e ' + urlEureka + ' --net=msa_blank -p 8888:8888 ' + imageTag
	wrappers {
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
	String urlEureka = 'EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://54.238.143.77:9000/eureka/'
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -e ' + urlEureka + ' --net=msa_blank -p 9120:9120 ' + imageTag
	wrappers {
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
	String urlEureka = 'EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://54.238.143.77:9000/eureka/'
	envs['DEPLOY_COMMAND_01'] = 'sudo docker stop ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_02'] = 'sudo docker rm ' + envs['APP_NAME']
	envs['DEPLOY_COMMAND_03'] = 'sudo docker rmi ' + imageTag
	envs['DEPLOY_COMMAND_04'] = 'sudo docker run -d --name ' + envs['APP_NAME'] + ' -e ' + urlEureka + ' --net=msa_blank -p 9110:9110 ' + imageTag
	wrappers {
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