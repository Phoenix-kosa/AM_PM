package phoenix.AM_PM.domain.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.project.dto.RequestProject;
import phoenix.AM_PM.domain.project.dto.ResponseProject;
import phoenix.AM_PM.domain.project.entity.Project;
import phoenix.AM_PM.domain.project.service.ProjectService;
import phoenix.AM_PM.domain.projectplan.service.ProjectPlanService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectPlanService projectPlanService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectPlanService projectPlanService) {
        this.projectService = projectService;
        this.projectPlanService = projectPlanService;
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
        try {
            ResponseProject project = projectService.createProject(requestProject);
            projectPlanService.createDefaultProjectPlans(project.getId()); // 새 프로젝트에 대한 기본 ProjectPlan 생성
            return new ResponseEntity(project, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("프로젝트 생성 중 오류 발생: " + e.getMessage());
        }
    }

    // 수정
    @PutMapping("/{project-id}")
    public ResponseEntity modifyProject(@RequestBody RequestProject requestProject,
                                        @PathVariable("project-id") Integer projectId) { // 날짜 생성, 수정
        projectService.modifyProject(projectId, requestProject);
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }


}
