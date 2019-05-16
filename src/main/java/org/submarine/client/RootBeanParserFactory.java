package org.submarine.client;

import com.peterfranza.gwt.jaxb.client.parser.JAXBBindings;
import com.peterfranza.gwt.jaxb.client.parser.JAXBParserFactory;
import org.submarine.client.bpmn.TDefinitions;

@JAXBBindings(value= TDefinitions.class)
public interface RootBeanParserFactory extends JAXBParserFactory<TDefinitions> {}