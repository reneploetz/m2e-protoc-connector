package de.reneploetz.eclipse.tools.m2e;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;

public class ProtocPluginProjectConfigurator extends AbstractJavaProjectConfigurator {

	@Override
	public AbstractBuildParticipant getBuildParticipant(final IMavenProjectFacade aProjectFacade, 
			final MojoExecution aExecution, final IPluginExecutionMetadata aExecutionMetadata) {
		return new ProtocPluginBuildParticipant(aExecution);
	}
}

