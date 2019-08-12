package field.mapper.dto;

import field.mapper.annotation.FieldMapper;
import field.mapper.conversion.FieldConversions;

public class FromClass {

    @FieldMapper(name = "number", method = FieldConversions.INTEGER_TO_STRING, clazz = FieldConversions.class)
    private String number;

    public FromClass() {
    }

    public FromClass(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
