package br.com.softplan.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	// mesmo nome que esta em persistence.xml
	public static final String PERSISTENCE_UNIT_NAME = "sample";

	private static EntityManager entityManager;

	private EntityManagerUtil() {
	}

	public static EntityManager getEntityManager() {
		
		if (entityManager == null) {
			System.out.println("start Factory...");
			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("sample");
			System.out.println("sucessoo, factory rodandoo!!! "+emFactory);
			return emFactory.createEntityManager();
		}
		
		return entityManager;
	}
}
