package nl.developers.randyvroegop.sollicitatieapp.resume.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Getter
@Document
// Needed by RestController on POST methods to instantiate a request body
@NoArgsConstructor
// Needed by @builder because we explicitly set a NoArgsConstructor as well
@AllArgsConstructor
public class Resume {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "^(((\\+31|0|0031)6)[1-9][0-9]{7})$")
    private String phonenumber;

    @Valid
    @NotNull
    private List<Experience> experience;
}
