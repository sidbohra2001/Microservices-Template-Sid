package com.security.authentication.config;

import com.security.authentication.exeptions.InvalidPasswordException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class PasswordConstraintValidator {

    @SneakyThrows
    public boolean isValid(String password) {

        //Defining Password Validator
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule(),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)
        ));
        //Validating The Password
        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()) return true;
        String errorMessage = String.join(",", validator.getMessages(result));
        throw new InvalidPasswordException(errorMessage);
    }
}
