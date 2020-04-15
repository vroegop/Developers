package nl.developers.randyvroegop.sollicitatieapp.resume.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Document
@Builder
@Getter
// Needed by RestController on POST methods to instantiate a request body
@NoArgsConstructor
// Needed by @builder because we explicitly set a NoArgsConstructor as well
@AllArgsConstructor
public class Experience {

    @Indexed(unique = true)
    @NotBlank
    private String companyName;

    @Min(1)
    private int yearsExperience;
}
