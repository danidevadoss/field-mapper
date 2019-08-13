# `@FieldMapper`
###### _A simple java solution for field mapping_
#### An Annotation that can be used to copy properties from one object to another object with intermediate conversions.


The problem field mapper solves is eliminating multiple lines of code for converting one object type to another object type with intermediate conversion.

Use cases can be like, When therse is need to convert entity to dto, Where some field type should be converted to another type as client requires.

Let's say we have an entity `User` and dto `UserDto` like below,

`User.java`:
```java
public class User{

  private Integer userId;

  private String userName;

  private Date createdDate;

  private String active;

  //Getter and Setters

}
```

`UserDto.java`:
```java
public class UserDto{

  private Integer userId;

  private String userName;

  private String createdDate;

  private boolean isActive;

  //Getter and Setters

}
```

Now we need is to convert `User` object to `UserDto` object.

### What we typically do?

1) For same data type we will used  **_getter and setters_** to get value for `User` and set it to `UserDto` object.
2) When we need to set `createdDate` in `UserDto`, we will use conversion method to convert date object from User object to MM-DD-YY formated string. method to convert will be in `DateUtilty.class` for reusing purpose.
3) For `isActive` field in `UserDto` we need to convert _Y/N_ to _true/false_. we can create conversion method in any class, let's say `CommonUtility` and use that to convert to _true/false_ and set the value to `UserDto`.

let's look at conversion code below,

```java
private UserDto userToUserDto(User user){

  UserDto userDto= new UserDto();
  userDto.setUserId(user.getUserId());
  userDto.setUserName(user.getUserName());
  userDto.setCreatedDate( DateUtilty.dateToMmddyy(user.getCreatedDate()));
  userDto.setIsActive(CommonUtility.booleanToYN(user.getActive()));
  
  return userDto;
}
```

**Yeah..! That's easy..!** Here we have only four fields and code looks okay. but _what if we have many fields and most of them needs to be converted while copying to different object type?_

Code will be long line of **_setters and getters-** and conversion methods

### Let's use `@FieldMapper`:
1)Add `@FieldMapper` annotation in required fields.

```java
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
```

```java
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
```
Let's modify our `userToUserDto` method:

```java
private UserDto userToUserDto(User user){
return FieldMapperUtility.copy(user,UserDto.class);
}
```
## What are the constraints? How to configure?
### **_Coming Soon...!_**

---
_Check test package for above example._

### More logics will implemented. Samples and docs will be updated...! 

#### _Programmers who are interested to collaborate are MOST WELCOME_

## License
[GNU](https://www.gnu.org/licenses/gpl-3.0.en.html)
