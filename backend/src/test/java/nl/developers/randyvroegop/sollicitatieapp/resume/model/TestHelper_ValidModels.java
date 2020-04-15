package nl.developers.randyvroegop.sollicitatieapp.resume.model;

import java.util.List;

public class TestHelper_ValidModels {
    public static Resume.ResumeBuilder validResumeBuilder() {
        return Resume.builder()
                .name("Randy Vroegop")
                .phonenumber("+31633831814")
                .experience(List.of(validExperienceBuilder().build(), validExperienceBuilder2().build()));
    }

    public static Experience.ExperienceBuilder validExperienceBuilder() {
        return Experience.builder()
                .companyName("ThePatientSafetyCompany")
                .yearsExperience(2);
    }

    public static Experience.ExperienceBuilder validExperienceBuilder2() {
        return Experience.builder()
                .companyName("Quintor")
                .yearsExperience(2);
    }
}
