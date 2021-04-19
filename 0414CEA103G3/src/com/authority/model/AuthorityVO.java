package com.authority.model;

public class AuthorityVO implements java.io.Serializable{
	
	private Integer empno;
	private Integer functionno;
	private String status;
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public Integer getFunctionno() {
		return functionno;
	}
	public void setFunctionno(Integer functionno) {
		this.functionno = functionno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
