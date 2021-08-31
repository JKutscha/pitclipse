/*******************************************************************************
 * Copyright 2012-2021 Phil Glover and contributors
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

package org.pitest.pitclipse.core.preferences;

/**
 * Constants used to deal with Pitclipse's preferences.
 */
public final class PitPreferences {
    public static final String MUTATORS_LABEL = "Mutators";
    public static final String MUTATOR_GROUP = "pitMutators";

    public static final String INDIVIDUAL_MUTATORS = "pitIndividualMutators";

    public static final String INCREMENTAL_ANALYSIS_LABEL = "Use &incremental analysis";
    public static final String INCREMENTAL_ANALYSIS = "incrementalAnalysis";

    public static final String EXCLUDED_CLASSES_LABEL = "E&xcluded classes (e.g.*IntTest)";
    public static final String EXCLUDED_CLASSES = "excludedClasses";

    public static final String RUN_IN_PARALLEL_LABEL = "Mutation tests run in para&llel";
    public static final String RUN_IN_PARALLEL = "runInParallel";

    public static final String EXCLUDED_METHODS_LABEL = "Excluded &methods (e.g.*toString*)";
    public static final String EXCLUDED_METHODS = "excludedMethods";

    public static final String AVOID_CALLS_TO_LABEL = "&Avoid calls to";
    public static final String AVOID_CALLS_TO = "avoidCallsTo";

    public static final String TIMEOUT_LABEL = "Pit Ti&meout";
    public static final String TIMEOUT = "pitTimeout";

    public static final String TIMEOUT_FACTOR_LABEL = "Timeout &Factor";
    public static final String TIMEOUT_FACTOR = "pitTimeoutFactor";

    public static final String EXECUTION_MODE_LABEL = "Pit execution scope";
    public static final String EXECUTION_MODE = "pitExecutionMode";

    public static final String PREFERENCE_DESCRIPTION_LABEL = "Pitclipse Preferences";
    public static final String MUTATORS_DESCRIPTION_LABEL = "Mutator Preferences";

    public static final String CLASS_PATTERN_DESCRIPTION = "With the Class Pattern the programm tries to extract the target class from the name of the test class, if the mutation test object is a test class.\n"
            + "Should the test class name not match a class in the class path, the default behaviour is used.";
    public static final String CLASS_PATTERN_ENABLED_LABEL = "Enable Class Pattern";
    public static final String CLASS_PATTERN_ENABLED = "pitClassPatternEnabled";
    public static final String CLASS_PATTERN_LABEL = "Class &Pattern";
    public static final String CLASS_PATTERN = "pitClassPattern";

    public static final String TEST_CLASS_LABEL = "Pattern for com.package.TestClass.java to com.package.Class.java";
    public static final String CLASS_TEST_LABEL = "Pattern for com.package.ClassTest.java to com.package.Class.java";
    /**
     * Helper pattern for {@link #CLASS_PATTERN}
     */
    private static final String HELPER_PATTERN = "\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*";
    private static final String TEST_STRING = "(?:Test)";
    /**
     * Pattern which matches a test class name like ClassTest
     */
    public static final String CLASS_TEST_PATTERN = "([" + HELPER_PATTERN + ".?]*)\\." + "(" + HELPER_PATTERN + ")" + TEST_STRING;
    /**
     * Pattern which matches a test class name like TestClass
     */
    public static final String TEST_CLASS_PATTERN = "([" + HELPER_PATTERN + ".?]*)\\." + TEST_STRING + "(" + HELPER_PATTERN + ")";
    /**
     * Pattern which is used as a default
     */
    public static final String DEFAULT_CLASS_PATTERN = TEST_CLASS_PATTERN;

    private PitPreferences() {
        // utility class should not be instantiated
    }

}
