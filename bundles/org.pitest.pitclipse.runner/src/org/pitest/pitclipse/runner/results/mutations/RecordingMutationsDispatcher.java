/*******************************************************************************
 * Copyright 2012-2019 Phil Glover and contributors
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *  
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package org.pitest.pitclipse.runner.results.mutations;

import java.util.Optional;

import org.pitest.pitclipse.runner.results.Mutations;

/**
 * <p>A singleton providing access to the latest mutations computed by PIT.</p>
 */
public enum RecordingMutationsDispatcher implements MutationsDispatcher {
    INSTANCE;

    private volatile Optional<Mutations> dispatchedMutations = Optional.empty();

    @Override
    public void dispatch(Mutations result) {
        dispatchedMutations = Optional.of(result);
    }

    public Mutations getDispatchedMutations() {
        return dispatchedMutations.orElseGet(Mutations::new);
    }
}
