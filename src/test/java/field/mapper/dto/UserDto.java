package field.mapper.dto;

import field.mapper.annotation.FieldMapper;
import field.mapper.conversion.CommonUtility;
import field.mapper.conversion.DateUtilty;

public class UserDto {

	@FieldMapper
	private int userId;

	@FieldMapper
	private String userName;

	@FieldMapper(method="dateToMmddyy", clazz=DateUtilty.class)
	private String createdDate;

	@FieldMapper(name="active", method="ynToBoolean", clazz=CommonUtility.class)
	private boolean isActive;

	public UserDto() {
		//Default constructor
	}
	
	public UserDto(Integer userId, String userName, String createdDate, boolean isActive) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.createdDate = createdDate;
		this.isActive = isActive;
	}

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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	//Getter and Setters
	
	
}
