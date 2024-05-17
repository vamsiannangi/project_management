package project_management.com.testController;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import project_management.com.controller.ProjectController;
import project_management.com.models.Project;
import project_management.com.service.ProjectService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

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
        when(projectService.getAllProjects()).thenReturn(projects);

        List<Project> result = projectController.getAllProjects();

        assertEquals(1, result.size());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void testGetProjectById_ProjectExists() {
        when(projectService.getProjectById(1L)).thenReturn(project);

        Project result = projectController.getProjectById(1L);

        assertNotNull(result);
        assertEquals(project.getName(), result.getName());
    }


    @Test
    void testCreateProject() {
        when(projectService.createOrUpdateProject(project)).thenReturn(project);

        Project result = projectController.createProject(project);

        assertNotNull(result);
        assertEquals(project.getName(), result.getName());
        verify(projectService, times(1)).createOrUpdateProject(project);
    }

    @Test
    void testUpdateProject() {
        project.setId(1L);
        when(projectService.createOrUpdateProject(project)).thenReturn(project);

        Project result = projectController.updateProject(1L, project);

        assertNotNull(result);
        assertEquals(project.getName(), result.getName());
        verify(projectService, times(1)).createOrUpdateProject(project);
    }

    @Test
    void testDeleteProject_ProjectExists() {
        doNothing().when(projectService).deleteProject(1L);

        String result = projectController.deleteProject(1L);

        assertEquals("Deleted successfully", result);
        verify(projectService, times(1)).deleteProject(1L);
    }


}