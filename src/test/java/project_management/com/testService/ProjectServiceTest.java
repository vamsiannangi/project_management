package project_management.com.testService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project_management.com.models.Project;
import project_management.com.repository.ProjectRepository;
import project_management.com.service.ProjectService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(1, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById_ProjectExists() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Project foundProject = projectService.getProjectById(1L);

        assertNotNull(foundProject);
        assertEquals(project.getName(), foundProject.getName());
    }

    @Test
    void testGetProjectById_ProjectDoesNotExist() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            projectService.getProjectById(1L);
        });

        assertEquals("Project not found with id: 1", exception.getMessage());
    }

    @Test
    void testCreateOrUpdateProject() {
        when(projectRepository.save(project)).thenReturn(project);

        Project savedProject = projectService.createOrUpdateProject(project);

        assertNotNull(savedProject);
        assertEquals(project.getName(), savedProject.getName());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testDeleteProject_ProjectExists() {
        when(projectRepository.existsById(1L)).thenReturn(true);
        doNothing().when(projectRepository).deleteById(1L);

        assertDoesNotThrow(() -> projectService.deleteProject(1L));
        verify(projectRepository, times(1)).existsById(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProject_ProjectDoesNotExist() {
        when(projectRepository.existsById(1L)).thenReturn(false);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            projectService.deleteProject(1L);
        });

        assertEquals("Project not found with id: 1", exception.getMessage());
        verify(projectRepository, times(1)).existsById(1L);
        verify(projectRepository, times(0)).deleteById(1L);
    }
}
