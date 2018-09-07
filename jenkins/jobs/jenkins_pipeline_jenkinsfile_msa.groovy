import javaposse.jobdsl.dsl.DslFactory

/**
 *  This script contains logic that
 *
 *  - for each project from the REPOS env variable generates a Jenkinsfile deployment pipeline
 */


DslFactory dsl = this

// Git credentials to use
String gitCredentials = binding.variables["GIT_CREDENTIAL_ID"] ?: "git"
String gitSshCredentials = binding.variables["GIT_SSH_CREDENTIAL_ID"] ?: "gitSsh"
Boolean gitUseSshKey= Boolean.parseBoolean(binding.variables["GIT_USE_SSH_KEY"] ?: "false")
// we're parsing the REPOS parameter to retrieve list of repos to build
String repos = binding.variables["REPOS"] ?: [
		"https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/btkitayamahr/config",
		"https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/btkitayamahr/contents",
		"https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/btkitayamahr/discovery",
		"https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/btkitayamahr/frontend",
		"https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/btkitayamahr/gateway"
	].join(",")
List<String> parsedRepos = repos.split(",")
String jenkinsfileDir = binding.variables["JENKINSFILE_DIR"] ?: "${WORKSPACE}/jenkins/declarative-pipeline"

Map<String, Object> envs = [:]
envs['PIPELINE_VERSION'] = binding.variables["PIPELINE_VERSION"] ?: ""
envs['REPO_WITH_BINARIES_CREDENTIAL_ID'] = binding.variables['REPO_WITH_BINARIES_CREDENTIAL_ID'] ?: ''
envs['GIT_REPOSITORY'] = 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git'
envs['GIT_BRANCH_NAME'] = 'scc'
envs['TOOLS_REPOSITORY'] = binding.variables["TOOLS_REPOSITORY"]
envs['TOOLS_BRANCH'] = binding.variables["TOOLS_BRANCH"]
envs['GIT_CREDENTIAL_ID'] = 'git-gitbucket'
envs['CONFIG_REPOSITORY'] = 'https://terasoluna-cloud-management-423625999.ap-northeast-1.elb.amazonaws.com/git/terasoluna-msa/verification.git'
envs['CONFIG_BRANCH'] = 'scc'
envs['CONFIG_CREDENTIAL_ID'] = 'git-gitbucket'
envs['HOSTNAME_TEST'] = '52.198.239.61'
envs['USERNAME_TEST'] = 'centos'

parsedRepos.each {
	String gitRepoName = it.split('/').last() - '.git'
	String fullGitRepo = it
	String branchName = "master"
	int customNameIndex = it.indexOf('$')
	int customBranchIndex = it.indexOf('#')
	if (customNameIndex == -1 && customBranchIndex == -1) {
		// url
		fullGitRepo = it
		branchName = "master"
	} else if (customNameIndex > -1 && (customNameIndex < customBranchIndex || customBranchIndex == -1)) {
		fullGitRepo = it.substring(0, customNameIndex)
		if (customNameIndex < customBranchIndex) {
			// url$newName#someBranch
			gitRepoName = it.substring(customNameIndex + 1, customBranchIndex)
			branchName = it.substring(customBranchIndex + 1)
		} else if (customBranchIndex == -1) {
			// url$newName
			gitRepoName = it.substring(customNameIndex + 1)
		}
	} else if (customBranchIndex > -1) {
		fullGitRepo = it.substring(0, customBranchIndex)
		if (customBranchIndex < customNameIndex) {
			// url#someBranch$newName
			gitRepoName = it.substring(customNameIndex + 1)
			branchName = it.substring(customBranchIndex + 1, customNameIndex)
		} else if (customNameIndex == -1) {
			// url#someBranch
			gitRepoName = it.substring(it.lastIndexOf("/") + 1, customBranchIndex)
			branchName = it.substring(customBranchIndex + 1)
		}
	}
	String projectName = "${gitRepoName}-declarative-pipeline"
	
	envs['GIT_REPOSITORY'] = fullGitRepo
	envs['GIT_BRANCH_NAME'] = branchName

	dsl.pipelineJob(projectName) {
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
}
