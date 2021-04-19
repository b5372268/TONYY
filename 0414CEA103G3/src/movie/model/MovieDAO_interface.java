package movie.model;

import java.util.*;
import comment.model.*;

public interface MovieDAO_interface {
	
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(Integer movieno);
	public MovieVO findByPrimaryKey(Integer movieno);
	public List<MovieVO> getAll();
	//琩高琘场(癸)(肚 Set)
    public Set<CommentVO> getCommentsByMovieno(Integer movieno);
    //窾ノ狡琩高(肚把计篈Map)(肚 List)
//  public List<MovieVO> getAll(Map<String, String[]> map); 
}
