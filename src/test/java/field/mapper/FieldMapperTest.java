package field.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import field.mapper.conversion.DateUtilty;
import field.mapper.dto.User;
import field.mapper.dto.UserDto;
import field.mapper.util.FieldMapperUtility;

/**
 * Test cases for Field mapper
 */
public class FieldMapperTest {

	@Test
	public void test_copyFromOneObjectToAnotherObject_withValue() {
		Date date = new Date();
		String expectedDate=DateUtilty.dateToMmddyy(date);
		User user = new User(1,"John Doe",date,"Y");
		//Conversion
		UserDto userDto=FieldMapperUtility.copy(user, UserDto.class);
		//Assertion
		assertEquals(1, userDto.getUserId());
		assertEquals("John Doe", userDto.getUserName());
		assertEquals(expectedDate, userDto.getCreatedDate());
		assertEquals(true, userDto.isActive());
	}
	
	@Test
	public void test_copyFromOneObjectToAnotherObject_withoutValues() {
		User user = new User();
		//Conversion
		UserDto userDto=FieldMapperUtility.copy(user, UserDto.class);
		//Assertion
		assertEquals(0, userDto.getUserId());
		assertEquals(null, userDto.getUserName());
		assertEquals(null, userDto.getCreatedDate());
		assertEquals(false, userDto.isActive());
	}

}
