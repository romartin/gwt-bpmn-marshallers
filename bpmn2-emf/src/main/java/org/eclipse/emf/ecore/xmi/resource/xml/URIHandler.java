package org.eclipse.emf.ecore.xmi.resource.xml;

import org.eclipse.emf.common.util.URI;

public class URIHandler {

    public static class PlatformSchemeAware extends URIHandler
    {
        @Override
        public URI deresolve(URI uri)
        {
            return !uri.isPlatform() || (uri.segmentCount() > 0 && baseURI.segmentCount() > 0 && uri.segment(0).equals(baseURI.segment(0))) ?
                    super.deresolve(uri) : uri;
        }
    }

    protected URI baseURI;
    protected boolean resolve;

    public void setBaseURI(URI uri)
    {
        baseURI = uri;
        resolve = uri != null && uri.isHierarchical() && !uri.isRelative();
    }

    public URI resolve(URI uri)
    {
        return resolve && uri.isRelative() && uri.hasRelativePath() ? uri.resolve(baseURI) : uri;
    }

    public URI deresolve(URI uri)
    {
        if (resolve && !uri.isRelative())
        {
            URI deresolvedURI = uri.deresolve(baseURI, true, true, false);
            if (deresolvedURI.hasRelativePath())
            {
                uri = deresolvedURI;
            }
        }
        return uri;
    }
}
