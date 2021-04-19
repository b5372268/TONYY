package comment.model;

import java.util.List;

public class CommentService {
	
	private CommentDAO_interface dao;

	public CommentService() {
		dao = new CommentDAO();
	}

	public CommentVO addComment(Integer memberno, Integer movieno, String content) {

		CommentVO commentVO = new CommentVO();

		commentVO.setMemberno(memberno);
		commentVO.setMovieno(movieno);
		commentVO.setContent(content);
		
		dao.insert(commentVO);

		return commentVO;
	}

	public CommentVO updateComment(Integer commentno, String content, String status) {

		CommentVO commentVO = new CommentVO();
		
		commentVO.setContent(content);
		commentVO.setStatus(status);
		commentVO.setCommentno(commentno);
	
		dao.update(commentVO);

		return commentVO;
	}

	public void deleteComment(Integer commentno) {
		dao.delete(commentno);
	}

	public CommentVO getOneComment(Integer commentno) {
		return dao.findByPrimaryKey(commentno);
	}

	public List<CommentVO> getAll() {
		return dao.getAll();
	}

}
