package cn.haut.elec.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import cn.haut.elec.domain.ElecBug;

public class ForInputString {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from ElecBug");
		List<ElecBug> list = query.list();
		for (ElecBug elecBug : list) {
			System.out.println("bugID=" + elecBug.getBugID() + "jctID="
					+ elecBug.getElecStation().getJctID());
		}
		transaction.commit();
		session.close();
	}
}
