//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.submarine.client.eclipse.bpmn2.util;

public class NamespaceHelper {
    public NamespaceHelper() {
    }

    public static String xmiToXsdNamespaceUri(String xmiNsUri) {
        if (!xmiNsUri.endsWith("-XMI")) {
            throw new IllegalArgumentException("XMI namespace expected");
        } else {
            return xmiNsUri.substring(0, xmiNsUri.length() - 4);
        }
    }
}
