package nl.developers.randyvroegop.sollicitatieapp.resume;

import lombok.RequiredArgsConstructor;
import nl.developers.randyvroegop.sollicitatieapp.resume.model.Resume;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResumeService {

    final ResumeMongoRepository resumeMongoRepository;

    public Flux<Resume> getAllResumes() {
        return resumeMongoRepository.findAll();
    }

    public Flux<Resume> getResumeByExample(Resume example) {
        return resumeMongoRepository.findAll(Example.of(example));
    }

    public Mono<Resume> saveResume(Resume resume) {
        return resumeMongoRepository.save(resume);
    }
}
