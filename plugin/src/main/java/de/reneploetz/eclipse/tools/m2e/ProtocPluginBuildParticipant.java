package de.reneploetz.eclipse.tools.m2e;

import java.io.File;
import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.codehaus.plexus.util.Scanner;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;
import org.sonatype.plexus.build.incremental.BuildContext;

public class ProtocPluginBuildParticipant extends MojoExecutionBuildParticipant {

	public ProtocPluginBuildParticipant(final MojoExecution aExecution) {
		super(aExecution, true);
	}

	@Override
	public Set<IProject> build(final int aKind, final IProgressMonitor aMonitor) throws Exception {
		final IMaven maven = MavenPlugin.getMaven();
		final BuildContext buildContext = getBuildContext();

		final File source = maven.getMojoParameterValue(getSession(), getMojoExecution(), "protoSourceRoot", File.class);
		final Scanner ds = buildContext.newScanner(source);
		ds.scan();

		final String[] includedFiles = ds.getIncludedFiles();
		if (includedFiles == null || includedFiles.length <= 0) {
			return null;
		}

		final Set<IProject> result = super.build(aKind, aMonitor);
		File generated = maven.getMojoParameterValue(getSession(), getMojoExecution(), "outputDirectory", File.class);
		if (generated != null) {
			buildContext.refresh(generated);
		}

		return result;
	}
}

