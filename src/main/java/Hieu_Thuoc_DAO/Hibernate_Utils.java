package Hieu_Thuoc_DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate_Utils {
	private static SessionFactory sf;
	private Hibernate_Utils() {
		
	};
	public static SessionFactory getSessionFactory() {
		if(sf==null) {
			sf=new Configuration().configure().buildSessionFactory();
		}
		return sf;
	}
	
}
