package org.pitest.pitclipse.runner.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.TextConsole;

public final class PitErrorUtil {
    private static final Pattern COVERAGE_PATTERN = Pattern
            .compile("FINE : Gathering coverage for test Description \\[testClass=(.+), name=(.+)\\]");
    private static final String PIT_CONSOLE_STRING = "PIT Mutation Test";

    private PitErrorUtil() {
        // only static methods
    }

    public static void showErrorMessage(String errorMessage) {
        Display.getDefault().syncExec(
                () -> MessageDialog.openError(Display.getDefault().getActiveShell(), "Pitclipse Error",
                        errorMessage + getLastPitOutput()));
    }

    private static TextConsole findPitConsole() {
        ConsolePlugin plugin = ConsolePlugin.getDefault();
        IConsoleManager conMan = plugin.getConsoleManager();
        IConsole[] consoles = conMan.getConsoles();
        for (IConsole console : consoles) {
            if (console.getName().contains(PIT_CONSOLE_STRING)) {
                return (TextConsole) console;
            }
        }
        // no console found
        return null;
    }

    public static String getLastPitOutput() {
        String output = "";
        TextConsole myConsole = findPitConsole();
        if (myConsole == null) {
            return output;
        }
        String console = myConsole.getDocument().get();
        String[] lines = console.split("\\n");
        if (lines != null && lines.length > 2) {
            // get second to last line which holds the last information pit did
            output = lines[lines.length - 1];
        }
        Matcher matcher = COVERAGE_PATTERN.matcher(output);
        if (matcher.find() && matcher.groupCount() == 2) {
            return "\tFailing test in class:\t" + matcher.group(1) + "\n\tand method:\t" + matcher.group(2)
            + "\nYou need green tests for executing mutation tests.";
        }
        return output;
    }
}
