package org.submarine.client;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import org.submarine.client.model.TActivity;

interface Bpmn2Factory extends AutoBeanFactory {
  AutoBean<TActivity> activity();
}