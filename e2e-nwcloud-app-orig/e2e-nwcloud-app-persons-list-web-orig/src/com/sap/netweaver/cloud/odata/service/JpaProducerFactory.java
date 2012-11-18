package com.sap.netweaver.cloud.odata.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.ODataProducerFactory;
import org.odata4j.producer.jpa.JPAProducer;

public class JpaProducerFactory implements ODataProducerFactory {

    private static final String PERSISTENCE_UNIT_NAME = "e2e-nwcloud-app-jpa-model-orig";

  @Override
  public ODataProducer create(Properties arg0) {
      DataSource ds;

      try {
          InitialContext ctx = new InitialContext();
          ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
      } catch (NamingException e) {
          throw new RuntimeException(e);
      }

      Map<String, DataSource> emfProperties = new HashMap<String, DataSource>();
      emfProperties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,
              emfProperties);
      JPAProducer producer = new JPAProducer(entityManagerFactory, PERSISTENCE_UNIT_NAME, 50);
      return producer;
  }

}
