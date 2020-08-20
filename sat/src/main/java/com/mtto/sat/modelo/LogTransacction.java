package com.mtto.sat.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_transacction")
public class LogTransacction {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 

	@Column(name = "action_name", nullable = false, length = 50)	
	private String actionName; 

	@Column(name = "entity_name", nullable = false, length = 50)	
	private String entityName; 

	@Column(name = "office_id")	
	private Integer officeId; 

	@Column(name = "api_get_url", nullable = false, length = 100)	
	private String apiGetUrl; 

	@Column(name = "resource_id")	
	private Integer resourceId; 

	@Column(name = "subresource_id")	
	private Integer subresourceId; 

	@Column(name = "command_as_json", nullable = false, length = 500)	
	private String commandAsJson; 

	@Column(name = "maker_id")	
	private Integer makerId; 

	@Column(name = "made_on_date")	
	private Date madeOnDate; 

	@Column(name = "checker_id")	
	private Integer checkerId; 

	@Column(name = "checked_on_date")	
	private Date checkedOnDate; 

	@Column(name = "processing_result_enum")	
	private int processingResultEnum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getApiGetUrl() {
		return apiGetUrl;
	}

	public void setApiGetUrl(String apiGetUrl) {
		this.apiGetUrl = apiGetUrl;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getSubresourceId() {
		return subresourceId;
	}

	public void setSubresourceId(Integer subresourceId) {
		this.subresourceId = subresourceId;
	}

	public String getCommandAsJson() {
		return commandAsJson;
	}

	public void setCommandAsJson(String commandAsJson) {
		this.commandAsJson = commandAsJson;
	}

	public Integer getMakerId() {
		return makerId;
	}

	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}

	public Date getMadeOnDate() {
		return madeOnDate;
	}

	public void setMadeOnDate(Date madeOnDate) {
		this.madeOnDate = madeOnDate;
	}

	public Integer getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(Integer checkerId) {
		this.checkerId = checkerId;
	}

	public Date getCheckedOnDate() {
		return checkedOnDate;
	}

	public void setCheckedOnDate(Date checkedOnDate) {
		this.checkedOnDate = checkedOnDate;
	}

	public int getProcessingResultEnum() {
		return processingResultEnum;
	}

	public void setProcessingResultEnum(int processingResultEnum) {
		this.processingResultEnum = processingResultEnum;
	}



	
}
