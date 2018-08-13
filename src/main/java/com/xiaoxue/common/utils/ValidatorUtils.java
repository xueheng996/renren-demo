package com.xiaoxue.common.utils;

import com.xiaoxue.common.exception.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtils {

    private  static Validator validator;

    static {
        validator=Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static void validateEntity(Object object,Class<?>... groups) throws RRException{
        Set<ConstraintViolation<Object>> constraintViolationSet=validator.validate(object,groups);
        if(!constraintViolationSet.isEmpty()){
            ConstraintViolation<Object> constraint=(ConstraintViolation<Object>)constraintViolationSet.iterator().next();
            throw  new RRException(constraint.getMessage());
        }
    }
}
