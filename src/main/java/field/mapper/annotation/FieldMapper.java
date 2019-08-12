package field.mapper.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to copy property values from on object to another object with same {@link FieldMapper#name} attribute.
 *
 * value can be converted to required form by using {@link FieldMapper#clazz}  and {@link FieldMapper#method} attributes
 * to use custom conversion method.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldMapper {

    /**
     * This attribute that is matched with the fields in object from which values should be copied if the name is same
     */
    String name() default "";


    /**
     * Class that will be used to invoke conversion {@link FieldMapper#method}, method should be defined public static
     */
    Class clazz() default Object.class;

    /**
     * String value of method name that should be invoked for {@link FieldMapper#clazz}, class should be public
     * if method is not specified
     */
    String method() default "";
}
