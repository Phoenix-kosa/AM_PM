package phoenix.AM_PM.domain.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.project.dto.RequestProject;
import phoenix.AM_PM.domain.project.dto.ResponseProject;
import phoenix.AM_PM.domain.project.entity.Project;
import phoenix.AM_PM.domain.project.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity getProjectList() { // 로그인한 사용자의 프로젝트 목록 조회
        // 임시 아이디 1
        Integer userId = 1;
        List<ResponseProject> projectList = projectService.getProjectList(userId);
        return ResponseEntity.ok().body(projectList);
    }
    // 조회
    @GetMapping("/{project-id}")
    public ResponseEntity getProject(@PathVariable("project-id") Integer projectId) {
        ResponseProject project = projectService.readProject(projectId);
        return ResponseEntity.ok().body(project);
    }
    // 생성
    @PostMapping
    public ResponseEntity createProject(@RequestBody RequestProject requestProject) { // 로그인 사용자 가져다쓰기
        System.out.println(requestProject);
        ResponseProject project = projectService.createProject(requestProject);
        return new ResponseEntity(project, HttpStatus.CREATED);
    }
    // 수정
    @PutMapping("/{project-id}")
    public ResponseEntity modifyProject(@RequestBody RequestProject requestProject,
                                        @PathVariable("project-id") Integer projectId) { // 날짜 생성, 수정
        projectService.modifyProject(projectId, requestProject);
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
}
