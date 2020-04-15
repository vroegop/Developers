package nl.developers.randyvroegop.sollicitatieapp.resume.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static nl.developers.randyvroegop.sollicitatieapp.resume.model.TestHelper_ValidModels.validExperienceBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class ExperienceTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("Experience should be valid")
    @Test
    public void experienceShouldBeValid() {
        Experience experience = validExperienceBuilder().build();

        Set<ConstraintViolation<Experience>> noValidationExpected = validator.validate(experience);

        assertThat(noValidationExpected.size()).isEqualTo(0);
    }

    @DisplayName("Experience company name should not be blank")
    @Test
    public void experienceShouldHaveCompanyName() {
        Experience experience = validExperienceBuilder().companyName("").build();

        Set<ConstraintViolation<Experience>> notBlankViolationExpected = validator.validate(experience);

        assertThat(notBlankViolationExpected.size()).isEqualTo(1);
        assertThat(notBlankViolationExpected.iterator().next().getMessage()).isEqualTo("must not be blank");
    }

    @DisplayName("yearsExperience should be higher then zero")
    @Test
    public void yearsExperienceShouldBeHigherThenZero() {
        Experience.ExperienceBuilder experienceBuilder = validExperienceBuilder();

        Set<ConstraintViolation<Experience>> noValidationExpected = validator.validate(experienceBuilder.build());

        assertThat(noValidationExpected.size()).isEqualTo(0);

        Set<ConstraintViolation<Experience>> zeroIsNotValid = validator.validate(experienceBuilder.yearsExperience(0).build());

        assertThat(zeroIsNotValid.size()).isEqualTo(1);
        assertThat(zeroIsNotValid.iterator().next().getMessage()).contains("must be greater than or equal to 1");

        Set<ConstraintViolation<Experience>> negativeIsNotValid = validator.validate(experienceBuilder.yearsExperience(-1).build());

        assertThat(zeroIsNotValid.size()).isEqualTo(1);
        assertThat(negativeIsNotValid.iterator().next().getMessage()).contains("must be greater than or equal to 1");
    }
}