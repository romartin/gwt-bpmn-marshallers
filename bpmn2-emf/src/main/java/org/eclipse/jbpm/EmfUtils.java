package org.eclipse.jbpm;

import com.google.gwt.core.client.GWT;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EmfUtils {

    private static int indent;

    public static void start(EPackage ePackage) {
        iClear();
        log(ePackage);
        iClear();
    }

    private static void log(EPackage ePackage) {
        log("[EPackage]");
        logAttribute("name", ePackage.getName());
        logAttribute("NsURI", ePackage.getNsURI());
        logAttribute("NsPrefix", ePackage.getNsPrefix());

        EList<EClassifier> eClassifiers = ePackage.getEClassifiers();
        logClassifiers(eClassifiers);

        EList<EPackage> eSubpackages = ePackage.getESubpackages();
        logPackages(eSubpackages);
    }

    private static void logPackages(EList<EPackage> ePackages) {
        log("[EPackage's]");
        iNext();
        for (EPackage ePackage : ePackages) {
            log(ePackage);
        }
        iPrev();
    }

    private static void logClassifiers(EList<EClassifier> eClassifiers) {
        log("[EClassifier's]");
        iNext();
        for (EClassifier eClassifier : eClassifiers) {
            log(eClassifier);
        }
        iPrev();
    }

    private static void logStructuralFeatures(EList<EStructuralFeature> eStructuralFeatures) {
        log("[EStructuralFeature's]");
        iNext();
        for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
            log(eStructuralFeature);
        }
        iPrev();
    }

    private static void log(EClassifier eClassifier) {
        log("[EClassifier]");
        boolean isEClass = eClassifier instanceof EClass;
        logAttribute("isEClass", isEClass + "");
        logAttribute("instanceClass", getClassName(eClassifier.getInstanceClass()));
        logAttribute("classifierId", Integer.toString(eClassifier.getClassifierID()));
        logAttribute("name", eClassifier.getName());
        logAttribute("instanceClassName", eClassifier.getInstanceClassName());
        logAttribute("instanceTypeName", eClassifier.getInstanceTypeName());
        // eClassifier.getETypeParameters();
        if (isEClass) {
            log((EClass) eClassifier);
        }
    }

    private static void log(EClass eClass) {
        EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
        logStructuralFeatures(eStructuralFeatures);
        EList<EAttribute> eAttributes = eClass.getEAttributes();
        logAttributes(eAttributes);
    }

    private static void logAttributes(EList<EAttribute> eAttributes) {
        log("[EAttribute's]");
        iNext();
        for (EAttribute eAttribute : eAttributes) {
            log(eAttribute);
        }
        iPrev();
    }

    private static void log(EAttribute eAttribute) {
        log("[EAttribute]");
        logAttribute("name", eAttribute.getName());
        logAttribute("featureId", Integer.toString(eAttribute.getFeatureID()));
    }

    private static void log(EStructuralFeature eStructuralFeature) {
        log("[EStructuralFeature]");
        logAttribute("name", eStructuralFeature.getName());
        logAttribute("containerClass", getClassName(eStructuralFeature.getContainerClass()));
        logAttribute("eContainerClass", getEClassName(eStructuralFeature.getEContainingClass()));
    }

    private static String getEClassName(EClass c) {
        return c != null ? c.getName() : "null";
    }

    private static String getClassName(Class<?> c) {
        return c != null ? c.getName() : "null";
    }

    private static void logAttribute(String key, String value) {
        log("- " + key + " = [" + value + "]");
    }

    private static void log(String message) {
        String s = "";
        for (int i = 0; i < indent; i++) {
            s += " ";
        }
        s += message;
        GWT.log(s);
    }

    private static void iPrev() {
        indent-=2;
    }

    private static void iNext() {
        indent+=2;
    }

    private static void iClear() {
        indent = 0;
    }

}
