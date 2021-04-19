package com.authority.model;

import java.util.List;

public interface AuthorityDAO_interface {
          public void insert(AuthorityVO authorityVO);
          public void update(AuthorityVO authorityVO);
          public void delete(Integer empno ,Integer functionno);
          public AuthorityVO findByEmpNo(Integer empno);
          public List<AuthorityVO> getAllByEmpNo();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<AuthorityVO> getAll(Map<String, String[]> map); 
}