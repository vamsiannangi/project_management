package project_management.com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project_management.com.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
