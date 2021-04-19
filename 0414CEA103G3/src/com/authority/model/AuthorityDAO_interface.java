package com.authority.model;

import java.util.List;

public interface AuthorityDAO_interface {
          public void insert(AuthorityVO authorityVO);
          public void update(AuthorityVO authorityVO);
          public void delete(Integer empno ,Integer functionno);
          public AuthorityVO findByEmpNo(Integer empno);
          public List<AuthorityVO> getAllByEmpNo();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<AuthorityVO> getAll(Map<String, String[]> map); 
}