package field.mapper.dto;

import field.mapper.annotation.FieldMapper;
import field.mapper.conversion.FieldConversions;

public class InvalidConversionMethod {

    @FieldMapper(name = "test", method = "abcde", clazz = FieldConversions.class)
    private String test;

    public InvalidConversionMethod(String test){
        this.test=test;
    }

    public InvalidConversionMethod(){
        this.test=test;
    }
}
