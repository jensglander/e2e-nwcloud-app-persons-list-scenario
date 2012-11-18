package com.sap.netweaver.cloud.odata.service;

import java.util.Set;

import org.odata4j.producer.resources.AbstractODataApplication;


public class JpaApplication extends AbstractODataApplication {

  public JpaApplication() {
  }

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = super.getClasses();
    classes.add(JpaProducerProvider.class);
    return classes;
  }

}