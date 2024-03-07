package com.example.hangman.util.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchValidatorInterface, Object> {

    String password;
    String confirmPassword;

    @Override
    public void initialize(PasswordMatchValidatorInterface constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
        Object passwordValue = beanWrapper.getPropertyValue(password);
        Object confirmPasswordValue = beanWrapper.getPropertyValue(confirmPassword);
        boolean isValid = passwordValue.equals(confirmPasswordValue);
        if (!isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Passwords doesn't match!")
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();

        }
        return isValid;
    }
}
