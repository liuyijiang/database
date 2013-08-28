package org.hb.com.select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hb.com.entiy.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HBSelectWithHQL {

	private Configuration configuration = null;
	private SessionFactory  sf = null;
	private  Session session  = null;
	private List<Student> list = new ArrayList<Student>();
	
	public HBSelectWithHQL(){
		configuration = new Configuration().configure("hibernate.cfg.xml");   
		sf = configuration.buildSessionFactory();
		session = sf.openSession();
	}
	
	/**
	 * list��ѯ��ʹ�û���
	 */
	public void findByList(){
		Query q = session.createQuery("from Student t ");
		List<Student> list = q.list();
		for(Student s : list){
			System.out.print(s.getName() + " - ");
		}
	}
	
	/**
	 * iterateʵ��ʹ����һ������   ���Ȳ��Ұ����к�ȥ��һ������ƥ���Ƿ��У���ֱ��ȡ�������û�� �ͷ���SQL��䡣  ������N+1��SQL�����N+1����
	 */
	public void findByIterate(){
		Query q = session.createQuery("from Student t ");
		Iterator<Student> i = q.iterate();
		if(i.hasNext()){
			System.out.print(i.next().getName() + " - ");
		}
	}
	
	
	public static void main(String[] args) {
		HBSelectWithHQL h = new  HBSelectWithHQL();
		h.findByIterate();

	}

}
