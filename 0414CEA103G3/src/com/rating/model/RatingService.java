package com.rating.model;

import java.util.List;

public class RatingService {

	private RatingDAO_interface dao;

	public RatingService() {
		dao = new RatingDAO();
	}

	public RatingVO addRating(Integer memberno, Integer movieno, Double rating) {

		RatingVO ratingVO = new RatingVO();

		ratingVO.setMemberno(memberno);
		ratingVO.setMovieno(movieno);
		ratingVO.setRating(rating);
				
		dao.insert(ratingVO);

		return ratingVO;
	}

	public RatingVO updateRating(Double rating, Integer memberno, Integer movieno) {

		RatingVO ratingVO = new RatingVO();

		ratingVO.setRating(rating);
		ratingVO.setMemberno(memberno);
		ratingVO.setMovieno(movieno);
		
		dao.update(ratingVO);

		return ratingVO;
	}

	public void deleteRating(Integer memberno,Integer movieno) {
		dao.delete(memberno,movieno);
	}

	public RatingVO getOneRatingByMovieNo(Integer movieno) {
		return dao.findByMovieNo(movieno);
	}

	public List<RatingVO> getAll() {
		return dao.getAllByMovieNo();
	}
	

}
