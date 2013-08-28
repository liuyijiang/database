package org.hb.com.entiy;

import java.util.Set;

/**
 * °à¼¶Àà
 * @author jim.liu
 *
 */
public class ClassVO {
   
	private int id;
	private String name;
	private Set<Student> students;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
	
}
