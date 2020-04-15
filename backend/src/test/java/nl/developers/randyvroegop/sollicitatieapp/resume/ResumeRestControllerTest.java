package nl.developers.randyvroegop.sollicitatieapp.resume;

import nl.developers.randyvroegop.sollicitatieapp.resume.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static nl.developers.randyvroegop.sollicitatieapp.resume.model.TestHelper_ValidModels.validResumeBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResumeRestControllerTest {

    @Mock
    ResumeService resumeService;

    @InjectMocks
    ResumeRestController resumeRestController;

    @DisplayName("REST: Get all resumes")
    @Test
    void getAllResumes() {
        when(resumeService.getAllResumes()).thenReturn(Flux.just(validResumeBuilder().build()));

        Flux<Resume> resumes = resumeRestController.getAllResumes();

        assertThat(resumes.blockFirst().getName()).isEqualTo("Randy Vroegop");
    }

    @DisplayName("REST: Get all resumes by example")
    @Test
    void getAllResumesByExample() {
        when(resumeService.getResumeByExample(any(Resume.class))).thenReturn(Flux.just(validResumeBuilder().build()));

        Resume example = Resume.builder().name("Randy Vroegop").build();
        Flux<Resume> resumes = resumeRestController.getResumeByExample(example);

        assertThat(resumes.blockFirst().getName()).isEqualTo("Randy Vroegop");
    }

    @DisplayName("REST: Add resume")
    @Test
    void addResume() {
        when(resumeService.saveResume(any(Resume.class))).thenReturn(Mono.just(validResumeBuilder().build()));

        Resume example = Resume.builder().name("Randy Vroegop").build();
        Mono<Resume> resumes = resumeRestController.newResume(example);

        assertThat(resumes.block().getName()).isEqualTo("Randy Vroegop");
    }
}