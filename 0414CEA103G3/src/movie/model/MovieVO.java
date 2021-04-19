package movie.model;
import java.sql.Date;

public class MovieVO implements java.io.Serializable {

	private Integer movieno;
	private String moviename;
	private byte[] moviepicture;
	private String director;
	private String actor;
	private String category;
	private Integer length;
	private String status;
	private Date premiredate;
	private Date offdate;
	private String trailor;
	private String grade;
	private Double rating;
	private Double expectation;

	public Integer getMovieno() {
		return movieno;
	}
	public void setMovieno(Integer movieno) {
		this.movieno = movieno;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public byte[] getMoviepicture() {
		return moviepicture;
	}
	public void setMoviepicture(byte[] moviepicture) {
		this.moviepicture = moviepicture;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPremiredate() {
		return premiredate;
	}
	public void setPremiredate(Date premiredate) {
		this.premiredate = premiredate;
	}
	public Date getOffdate() {
		return offdate;
	}
	public void setOffdate(Date offdate) {
		this.offdate = offdate;
	}
	public String getTrailor() {
		return trailor;
	}
	public void setTrailor(String trailor) {
		this.trailor = trailor;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Double getExpectation() {
		return expectation;
	}
	public void setExpectation(Double expectation) {
		this.expectation = expectation;
	}
}
