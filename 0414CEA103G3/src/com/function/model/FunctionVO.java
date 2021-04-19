package com.function.model;

public class FunctionVO implements java.io.Serializable{
	
	private Integer functionno;
	private String functiondesc;
	private String status;
	
	public Integer getFunctionno() {
		return functionno;
	}
	public void setFunctionno(Integer functionno) {
		this.functionno = functionno;
	}
	public String getFunctiondesc() {
		return functiondesc;
	}
	public void setFunctiondesc(String functiondesc) {
		this.functiondesc = functiondesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
