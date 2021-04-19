package com.movie.model;

import java.util.List;

public class MovieService {

	private MovieDAO_interface dao;

	public MovieService() {
		dao = new MovieDAO();
	}

	public MovieVO addMovie(String moviename, byte[] moviepicture, String director, String actor,
			String category, Integer length, String status, java.sql.Date premiredate, java.sql.Date offdate,
			String trailor, String grade, Double rating, Double expectation) {

		MovieVO movieVO = new MovieVO();

		movieVO.setMoviename(moviename);
		movieVO.setMoviepicture(moviepicture);
		movieVO.setDirector(director);
		movieVO.setActor(actor);
		movieVO.setCategory(category);
		movieVO.setLength(length);
		movieVO.setStatus(status);
		movieVO.setPremiredate(premiredate);
		movieVO.setOffdate(offdate);
		movieVO.setTrailor(trailor);
		movieVO.setGrade(grade);
		movieVO.setRating(rating);
		movieVO.setExpectation(expectation);
		
		dao.insert(movieVO);

		return movieVO;
	}

	public MovieVO updateMovie(Integer movieno, String moviename, byte[] moviepicture, String director, 
			String actor, String category, Integer length, String status, java.sql.Date premiredate, 
			java.sql.Date offdate, String trailor, String grade, Double rating, Double expectation) {

		MovieVO movieVO = new MovieVO();

		movieVO.setMovieno(movieno);
		movieVO.setMoviename(moviename);
		movieVO.setMoviepicture(moviepicture);
		movieVO.setDirector(director);
		movieVO.setActor(actor);
		movieVO.setCategory(category);
		movieVO.setLength(length);
		movieVO.setStatus(status);
		movieVO.setPremiredate(premiredate);
		movieVO.setOffdate(offdate);
		movieVO.setTrailor(trailor);
		movieVO.setGrade(grade);
		movieVO.setRating(rating);
		movieVO.setExpectation(expectation);
		
		dao.update(movieVO);

		return movieVO;
	}

	public void deleteMovie(Integer movieno) {
		dao.delete(movieno);
	}

	public MovieVO getOneMovie(Integer movieno) {
		return dao.findByPrimaryKey(movieno);
	}

	public List<MovieVO> getAll() {
		return dao.getAll();
	}
}
