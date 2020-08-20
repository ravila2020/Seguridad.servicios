package com.mtto.sat.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "m_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	@Column(name = "name", nullable = false, length = 100)	
	private String name; 
	@Column(name = "description", nullable = false, length = 500)	
	private String description; 
	@Column(name = "is_disabled", nullable = false)	
	private boolean isDisable;

	public Role() {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isDisable = isDisable;
	}

	
	public Role(String name, String description, boolean isDisable) {
		super();
		this.name = name;
		this.description = description;
		this.isDisable = isDisable;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDisable() {
		return isDisable;
	}
	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}
	
	
}
