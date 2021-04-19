package com.movie.model;

import java.util.List;

public interface MovieDAO_interface {
	
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(Integer movieno);
	public MovieVO findByPrimaryKey(Integer movieno);
	public List<MovieVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<MovieVO> getAll(Map<String, String[]> map); 
}
