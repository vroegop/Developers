package nl.developers.randyvroegop.sollicitatieapp.resume.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static nl.developers.randyvroegop.sollicitatieapp.resume.model.TestHelper_ValidModels.validResumeBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class ResumeTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("Resume should be valid")
    @Test
    public void resumeShouldBeValid() {
        Resume resume = validResumeBuilder().build();

        Set<ConstraintViolation<Resume>> noValidationExpected = validator.validate(resume);

        assertThat(noValidationExpected.size()).isEqualTo(0);
    }

    @DisplayName("Resume name should not be empty")
    @Test
    public void resumeNameShouldNotBeEmpty() {
        Resume invalidName = validResumeBuilder().name("").build();

        Set<ConstraintViolation<Resume>> notBlankViolationExpected = validator.validate(invalidName);

        assertThat(notBlankViolationExpected.size()).isEqualTo(1);
        assertThat(notBlankViolationExpected.iterator().next().getMessage()).isEqualTo("must not be blank");
    }

    @DisplayName("Phonenumber should match regex pattern")
    @Test
    public void phoneNumberShouldMatchPattern() {
        Resume invalidName = validResumeBuilder().phonenumber("").build();

        Set<ConstraintViolation<Resume>> phonenumberNotMatchingPatternViolation = validator.validate(invalidName);

        assertThat(phonenumberNotMatchingPatternViolation.size()).isEqualTo(1);
        assertThat(phonenumberNotMatchingPatternViolation.iterator().next().getMessage()).contains("must match");
    }
}