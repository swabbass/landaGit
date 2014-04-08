package ward.landa;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String name;
	private String dateTime;
	private String Teacher;
	private int imgID;
	private int courseID;
	private float rating;
	private List<Teacher> teachers;
	public Course(int courseID,String name ,String dateString,String teacher,int imgId,float rate) {
		setCourseID(courseID);
		setName(name);
		setDateTime(dateString);
		setTeacher(teacher);
		setImgID(imgId);
		setRating(rate);
		teachers=new ArrayList<Teacher>();
	
	}
	public void addTeacher(Teacher t)
	{
		if(t!=null)
		{
			for(Teacher tmp :teachers)
			{
				if(tmp.equals(t))
				{
					return ;
				}
			}
			teachers.add(t);
		}
	}
	public void removeTeacher(Teacher t)
	{

		if(teachers.contains(t))
		{
			teachers.remove(t);
		}
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Course)
		{
			Course tmp=(Course)o;
			return tmp.getCourseID()==this.getCourseID();
		}
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getTeacher() {
		return Teacher;
	}
	public void setTeacher(String teacher) {
		Teacher = teacher;
	}
	public int getImgID() {
		return imgID;
	}
	public void setImgID(int imgID) {
		this.imgID = imgID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
	
}
