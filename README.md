# field-mapper
Annotation that can be used to copy properties from one object to another method with intermediate conversions

The problem field mapper solves is eliminating multiple lines of code for converting one type object to another type object with intermediate conversion

this will mostly occur when we need to convert entity to dto, where few field type should be converted to return to client. 

eg:Let's say we have an entity User and UserDto like below,

public class User{

private Integer userId;

private String userName;

private Date createdDate;

private String active;

//Getter and Setters

}


public class UserDto{

private Integer userId;

private String userName;

private String createdDate;

private boolean isActive;

//Getter and Setters

}

Now the need is to convert User object to UserDto object

what we typically do?

1) For same data type we will used getter and setters to get value for User and set it to UserDto object
2) When we need to set 'createdDate' in UserDto, we will use conversion method to convert Date object from User object to MM-DD-YY formated string. method to convert will be in DateUtilty.class for reusing
3) For "isActive" field in UserDto we need to convert "Y/N" to "true/false". we create conversion method in any class let's saye CommonUtility and use that to convert and set the value to UserDto

let's look at how code looks below,

private UserDto userToUserDto(User user){

UserDto userDto= new UserDto();
userDto.setUserId(user.getUserId());
userDto.setUserName(user.getUserName());
userDto.setCreatedDate( DateUtilty.dateToMmddyy(user.getCreatedDate()));
userDto.setIsActive(CommonUtility.booleanToYN(user.getActive()));

}

That's easy..! here we have only four fields and code looks okay. but what if we have many fields and most of them needs to be converted while copying to different Type object?
Code will be long line of setters and getters and conversion methods

@FieldMapper configuration:
1)Add @FieldMapper annotation in required fields

public class User{

@FieldMapper
private Integer userId;

@FieldMapper
private String userName;

@FieldMapper(method="mmddyyToDate", clazz=DateUtilty.class)
private Date createdDate;

@FieldMapper(name="active" method="ynToBoolean", clazz=CommonUtility.class)
private String active;

//Getter and Setters

}


public class UserDto{

@FieldMapper
private Integer userId;

@FieldMapper
private String userName;

@FieldMapper(method="dateToMmddyy", clazz=DateUtilty.class)
private String createdDate;

@FieldMapper(name="active" method="ynToBoolean", clazz=CommonUtility.class)
private boolean isActive;

//Getter and Setters

}

let's modify our userToUserDto method:

private UserDto userToUserDto(User user){

return FieldMapperUtility.copy(user,UserDto.class);
}









