package com.java;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})

public @interface Annotations {

    String value() default "";
    String name();
    int      age();
    String[] newNames();
}
