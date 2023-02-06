package com.example.form;

/**
 * 従業員の情報更新のフォーム.
 * 
 * @author tadunakitamura
 *
 */
public class UpdateEmployeeForm {

	/** 従業員ID */
	private String id;

	/** 扶養人数 */
	private String dependentsCount;

	public String getId() {
		return id;
	}
	
	public Integer getIntId() {
		return Integer.parseInt(id);
	}

	public Integer getIntDependentsCount() {
		return Integer.parseInt(dependentsCount);
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

}
