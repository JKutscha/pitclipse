package org.pitest.pitclipse.core.extension.point;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.pitest.pitclipse.pitrunner.PitOptions;

import org.pitest.pitclipse.reloc.guava.collect.ImmutableList;

@Immutable
public class PitRuntimeOptions {

	private final int portNumber;
	private final PitOptions options;
	private final ImmutableList<String> projects;

	public PitRuntimeOptions(int portNumber, PitOptions options, List<String> projects) {
		this.portNumber = portNumber;
		this.options = options;
		this.projects = ImmutableList.copyOf(projects);
	}

	public int getPortNumber() {
		return portNumber;
	}

	public PitOptions getOptions() {
		return options;
	}

	public List<String> getMutatedProjects() {
		return projects;
	}
}
