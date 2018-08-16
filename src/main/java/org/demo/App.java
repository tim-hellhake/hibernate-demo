package org.demo;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {

  private static final String PERSISTENCE_UNIT_NAME = "sample";

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();

    Receiver receiver = new Receiver();
    receiver.setNumber("1");
    save(em, receiver);

    TextMessage textMessage = new TextMessage();
    textMessage.setText("Hallo!");
    textMessage.setReceivers(Arrays.asList(receiver));
    save(em, textMessage);

    SoundMessage soundMessage = new SoundMessage();
    soundMessage.setSound("Imperial March");
    soundMessage.setReceivers(Arrays.asList(receiver));
    save(em, soundMessage);

    list(em, "SoundMessage");
    list(em, "TextMessage");

    em.close();
  }

  private static void save(EntityManager em, Object entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
  }

  private static void list(EntityManager em, String name) {
    System.out.println(String.format("Listing all entities of type %s", name));
    Query query = em.createQuery(String.format("select x from %s x", name));
    List entityList = query.getResultList();

    for (Object entity : entityList) {
      System.out.println(entity);
    }
  }
}
