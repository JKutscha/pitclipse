package org.pitest.pitclipse.runner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.pitest.pitclipse.runner.results.DetectionStatus;
import org.pitest.pitclipse.runner.results.Mutations;

/**
 * Wrapper class which holds an PitResult with a given error Message
 *
 */
public final class PitErrorResult {
    private PitErrorResult() {
        // no constructor
    }

    public static PitResults getPitErrorResultWithErrorMessage(Exception exception) {
        return PitResults.builder().withMutations(new Mutations() {
            private static final long serialVersionUID = -1373934687349019669L;
            @Override
            public List<Mutation> getMutation() {
                ArrayList<Mutations.Mutation> mutations = new ArrayList<>();
                Mutations.Mutation m = new Mutations.Mutation();
                m.setSourceFile("NO VALID FILE");
                m.setMutatedClass("NO VALID CLASS");
                m.setMutatedMethod("NO METHOD MUTATD");
                m.setLineNumber(BigInteger.valueOf(0));
                m.setMutator("NO VALID MUTATOR");
                m.setIndex(BigInteger.valueOf(0));
                m.setKillingTest("NO TEST");
                m.setDescription(exception.getMessage());
                m.setDetected(false);
                m.setStatus(DetectionStatus.SURVIVED);
                mutations.add(m);
                return mutations;
            }
        }).build();
    }
}
