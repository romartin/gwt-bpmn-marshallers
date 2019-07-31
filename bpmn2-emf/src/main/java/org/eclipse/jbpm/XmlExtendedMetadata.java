package org.eclipse.jbpm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

public class XmlExtendedMetadata extends BasicExtendedMetaData {
    private static Map<String, String> xmiToXmlNamespaceMap;

    private static void initXmiToXmlNamespaceMap() {
        xmiToXmlNamespaceMap = new HashMap(6);
        String[] namespaces = new String[] {
                "http://www.omg.org/spec/BPMN/20100524/MODEL-XMI",
                "http://www.omg.org/spec/BPMN/20100524/DI-XMI",
                "http://www.omg.org/spec/DD/20100524/DI-XMI",
                "http://www.omg.org/spec/DD/20100524/DC-XMI"
        };
        String[] var4 = namespaces;
        int var3 = namespaces.length;

        for(int var2 = 0; var2 < var3; ++var2) {
            String curNs = var4[var2];
            xmiToXmlNamespaceMap.put(curNs, xmiToXsdNamespaceUri(curNs));
        }

    }

    /*
        TODO: Override all parent calls to split("\\w"") -> not splitting properly on client side
     */
    @Override
    protected List<String> basicGetWildcards(EStructuralFeature eStructuralFeature) {
        EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
        if (eAnnotation != null)
        {
            String wildcards = eAnnotation.getDetails().get("wildcards");
            if (wildcards != null)
            {
                List<String> result = new ArrayList<String>();
                for (String wildcard : wildcards.split(","))
                {
                    if (wildcard.equals("##other"))
                    {
                        result.add("!##" + getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
                    }
                    else if (wildcard.equals("##local"))
                    {
                        result.add(null);
                    }
                    else if (wildcard.equals("##targetNamespace"))
                    {
                        result.add(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
                    }
                    else
                    {
                        result.add(wildcard);
                    }
                }
                return result;
            }
        }

        return Collections.emptyList();
    }

    public XmlExtendedMetadata() {
    }

    public String getNamespace(EPackage ePackage) {
        if (xmiToXmlNamespaceMap == null) {
            initXmiToXmlNamespaceMap();
        }

        String ns = super.getNamespace(ePackage);
        String xmlNs;
        return (xmlNs = (String)xmiToXmlNamespaceMap.get(ns)) != null ? xmlNs : ns;
    }

    public EClassifier getType(EPackage ePackage, String name) {
        if (Bpmn2Package.eINSTANCE.equals(ePackage)) {
            if ("tBaseElementWithMixedContent".equals(name)) {
                return Bpmn2Package.Literals.BASE_ELEMENT;
            }

            if ("tImplementation".equals(name)) {
                return org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING;
            }

            if ("tScript".equals(name)) {
                return org.eclipse.emf.ecore.EcorePackage.Literals.EOBJECT;
            }

            if ("tText".equals(name)) {
                return org.eclipse.emf.ecore.EcorePackage.Literals.EOBJECT;
            }

            if ("tTransactionMethod".equals(name)) {
                return org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING;
            }
        }

        return super.getType(ePackage, name);
    }

    public static String xmiToXsdNamespaceUri(String xmiNsUri) {
        if (!xmiNsUri.endsWith("-XMI")) {
            throw new IllegalArgumentException("XMI namespace expected");
        } else {
            return xmiNsUri.substring(0, xmiNsUri.length() - 4);
        }
    }

}
