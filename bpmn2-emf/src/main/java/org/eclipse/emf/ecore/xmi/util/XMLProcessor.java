package org.eclipse.emf.ecore.xmi.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

public class XMLProcessor {

    protected final static String XML_EXTENSION = "xml";

    protected final static String STAR_EXTENSION = "*";

    protected Map<String, Resource.Factory> registrations;

    public XMLProcessor(EPackage.Registry registry) {

    }

    protected Map<String, Resource.Factory> getRegistrations()
    {
        if (registrations == null)
        {
            Map<String, Resource.Factory> result = new HashMap<String, Resource.Factory>();
            // TODO result.put(STAR_EXTENSION, new XMLResourceFactoryImpl());
            registrations = result;
        }

        return registrations;
    }
}
