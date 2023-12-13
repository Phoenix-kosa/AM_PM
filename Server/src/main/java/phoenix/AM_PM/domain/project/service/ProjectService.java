package phoenix.AM_PM.domain.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.member.entity.Members;
import phoenix.AM_PM.domain.member.entity.Roles;
import phoenix.AM_PM.domain.member.repository.MemberRepository;
import phoenix.AM_PM.domain.project.dto.RequestProject;
import phoenix.AM_PM.domain.project.dto.ResponseProject;
import phoenix.AM_PM.domain.project.entity.Project;
import phoenix.AM_PM.domain.project.repository.ProjectRepository;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.global.exception.BusinessLogicException;
import phoenix.AM_PM.global.exception.ExceptionCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    ProjectService(ProjectRepository projectRepository, MemberRepository memberRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    public List<ResponseProject> getProjectList(Integer userId) {
        List<ResponseProject> projectList = new ArrayList<>();
        memberRepository.findAllByUserId(userId).stream().forEach(members -> projectList.add(ResponseProject.of(members.getProject())));
        return projectList;
    }

    public ResponseProject readProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()->new BusinessLogicException(ExceptionCode.PROJECT_NOT_FOUND));
        return ResponseProject.of(project);
    }

    public ResponseProject createProject(RequestProject requestProject) {
        Project entity = Project.from(requestProject);
        Project project = projectRepository.save(entity);

        Members member = new Members().builder()
                // 임시 유저 아이디 1
                .user(userRepository.findById(1).orElseThrow(()->new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)))
                .project(project)
                .roles(Roles.representative_member)
                .build();
        memberRepository.save(member);

        return ResponseProject.of(project);
    }

    @Transactional
    public void modifyProject(Integer projectId, RequestProject requestProject) {
        Project project = projectRepository.findById(projectId).orElseThrow(()->new BusinessLogicException(ExceptionCode.PROJECT_NOT_FOUND));
        Optional.ofNullable(requestProject.getTitle()).ifPresent(project::updateTitle);
        Optional.ofNullable(requestProject.getContent()).ifPresent(project::updateContent);
        Optional.ofNullable(requestProject.getStartDate()).ifPresent(project::updateStartDate);
        Optional.ofNullable(requestProject.getEndDate()).ifPresent(project::updateEndDate);
    }
}
