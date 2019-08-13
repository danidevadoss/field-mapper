package field.mapper;

import org.junit.Assert;
import org.junit.Test;

import field.mapper.dto.FromClass;
import field.mapper.dto.InvalidConversionMethod;
import field.mapper.dto.ToClass;
import field.mapper.util.FieldMapperUtility;

/**
 * Test cases for Field mapper
 */
public class FieldMapperTest {

	@Test
	public void test_copyFromOneObjectToAnotherObject_withValue() {
		FromClass fromClass = new FromClass("10");
		ToClass toClass = new ToClass();
		FieldMapperUtility.copy(fromClass, toClass);
		Assert.assertEquals(10, toClass.getNumber().intValue());
	}

	@Test
	public void test_copyFromOneObjectToAnotherObject_withoutValue() {
		FromClass fromClass = new FromClass();
		ToClass toClass = new ToClass();
		FieldMapperUtility.copy(fromClass, toClass);
		Assert.assertEquals(null, toClass.getNumber());
	}

	@Test
	public void test_copyFromOneObjectToAnotherClass_withValues() {
		FromClass fromClass = new FromClass("10");
		ToClass toClass = FieldMapperUtility.copy(fromClass, ToClass.class);
		Assert.assertEquals(10, toClass.getNumber().intValue());
	}

	@Test
	public void test_copyFromOneObjectToAnotherClass_withoutValues() {
		FromClass fromClass = new FromClass();
		ToClass toClass = FieldMapperUtility.copy(fromClass, ToClass.class);
		Assert.assertEquals(null, toClass.getNumber());
	}

	/**
	 * 
	 */
	@Test(expected = RuntimeException.class)
	public void test_methodNotAvailable_throwException() {
		InvalidConversionMethod fromClass = new InvalidConversionMethod("test");
		FieldMapperUtility.copy(fromClass, InvalidConversionMethod.class);

	}

}
