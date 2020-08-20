package com.mtto.sat.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
//@IdClass(PermissionId.class)
@Table(name = "m_permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id; 
	
	@Column(name = "grouping", nullable = false, length = 50)	
	private String grouping; 
	
	@Column(name = "code", nullable = false, length = 100)	
	private String code; 
	
	@Column(name = "entity_name", nullable = false, length = 100)	
	private String entityName; 
		
	@Column(name = "action_name", nullable = false, length = 100)	
	private String action_name; 

	@Column(name = "can_maker_checker", nullable = false)	
	private boolean canMakerChecker;

	public Permission() {
		this.id = id;
		this.grouping = grouping;
		this.code = code;
		this.entityName = entityName;
		this.action_name = action_name;
		this.canMakerChecker = canMakerChecker;
	}
	
	
	public Permission(String grouping, String code, String entityName, String action_name, boolean canMakerChecker) {
		super();
		this.grouping = grouping;
		this.code = code;
		this.entityName = entityName;
		this.action_name = action_name;
		this.canMakerChecker = canMakerChecker;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public boolean isCanMakerChecker() {
		return canMakerChecker;
	}

	public void setCanMakerChecker(boolean canMakerChecker) {
		this.canMakerChecker = canMakerChecker;
	} 

}
