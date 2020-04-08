# `@FieldMapper`
###### _A simple java solution for field mapping_
#### An Annotation that can be used to copy properties from one object to another object with intermediate conversions.


The problem field mapper solves is eliminating multiple lines of code for converting one object type to another object type with intermediate conversion.

Use cases can be like, When there is need to convert entity to dto, Where some field type should be converted to another type.

Let's say we have an entity `User` and a dto `UserDto` like below,

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

Now, the need is to copy properties from `User` object to `UserDto` object.

### What we typically do?

1) For same data type we will use  **_getter and setters_** to get value form `User` and set it to `UserDto` object.
2) When we need to set `createdDate` in `UserDto`, we will use conversion method to convert date object from `User` object to MM-DD-YY formated string. method to convert will be in `DateUtilty.class` for reusing purpose.
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

**Yeah..! That's easy..!** Here we have only four fields and code looks okay. but _what if we have many fields and some fields needs to be converted while copying to different object type?_

Code will be long line of **_setters and getters_** and conversion methods

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

  @FieldMapper(name="active" method="booleanToYn", clazz=CommonUtility.class)
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
## How to configure?

Just annotate the fields that needs to be copied.
we have three usefull attributes in `@FieldMapper` 
* `clazz`- class form which the method should be accessed
* `method`- method name which is to be invoked from class mentioned in clazz
* `name` -  name is to map different fields having different field name. if field from which value is copied and field from which value is to be copied have same name then this attribute can be skipped. In above example we have used name attribute to map `active` field in `User.class` and `isActive` field in `UserDto.class`

## What are the constraints?? 

1) Method mentioned in `method` attribute of `@FieldMapper` should be static method
2) If Java Security Manager restricts permission to access field using java reflection API. This annotation cannot be used. since  `FieldMapperUtility.class`  uses Reflection API to access private members to get and set values

---
_Check test package for above example._
## So Thats it ???
### No..! More logics will implemented. Samples and docs will be updated...! 

## License
[GNU](https://www.gnu.org/licenses/gpl-3.0.en.html)
