package comment.model;

import java.util.*;

public interface CommentDAO_interface {
	
    public void insert(CommentVO commentVO);
    public void update(CommentVO commentVO);
    public void delete(Integer commentno);
    public CommentVO findByPrimaryKey(Integer commentno);
//    public CommentVO findByMemberNo(Integer memberno);
//    public CommentVO findByMovieNo(Integer movieno);
    public List<CommentVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<CommentVo> getAll(Map<String, String[]> map); 

}
