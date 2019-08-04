package org.eclipse.jbpm;

import java.io.IOException;
import java.util.List;

import com.google.gwt.core.client.GWT;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;

import static java.util.stream.Collectors.toList;

public class Bpmn2Marshalling {

    public static DocumentRoot unmarshall(final String raw) {
        Bpmn2Resource bpmn2Resource = new Bpmn2Resource();
        try {
            bpmn2Resource.load(raw);
        } catch (IOException e) {
            logError(e);
        }
        return getDocumentRoot(bpmn2Resource);
    }

    public static String marshall(final DocumentRoot document) {
        return marshall(document.getDefinitions());
    }

    public static String marshall(final Definitions definitions) {
        Bpmn2Resource bpmn2Resource = new Bpmn2Resource();
        bpmn2Resource.getContents().add(definitions);
        String raw = null;
        try {
            raw = bpmn2Resource.toBPMN2();
        } catch (IOException e) {
            logError(e);
        }
        return raw;
    }

    private static DocumentRoot getDocumentRoot(final XMLResource resource) {
        DocumentRoot docRoot = (DocumentRoot) resource.getContents().get(0);
        Process process = getProcess(docRoot);
        GWT.log("Process id = " + process.getId());
        GWT.log("Process name = " + process.getName());
        FeatureMap anyAttribute = process.getAnyAttribute();
        GWT.log("Attributes size = " + anyAttribute.size());
        anyAttribute.forEach(e -> GWT.log("[" + e.getEStructuralFeature().getName() + "=" + e.getValue() + "]"));
        return docRoot;
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
