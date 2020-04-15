package nl.developers.randyvroegop.sollicitatieapp.resume;

import nl.developers.randyvroegop.sollicitatieapp.resume.model.Resume;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeMongoRepository extends ReactiveMongoRepository<Resume, String> {
}