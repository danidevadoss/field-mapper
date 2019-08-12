package field.mapper.util;

import field.mapper.annotation.FieldMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains utility that will perform copying values from one object to another object using {@link FieldMapper} annotation.
 * only fields with {@link FieldMapper} will be considered for copying
 */
public class FieldMapperUtility {


    /**
     * Method to copy values of fields from one object to another object  with same {@link FieldMapper#name()} attribute
     *
     * @param objectFrom Object from which values will be copied, only fields with {@link FieldMapper} will be considered for copying
     * @param objectTo   Object to which values will be copied, only fields with {@link FieldMapper} will be considered for copying
     * @throws RuntimeException If method doesn't exist in the class specified or exception while accessing fields
     */
    public static void copy(Object objectFrom, Object objectTo) {

        List<Field> fromFields = Arrays.asList(objectFrom.getClass().getDeclaredFields());
        List<Field> toFields = Arrays.asList(objectTo.getClass().getDeclaredFields());
        Map<String, Field> fromFieldValueMap = new HashMap<String, Field>();

        // Creating map with key as name in field mapper and value as field to set value in objectTo
        for (Field field : fromFields) {
            field.setAccessible(true);
            FieldMapper fieldMapper = field.getAnnotation(FieldMapper.class);
            if (fieldMapper != null) {
                fromFieldValueMap.put(fieldMapper.name(), field);
            }
        }

        // looping through fields to be copied and copying field that matches
        for (Field field : toFields) {
            field.setAccessible(true);
            FieldMapper fieldMapper = field.getAnnotation(FieldMapper.class);
            if (fieldMapper != null && fromFieldValueMap.containsKey(fieldMapper.name())) {
                Field fromField = fromFieldValueMap.get(fieldMapper.name());
                try {
                    if (!"".equals(fieldMapper.method())) {
                        //Method invoked dynamically
                        Method method = fieldMapper.clazz().getDeclaredMethod(fieldMapper.method(), fromField.getType());
                        field.set(objectTo, method.invoke(null, fromField.get(objectFrom)));
                    } else {
                        field.set(objectTo, fromField.get(objectFrom));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Method to create instance of the {@code clazz} and copy the properties from {@code objectFrom}
     * @param objectFrom Object from which values will be copied, only fields with {@link FieldMapper} will be considered for copying
     * @param clazz class for which instance is created and properties are copied
     * @param <T>
     * @return new instance of {@code clazz} with properties copied from {@code objectFrom}
     */
    public static <T> T copy(Object objectFrom, Class<T> clazz) {
        try {
            Object objectTo = clazz.newInstance();
            copy(objectFrom, objectTo);
            return (T) objectTo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
