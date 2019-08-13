package field.mapper.dto;

import java.util.Date;

import field.mapper.annotation.FieldMapper;
import field.mapper.conversion.CommonUtility;
import field.mapper.conversion.DateUtilty;

public class User {

	@FieldMapper
	private int userId;

	@FieldMapper
	private String userName;

	@FieldMapper(method = "mmddyyToDate", clazz = DateUtilty.class)
	private Date createdDate;

	@FieldMapper(name = "active", method = "booleanToYn", clazz = CommonUtility.class)
	private String active;

	public User() {
		//Default constructor
	}
	
	
	public User(Integer userId, String userName, Date createdDate, String active) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.createdDate = createdDate;
		this.active = active;
	}

	//Getter and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}	

}
