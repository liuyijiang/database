package org.hb.com.entiy;

import java.io.Serializable;

public class Student implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -4357134104099640357L;
	private Integer id;
	private String name;
	private String keyNumber;
	private Integer age;
	private Integer cid;
	private Integer sid;
	
	private ClassVO cvo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyNumber() {
		return keyNumber;
	}
	public void setKeyNumber(String keyNumber) {
		this.keyNumber = keyNumber;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public ClassVO getCvo() {
		return cvo;
	}
	public void setCvo(ClassVO cvo) {
		this.cvo = cvo;
	}
	
	
}
