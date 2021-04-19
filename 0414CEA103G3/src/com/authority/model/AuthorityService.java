package com.authority.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_interface dao;

	public AuthorityService() {
		dao = new AuthorityDAO();
	}

	public AuthorityVO addAuthority(Integer empno, Integer functionno, String status) {

		AuthorityVO authorityVO = new AuthorityVO();

		authorityVO.setEmpno(empno);
		authorityVO.setFunctionno(functionno);
		authorityVO.setStatus(status);
				
		dao.insert(authorityVO);

		return authorityVO;
	}

	public AuthorityVO updateAuthority(Integer empno, Integer functionno, String status) {

		AuthorityVO authorityVO = new AuthorityVO();

		authorityVO.setEmpno(empno);
		authorityVO.setFunctionno(functionno);
		authorityVO.setStatus(status);
		
		dao.update(authorityVO);

		return authorityVO;
	}

	public void deleteAuthority(Integer empno,Integer functionno) {
		dao.delete(empno,functionno);
	}

	public AuthorityVO getOneAuthorityByEmpNo(Integer empno) {
		return dao.findByEmpNo(empno);
	}

	public List<AuthorityVO> getAll() {
		return dao.getAllByEmpNo();
	}

}
