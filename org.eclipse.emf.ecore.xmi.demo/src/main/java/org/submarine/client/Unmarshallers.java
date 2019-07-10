package org.submarine.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import static java.util.stream.Collectors.toList;

public class Unmarshallers {

    public static BiConsumer<String, Consumer<DocumentRoot>> UNMARSHALLER = (raw, callback) -> {
        Bpmn2Resource bpmn2Resource = new Bpmn2Resource();
        try {
            bpmn2Resource.load(raw);
        } catch (IOException e) {
            logError(e);
        }
        DocumentRoot docRoot = (DocumentRoot) bpmn2Resource.getContents().get(0);
        callback.accept(docRoot);
    };

    public static BiConsumer<DocumentRoot, Consumer<String>> MARSHALLER = (root, callback) -> {
        Bpmn2Resource bpmn2Resource = new Bpmn2Resource();
        bpmn2Resource.getContents().add(root);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            bpmn2Resource.save(outputStream, new HashMap<>());
        } catch (IOException e) {
            logError(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                logError(e);
            }
        }
        String raw = null;
        try {
            SafeHtmlUtils.htmlEscape(outputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logError(e);
        }

        callback.accept(raw);
    };

    private static void logError(Exception e) {
        e.printStackTrace();
        Window.alert("Error: " + e.getMessage());
    }

    public static String toString(DocumentRoot docRoot) {
        String result = (String.valueOf(docRoot.getDefinitions().getRootElements().stream()
                                               .filter(p -> p instanceof Process)
                                               .map(p -> (Process) p)
                                               .flatMap(p -> p.getFlowElements().stream().map(FlowElement::getClass))
                                               .collect(toList())));
        return result;
    }

    // See http://emfjson.org/projects/gwt/latest/
    public static BiConsumer<String, Consumer<String>> ROGER_UNMARSHALLER = (raw, callback) -> {
        GWT.log("RUNNING!!!!");
        ResourceSet resourceSet = new ResourceSetImpl();

        resourceSet.getPackageRegistry()
                .put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

        resourceSet.getPackageRegistry()
                .put("http://www.omg.org/spec/BPMN/20100524/MODEL", Bpmn2Package.eINSTANCE);

        /*resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new JsonResourceFactory());
                 // .put("*", new XmlResourceFactory());

        resourceSet.getURIConverter().getURIHandlers().add(new LocalStorageHandler());

        URI uri1 = URI.createURI("emfjs://dummyUriWithValidSuffix.xml");
        URI uri2 = LocalStorageHandler.getURI("emfjs:dummyUriWithValidSuffix.xml");

        //resourceSet.getURIConverter().createInputStream();
        //DatastoreUtil.getEntity();

        Storage store = Storage.getLocalStorageIfSupported();
        store.setItem("emfjs:file:/dummyUriWithValidSuffix.xml", raw);

        Resource resource = resourceSet.createResource(uri2);*/

        Resource resource = null;

        try {
            resource.load(null, new Callback<Resource>() {
                @Override
                public void onFailure(Throwable caught) {
                    GWT.log("ERROR1! = " + caught.getMessage());
                }

                @Override
                public void onSuccess(Resource result) {
                    GWT.log("SUCCESS!");
                }
            });
        } catch (IOException e) {
            GWT.log("ERROR2! = " + e.getMessage());
        } finally {
            GWT.log("COMPLETE 1!!!!");
        }
    };

}
