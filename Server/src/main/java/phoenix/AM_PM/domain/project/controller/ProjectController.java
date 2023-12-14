package phoenix.AM_PM.domain.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.project.dto.RequestProject;
import phoenix.AM_PM.domain.project.dto.ResponseProject;
import phoenix.AM_PM.domain.project.service.ProjectService;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.global.config.auth.MyUserDetails;

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
    public ResponseEntity getProjectList(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<ResponseProject> projectList = projectService.getProjectList(myUserDetails.getUser().getId());
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
    public ResponseEntity createProject(@RequestBody RequestProject requestProject,
                                        @AuthenticationPrincipal MyUserDetails myUserDetails) {
        ResponseProject project = projectService.createProject(requestProject, myUserDetails.getUser());
        return new ResponseEntity(project, HttpStatus.CREATED);
    }
    // 수정
    @PutMapping("/{project-id}")
    public ResponseEntity modifyProject(@RequestBody RequestProject requestProject,
                                        @PathVariable("project-id") Integer projectId,
                                        @AuthenticationPrincipal MyUserDetails myUserDetails) {
        projectService.modifyProject(projectId, requestProject, myUserDetails.getUser());
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
}
