package org.eclipse.jbpm;

import java.io.IOException;
import java.util.List;

import com.google.gwt.core.client.GWT;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;

import static java.util.stream.Collectors.toList;

public class Bpmn2Marshalling {

    public static DocumentRoot unmarshall(final String raw) {
        Bpmn2Resource bpmn2Resource = new Bpmn2Resource();
        try {
            bpmn2Resource.load(raw);
        } catch (IOException e) {
            logError(e);
        }
        DocumentRoot docRoot = (DocumentRoot) bpmn2Resource.getContents().get(0);
        return docRoot;
    }

    public static String marshall(final DocumentRoot document) {
        Bpmn2Resource bpmn2Resource = new Bpmn2Resource();
        bpmn2Resource.getContents().add(document);
        String raw = null;
        try {
            raw = bpmn2Resource.toBPMN2();
        } catch (IOException e) {
            logError(e);
        }
        return raw;
    }

    public static Process getProcess(DocumentRoot docRoot) {
        return (Process) docRoot.getDefinitions().getRootElements().stream()
                .filter(p -> p instanceof Process)
                .findAny()
                .get();
    }

    public static String testString(DocumentRoot docRoot) {
        Process process = getProcess(docRoot);
        List<Class<?>> flowElementClasses = process.getFlowElements().stream().map(FlowElement::getClass).collect(toList());
        String result = (String.valueOf(flowElementClasses));
        return result;
    }

    private static void logError(Exception e) {
        String s = "Error: [" + e.getClass().getSimpleName() + "] " + e.getMessage();
        GWT.log(s);
    }
}
