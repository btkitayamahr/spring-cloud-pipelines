import javaposse.jobdsl.dsl.DslFactory

/**
 *  This script contains logic that
 *
 *  - for each project from the REPOS env variable generates a Jenkinsfile deployment pipeline
 */


DslFactory dsl = this

String jenkinsfileDir = binding.variables["JENKINSFILE_DIR"] ?: "${WORKSPACE}/jenkins/declarative-pipeline"

dsl.pipelineJob('msa-pipline-discovery') {
	definition {
		cps {
			script("""${dsl.readFileFromWorkspace(jenkinsfileDir + '/Jenkinsfile-discovery')}""")
		}
	}
}

