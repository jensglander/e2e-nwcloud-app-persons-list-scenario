package com.sap.netweaver.cloud.odata.service;

import java.util.Properties;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.odata4j.producer.ODataProducer;

@Provider
public class JpaProducerProvider implements ContextResolver<ODataProducer> {
  public JpaProducerProvider() {
  }

  public ODataProducer getContext(Class<?> type) {
    return new JpaProducerFactory().create(new Properties());
  }

}
