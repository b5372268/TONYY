package movie.model;

import java.util.*;
import comment.model.*;

public interface MovieDAO_interface {
	
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(Integer movieno);
	public MovieVO findByPrimaryKey(Integer movieno);
	public List<MovieVO> getAll();
	//�d�߬Y���������u(�@��h)(�^�� Set)
    public Set<CommentVO> getCommentsByMovieno(Integer movieno);
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<MovieVO> getAll(Map<String, String[]> map); 
}
