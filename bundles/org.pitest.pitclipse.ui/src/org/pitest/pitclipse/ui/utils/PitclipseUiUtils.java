/*******************************************************************************
 * Copyright 2021 Lorenzo Bettini and contributors
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
package org.pitest.pitclipse.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.pitest.pitclipse.core.PitCoreActivator;
import org.pitest.pitclipse.core.preferences.PitPreferences;

/**
 * A few utilities for the UI.
 * 
 * @author Lorenzo Bettini
 *
 */
public class PitclipseUiUtils {

    private PitclipseUiUtils() {
        // Only static utility methods
    }

    /**
     * Only performs the operation if the {@link Composite} is not null and not
     * disposed.
     * 
     * @param c
     */
    public static void setFocusSafely(Composite c) {
        if (c != null && !c.isDisposed()) {
            c.setFocus();
        }
    }

    /**
     * Only performs the operation if the {@link Composite} is not null and not
     * disposed.
     * 
     * @param c
     */
    public static void disposeSafely(Composite c) {
        if (c != null && !c.isDisposed()) {
            c.dispose();
        }
    }

    /**
     * Executes the {@link Runnable} and in case of {@link SWTError} shows a dialog
     * with the specified title and error message, using the specified parent.
     * 
     * @param runnable
     * @param parent
     * @param title
     * @param errorMessage
     */
    public static void executeSafely(Runnable runnable, Composite parent,
            String title, String errorMessage) {
        try {
            runnable.run();
        } catch (SWTError e) {
            if (e.getMessage() != null) {
                errorMessage += "\n" + e.getMessage();
            }
            MessageBox messageBox = new MessageBox(parent.getShell(),
                    SWT.ICON_ERROR | SWT.OK);
            messageBox.setText(title);
            messageBox.setMessage(errorMessage);
            messageBox.open();
        }
    }

    /**
     * Tries to create the target class for the given test class, with the pattern
     * specified from the preferences.
     * @param testClass name of the test class
     * @return the test class or an empty string, if no test class was found or the
     *         pattern option is deactivated.
     */
    public static String getTargetClass(String testClass) {
        String targetClass = "";
        // try to get target class from test class, if enabled
        final IPreferenceStore preferenceStore = PitCoreActivator.getDefault().getPreferenceStore();
        if (preferenceStore.getBoolean(PitPreferences.CLASS_PATTERN_ENABLED)) {
            final Pattern classPattern = Pattern.compile(preferenceStore.getString(PitPreferences.CLASS_PATTERN));
            final Matcher matcher = classPattern.matcher(testClass);
            if (matcher.find() && matcher.groupCount() == 2) {
                targetClass = matcher.group(1) + '.' + matcher.group(2);
            }
        }
        return targetClass;
    }

    /**
     * @param className which should be tested
     * @return true, if the given class name is a file and has a valid name in the
     *         workspace, otherwise false
     */
    public static boolean isClassOnClasspath(String className) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IStatus status = workspace.validateName(className, IResource.FILE);
        if (status.isOK()) {
            final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
            for (IProject project : projects) {
                IJavaProject javaProject = JavaCore.create(project);
                try {
                    if (javaProject.findType(className) != null) {
                        return true;
                    }
                } catch (JavaModelException ignoreMe) {
                    // ignored
                }
            }
        }
        return false;
    }
}
