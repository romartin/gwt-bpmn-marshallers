package org.submarine.client;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.submarine.client.eclipse.bpmn2.impl.Bpmn2PackageImpl;

public class Main {

    public static void main(String[] args) {

        Bpmn2PackageImpl.init();
        String ns = "http://www.omg.org/spec/BPMN/20100524/MODEL";
        EPackage.Registry registry = EPackage.Registry.INSTANCE;
        EFactory eFactory = registry.getEFactory(ns);
        EPackage ePackage = eFactory.getEPackage();
        EClass eClass = (EClass) ePackage.getEClassifier("UserTask");
        EStructuralFeature name = eClass.getEStructuralFeature("name");
        EObject eObject = eFactory.create(eClass);
        eObject.eSet(name, "MYTASK");
        System.out.println(eObject);
    }
}
