package org.hb.com.entiy;

import java.util.Date;

import org.hb.com.base.ModelType;
/**
 * ≤‚ ‘√∂æŸ±£¥Ê≤‚ ‘
 * @author jim.liu
 *
 */
public class Model {
   
	private Integer id;
	private ModelType type;
	private String name;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ModelType getType() {
		return type;
	}
	public void setType(ModelType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
