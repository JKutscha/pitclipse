package org.pitest.pitclipse.runner.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.pitest.pitclipse.runner.config.PitExecutionMode.PROJECT_ISOLATION;
import static org.pitest.pitclipse.runner.config.PitExecutionMode.WORKSPACE;

@RunWith(MockitoJUnitRunner.class)
public class PitExecutionModeTest {

    @Mock
    private PitExecutionModeVisitor<Void> visitor;

    @Test
    public void projectVisitorIsInvoked() {
        whenTheProjectExecutionModeIsVisited();
        thenTheProjectExecutionVisitorMethodIsInvoked();
    }

    @Test
    public void workspaceVisitorIsInvoked() {
        whenTheWorkspaceExecutionModeIsVisited();
        thenTheWorkspaceExecutionVisitorMethodIsInvoked();
    }

    private void whenTheProjectExecutionModeIsVisited() {
        PROJECT_ISOLATION.accept(visitor);
    }

    private void whenTheWorkspaceExecutionModeIsVisited() {
        WORKSPACE.accept(visitor);
    }

    private void thenTheProjectExecutionVisitorMethodIsInvoked() {
        verify(visitor, only()).visitProjectLevelConfiguration();
    }

    private void thenTheWorkspaceExecutionVisitorMethodIsInvoked() {
        verify(visitor, only()).visitWorkspaceLevelConfiguration();
    }
}
