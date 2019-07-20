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
* Addd ecore etc into repo
* Instead of copying generated sources, use some mvn plugin to generate classes from ecore etc
* Check // TODO (kogito) - Roger

* Ed's Qs
** how did you generated the bpmn-eclipse-models?
** Compare org.eclipse.bpmn2.impl.Bpmn2PackageImpl from me and Ed's one
** also yours it's not using Class#getResource() (instead because of Reflection.register works?)
** Also this is based on the GWT plugin mode - not really long term support
