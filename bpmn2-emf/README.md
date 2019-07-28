jBPM BPMN2 EMF Model
====================

Custom EMF model classes for the jBPM BPMN2 domain.

7.x stream
-----------
* org.eclipse:org.eclipse.bpmn2:0.8.2-jboss:jar
* org.eclipse.emf:org.eclipse.emf.common:2.6.0.v20100614-1136
* org.eclipse.emf:org.eclipse.emf.ecore.xmi:2.5.0.v20100521-1846
* org.eclipse.emf:org.eclipse.emf.ecore:2.6.0.v20100614-1136

kogito (so this new module)
---------------------------
* org.eclipse.emf:org.eclipse.emf.ecore:jar:2.6.0.v20100614-1136
* Generated in Eclipse from model/BPMN20.genmodel by using:
** Compliance level: 8.0
** Runtime platform: GWT
** Runtime version: 2.6


TODO
----
* Qs
    * What about "old" tiho/bpmn2 plugins stuff? Dropping support for eclipse editors?
    * What about stunner 7.x vs kogito marshallers? Use only client stuff or keep different 2 impls?

* Next
    * Initialize resources via factory (BpsimFactoryImpl, DroolsFactoryImpl, Bpmn2FactoryImpl, etc)
    * Add support for drools & bpsim namespaces too (jbpm-bpmn2-emfextmodel)
    * Reduce generated models (GWT) to min state as much as possible
    * xmi
        * Reduce amount of code used for xmi stuff
        * Refactor code impls  

* Instead of copying generated sources, use some mvn plugin to generate classes from ecore etc
* Check // TODO (kogito) - Roger

HowTo
-----
1.- mvn clean
2.- remove generated packages
    - org.eclipse.bpmn2
    - org.eclipse.dd
    - org.jboss.drools
3.- Generate models
    - BPMN20.genmodel
    - bpmn2emfextmodel.genmodel
    - bpsim.genmodel
    - bpmn2color.genmodel ??
4.- Remove generated gwt.xml module files
5.- Fixes?
    - org.eclipse.bpmn2.impl.Bpmn2PackageImpl
        // TODO: NEW
        theBpmn2Package.getEClassifiers().addAll(DroolsPackage.eINSTANCE.getEClassifiers());
        theBpmn2Package.getEAnnotations().addAll(DroolsPackage.eINSTANCE.getEAnnotations());
        theBpmn2Package.fixEClassifiers();
    