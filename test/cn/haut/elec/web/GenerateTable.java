package cn.haut.elec.web;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GenerateTable {
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		System.out.println(sessionFactory.openSession());
	}
} 
