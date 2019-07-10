package org.submarine.client.xmi.resource.xml;

import java.util.Map;

import org.eclipse.emf.common.util.URI;

public class XMLOptions {

    // protected EcoreBuilder ecoreBuilder;

    protected Map<String, URI> externalSchemaLocation;

    protected boolean anyXML;

    protected boolean processSchemaLocations;

    /*public EcoreBuilder getEcoreBuilder()
    {
        return ecoreBuilder;
    }*/

    public Map<String, URI> getExternalSchemaLocations()
    {
        return externalSchemaLocation;
    }

    public boolean isProcessAnyXML()
    {
        return anyXML;
    }

    public boolean isProcessSchemaLocations()
    {
        return processSchemaLocations;
    }

    /*public void setEcoreBuilder(EcoreBuilder ecoreBuilder)
    {
        this.ecoreBuilder = ecoreBuilder;
    }*/

    public void setExternalSchemaLocations(Map<String, URI> schemaLocations)
    {
        this.externalSchemaLocation = schemaLocations;
    }

    public void setProcessAnyXML(boolean anyXML)
    {
        this.anyXML = anyXML;
    }

    public void setProcessSchemaLocations(boolean processSchemaLocations)
    {
        this.processSchemaLocations = processSchemaLocations;
    }

    @Override
    public int hashCode()
    {
        int hashCode = externalSchemaLocation != null ? externalSchemaLocation.hashCode() : 0;
        /*hashCode ^= (ecoreBuilder != null) ? ecoreBuilder.hashCode() : 0;*/
        return hashCode + (anyXML ? 1 : 0) + (processSchemaLocations ? 2 : 0);
    }

}
