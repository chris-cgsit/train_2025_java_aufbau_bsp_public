package students;

import java.io.Serializable;
import java.time.LocalDate;


public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name, city, comment;
	private int areaCode;
	private boolean xml, html, fxml;
	private LocalDate birthDate;
	private Gender gender;
	private String language;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isXml() {
		return xml;
	}

	public void setXml(boolean xml) {
		this.xml = xml;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public boolean isFxml() {
		return fxml;
	}

	public void setFxml(boolean fxml) {
		this.fxml = fxml;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=").append(getId());
		builder.append(", name=").append(getName());
		builder.append(", areaCode=").append(getAreaCode());
		builder.append(", city=").append(getCity());
		builder.append("\ngender=").append(getGender());
		builder.append(", birthDate=").append(getBirthDate());
		builder.append("\nxml=").append(isXml());
		builder.append(", html=").append(isHtml());
		builder.append(", fxml=").append(isFxml());
		builder.append(", languageId=").append(getLanguage());
		builder.append("\ncomment=").append(getComment());
		builder.append("]");
		return builder.toString();
	}
	
//	@Override
//	public String toString() {
//		return String.format("%s (%s %s)", getName(), getAreaCode(), getCity());
//	}

}
