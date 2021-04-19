package com.function.model;

import java.util.List;

public class FunctionService {
	
	private FunctionDAO_interface dao;

	public FunctionService() {
		dao = new FunctionDAO();
	}

	public void addFunction(String functiondesc) {

		dao.insert(functiondesc);

	}

	public FunctionVO updateFunction(Integer functionno, String functiondesc, String status) {

		FunctionVO functionVO = new FunctionVO();

		functionVO.setFunctionno(functionno);
		functionVO.setFunctiondesc(functiondesc);
		functionVO.setStatus(status);
		
		dao.update(functionVO);

		return functionVO;
	}

	public void deleteFunction(Integer functionno) {
		dao.delete(functionno);
	}

	public FunctionVO getOneFunction(Integer functionno) {
		return dao.findByPrimaryKey(functionno);
	}

	public List<FunctionVO> getAll() {
		return dao.getAll();
	}

}
