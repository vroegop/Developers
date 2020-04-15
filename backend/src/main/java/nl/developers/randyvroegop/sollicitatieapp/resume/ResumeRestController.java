package nl.developers.randyvroegop.sollicitatieapp.resume;

import lombok.RequiredArgsConstructor;
import nl.developers.randyvroegop.sollicitatieapp.resume.model.Resume;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A REST controller (probably slightly violating the idea some people might have when thinking about REST strictly)
 * to receive resumes, based on an example resume or receiving all of them.
 */
@CrossOrigin
@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeRestController {

    private final ResumeService resumeService;


    /**
     * @return Flux of all resumes
     */
    @GetMapping
    public Flux<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    /**
     * @param example A resume example to match Mongo's documents on in a field to field comparison.
     *                If you provide { "name": "Randy" } if will return all resume's matching the name.
     *                This makes filtering easy on the client-side, allowing complex searches without
     *                changing the backend implementation.
     * @return Any matching resumes
     * @apiNote This method might violate REST principles because this is basically a GET method with a request body
     * but this seems to be the most accurate way to supply an example. GET methods with Body statements are
     * not forbidden but might cause problems on older clients as it was forbidden in HTTP/1.1 RFC2616.
     * In 2014 RFC2616 was replaced by RFCs 7230-7237, still not encouraging GET methods with a request body.
     * GraphQL probably faced the same issue and also decided to use POST as the recommended  using
     * HTTP POST method for all requests, or provide the JSON in the URL: https://graphql.org/learn/serving-over-http/.
     */
    @PostMapping(path="/find")
    public Flux<Resume> getResumeByExample(@RequestBody Resume example) {
        return resumeService.getResumeByExample(example);
    }

    @PostMapping
    public Mono<Resume> newResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

}
