package ward.landa;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

	private int ID;
	private int imgId;
	private String name;
	private String email;
	private String phone;
	private String position;
	private String faculty;
	private List<Course> courses;

	public Teacher(int ID, int imgId, String name, String email, String phone,
			String pos, String faculty) {
		setID(ID);
		setImgId(imgId);
		setName(name);
		setEmail(email);
		setPhone(phone);
		setPosition(pos);
		setFaculty(faculty);
		courses = new ArrayList<Course>();
	}

	public void addCourse(Course c) {
		if (courses != null) {
			for (Course course : courses) {
				if (course.equals(c)) {
					return;
				}
			}
			courses.add(c);
		}
	}

	public void removeCourse(Course c) {
		if (courses.contains(c)) {
			courses.remove(c);
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
}
