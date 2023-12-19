package phoenix.AM_PM.domain.members.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.members.dto.RequestMembers;
import phoenix.AM_PM.domain.members.dto.ResponseMembers;
import phoenix.AM_PM.domain.members.service.MembersService;

import java.util.List;

@RestController
public class MembersController {
    private final MembersService membersService;

    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    // 프로젝트 멤버 조회
    @GetMapping("/api/members/{project-id}")
    public ResponseEntity getMembers(@PathVariable("project-id") Integer projectId) {
        List<ResponseMembers> responseMembersList = membersService.getMembers(projectId);
        return ResponseEntity.ok().body(responseMembersList);
    }
    // 프로젝트 멤버 추가
    @PutMapping("/api/members/{project-id}")
    public ResponseEntity addMembers(@PathVariable("project-id") Integer projectId,
                                     @RequestBody RequestMembers requestMembers) {
        membersService.addMembers(projectId, requestMembers);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // 프로젝트 멤버 삭제
    @DeleteMapping("/api/members/{project-id}")
    public ResponseEntity removeMembers(@PathVariable("project-id") Integer projectId,
                                     @RequestBody RequestMembers requestMembers) {
        membersService.removeMembers(projectId, requestMembers);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
    // 프로젝트 대표 멤버 조회
    @GetMapping("/api/representative_member/{project-id}")
    public ResponseEntity getRepresentativeMember(@PathVariable("project-id") Integer projectId) {
        ResponseMembers responseMembers = membersService.getRepresentativeMember(projectId);
        return ResponseEntity.ok().body(responseMembers);
    }
    // 프로젝트 대표 멤버 변경
    @PutMapping("/api/representative_member/{project-id}")
    public ResponseEntity modifyRepresentativeMember(@PathVariable("project-id") Integer projectId,
                                                     @RequestBody RequestMembers requestMembers) {
        membersService.modifyRepresentativeMember(projectId, requestMembers);
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
}
