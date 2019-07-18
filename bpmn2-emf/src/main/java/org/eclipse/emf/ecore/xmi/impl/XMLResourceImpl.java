package org.eclipse.emf.ecore.xmi.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.google.gwt.xml.client.Node;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;

public class XMLResourceImpl implements XMLResource {

    protected URI uri;

    public XMLResourceImpl(URI uri) {
        this.uri = uri;
    }

    @Override
    public boolean useZip() {
        return false;
    }

    @Override
    public void setUseZip(boolean useZip) {

    }

    @Override
    public Map<Object, Object> getDefaultSaveOptions() {
        return null;
    }

    @Override
    public Map<Object, Object> getDefaultLoadOptions() {
        return null;
    }

    @Override
    public String getPublicId() {
        return null;
    }

    @Override
    public String getSystemId() {
        return null;
    }

    @Override
    public void setDoctypeInfo(String publicId, String systemId) {

    }

    @Override
    public String getEncoding() {
        return null;
    }

    @Override
    public void setEncoding(String encoding) {

    }

    @Override
    public String getXMLVersion() {
        return null;
    }

    @Override
    public void setXMLVersion(String version) {

    }

    @Override
    public Map<String, EObject> getIDToEObjectMap() {
        return null;
    }

    @Override
    public Map<EObject, String> getEObjectToIDMap() {
        return null;
    }

    @Override
    public String getID(EObject eObject) {
        return null;
    }

    @Override
    public void setID(EObject eObject, String id) {

    }

    @Override
    public Map<EObject, AnyType> getEObjectToExtensionMap() {
        return null;
    }

    @Override
    public void load(Node node, Map<?, ?> options) throws IOException {

    }

    @Override
    public ResourceSet getResourceSet() {
        return null;
    }

    @Override
    public URI getURI() {
        return null;
    }

    @Override
    public void setURI(URI uri) {

    }

    @Override
    public long getTimeStamp() {
        return 0;
    }

    @Override
    public void setTimeStamp(long timeStamp) {

    }

    @Override
    public EList<EObject> getContents() {
        return null;
    }

    @Override
    public TreeIterator<EObject> getAllContents() {
        return null;
    }

    @Override
    public String getURIFragment(EObject eObject) {
        return null;
    }

    @Override
    public EObject getEObject(String uriFragment) {
        return null;
    }

    @Override
    public void save(Map<?, ?> options) throws IOException {

    }

    @Override
    public void save(Map<?, ?> options, Callback<Resource> callback) throws IOException {

    }

    @Override
    public void load(Map<?, ?> options) throws IOException {

    }

    @Override
    public void load(Map<?, ?> options, Callback<Resource> callback) throws IOException {

    }

    @Override
    public void save(OutputStream outputStream, Map<?, ?> options) throws IOException {

    }

    @Override
    public void load(InputStream inputStream, Map<?, ?> options) throws IOException {

    }

    @Override
    public boolean isTrackingModification() {
        return false;
    }

    @Override
    public void setTrackingModification(boolean isTrackingModification) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean isModified) {

    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void unload() {

    }

    @Override
    public void delete(Map<?, ?> options) throws IOException {

    }

    @Override
    public void delete(Map<?, ?> options, Callback<Resource> callback) throws IOException {

    }

    @Override
    public EList<Diagnostic> getErrors() {
        return null;
    }

    @Override
    public EList<Diagnostic> getWarnings() {
        return null;
    }

    @Override
    public EList<Adapter> eAdapters() {
        return null;
    }

    @Override
    public boolean eDeliver() {
        return false;
    }

    @Override
    public void eSetDeliver(boolean deliver) {

    }

    @Override
    public void eNotify(Notification notification) {

    }
}
