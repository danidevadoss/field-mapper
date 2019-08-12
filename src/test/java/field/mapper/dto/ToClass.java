package field.mapper.dto;

import field.mapper.annotation.FieldMapper;
import field.mapper.conversion.FieldConversions;

public class ToClass {

    @FieldMapper(name = "number", method = FieldConversions.STRING_TO_INTEGER, clazz = FieldConversions.class)
    private Integer number;

    public ToClass(){

    }
    public ToClass(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
