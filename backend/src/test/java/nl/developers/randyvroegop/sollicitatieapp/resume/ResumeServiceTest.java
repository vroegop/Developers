package nl.developers.randyvroegop.sollicitatieapp.resume;

import nl.developers.randyvroegop.sollicitatieapp.resume.model.Resume;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static nl.developers.randyvroegop.sollicitatieapp.resume.model.TestHelper_ValidModels.validResumeBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResumeServiceTest {

    @Mock
    ResumeMongoRepository mongoRepository;

    @InjectMocks
    ResumeService resumeService;

    @DisplayName("Get all resumes")
    @Test
    void getAllResumes() {
        when(mongoRepository.findAll()).thenReturn(Flux.just(validResumeBuilder().build()));

        Flux<Resume> resumes = resumeService.getAllResumes();

        assertThat(resumes.blockFirst().getName()).isEqualTo("Randy Vroegop");
    }

    @DisplayName("Get resume by example")
    @Test
    void getResumeByExample() {
        when(mongoRepository.findAll(any(Example.class))).thenReturn(Flux.just(validResumeBuilder().build()));

        Resume example = Resume.builder().name("Randy Vroegop").build();
        Flux<Resume> resumes = resumeService.getResumeByExample(example);

        assertThat(resumes.blockFirst().getName()).isEqualTo("Randy Vroegop");
    }

    @DisplayName("Add resume")
    @Test
    void addResume() {
        when(mongoRepository.save(any(Resume.class))).thenReturn(Mono.just(validResumeBuilder().build()));

        Resume example = Resume.builder().name("Randy Vroegop").build();
        Mono<Resume> resumes = resumeService.saveResume(example);

        assertThat(resumes.block().getName()).isEqualTo("Randy Vroegop");
    }
}