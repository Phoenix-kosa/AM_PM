package phoenix.AM_PM.domain.projectplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import phoenix.AM_PM.domain.projectplan.dto.ProjectPlanDTO;
import phoenix.AM_PM.domain.projectplan.entity.ProjectPlan;
import phoenix.AM_PM.domain.projectplan.repository.ProjectPlanRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class ProjectPlanService {

    @Autowired
    private ProjectPlanRepository projectPlanRepository;

    private final Path erdLocation = Paths.get("C:\\kosastudy\\AM_PM\\Server\\src\\main\\resources\\static\\img\\plan");

    private final Path usecaseLocation = Paths.get("C:\\kosastudy\\AM_PM\\Server\\src\\main\\resources\\static\\img\\plan");
    private final Path uiLocation = Paths.get("C:\\kosastudy\\AM_PM\\Server\\src\\main\\resources\\static\\img\\plan");

    public void createDefaultProjectPlans(int projectId) {
        createDefaultPlan(projectId, "ERD", "/img/plan/default-erd-image.png", "https://www.erdcloud.com/");
        createDefaultPlan(projectId, "USECASE", "/img/plan/default-usecase-image.png", "https://example.com/usecase");
        createDefaultPlan(projectId, "UI", "/img/plan/default-ui-image.png", "https://www.figma.com/");
    }

    public ProjectPlanDTO getProjectPlanById(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
        return convertToDTO(projectPlan);
    }
    public ProjectPlanDTO getErdExample(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
        return convertToDTO(projectPlan);
    }

    public ProjectPlanDTO getUsecaseExample(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
        return convertToDTO(projectPlan);
    }

    public ProjectPlanDTO getUiExample(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
        return convertToDTO(projectPlan);
    }

    public void storeErd(int id, MultipartFile file) {
        storeFile(id, file, erdLocation, (projectPlan, filePath) -> projectPlan.setFilePath(filePath));
    }

    public void storeUsecase(int id, MultipartFile file) {
        storeFile(id, file, usecaseLocation, (projectPlan, filePath) -> projectPlan.setFilePath(filePath));
    }

    public void storeUi(int id, MultipartFile file) {
        storeFile(id, file, uiLocation, (projectPlan, filePath) -> projectPlan.setFilePath(filePath));
    }

    private void storeFile(int id, MultipartFile file, Path location, BiConsumer<ProjectPlan, String> filePathSetter) {
        try {
            if (!Files.exists(location)) {
                Files.createDirectories(location);
            }

            Path destinationFile = location.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            ProjectPlan projectPlan = projectPlanRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
            filePathSetter.accept(projectPlan, destinationFile.toString());

            String sampleImgPath = "/img/plan/" + file.getOriginalFilename();
            projectPlan.setSampleImg(sampleImgPath);

            projectPlanRepository.save(projectPlan);
        } catch (IOException e) {
            throw new RuntimeException("파일을 저장하지 못했습니다.", e);
        }
    }


    public ProjectPlanDTO createNewEtcPage(int projectId, MultipartFile file, String sampleUrl, String sampleImg) throws IOException {
        int etcCount = projectPlanRepository.countByProjectIdAndTitleStartingWith(projectId, "ETC");
        String newTitle = "ETC" + (etcCount + 1);

        Path destinationDirectory = Paths.get("uploads");
        if (!Files.exists(destinationDirectory)) {
            Files.createDirectories(destinationDirectory);
        }
        String originalFilename = file.getOriginalFilename();
        Path destinationFilePath = destinationDirectory.resolve(originalFilename);
        Files.copy(file.getInputStream(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

        ProjectPlan projectPlan = new ProjectPlan();
        projectPlan.setProjectId(projectId);
        projectPlan.setTitle(newTitle);
        projectPlan.setSampleUrl(sampleUrl);
        projectPlan.setSampleImg(sampleImg);
        projectPlan.setFilePath(destinationFilePath.toString());

        ProjectPlan savedProjectPlan = projectPlanRepository.save(projectPlan);
        return convertToDTO(savedProjectPlan);
    }

        public List<ProjectPlanDTO> getAllEtcPages(int projectId) {
            List<ProjectPlan> etcPages = projectPlanRepository.findByProjectIdAndTitleStartingWith(projectId, "ETC");
            return etcPages.stream().map(this::convertToDTO).collect(Collectors.toList());
        }


    public ProjectPlanDTO updateEtcPage(int id, String newSampleUrl, String newSampleImg) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));

        if (projectPlan.getTitle().startsWith("ETC")) {
            projectPlan.setSampleUrl(newSampleUrl);
            projectPlan.setSampleImg(newSampleImg);
            ProjectPlan updatedProjectPlan = projectPlanRepository.save(projectPlan);
            return convertToDTO(updatedProjectPlan);
        } else {
            throw new IllegalStateException("ETC 유형 페이지만 업데이트할 수 있습니다.");
        }
    }


    public void deleteProjectPlan(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

        if (projectPlan.getTitle().startsWith("ETC")) {
            projectPlanRepository.deleteById(id);
        } else {
            throw new IllegalStateException("ERD, USECASE, UI의 페이지는 삭제할 수 없습니다.");
        }
    }

    public ProjectPlanDTO updateSampleUrl(int id, String newSampleUrl) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
        projectPlan.setSampleUrl(newSampleUrl);
        ProjectPlan updatedProjectPlan = projectPlanRepository.save(projectPlan);
        return convertToDTO(updatedProjectPlan);
    }

    private void createDefaultPlan(int projectId, String title, String sampleImg, String sampleUrl) {
        ProjectPlan projectPlan = new ProjectPlan();
        projectPlan.setProjectId(projectId);
        projectPlan.setTitle(title);
        projectPlan.setSampleImg(sampleImg);
        projectPlan.setSampleUrl(sampleUrl);
        projectPlanRepository.save(projectPlan);
    }
    private ProjectPlanDTO convertToDTO(ProjectPlan projectPlan) {
        ProjectPlanDTO dto = new ProjectPlanDTO();
        dto.setId(projectPlan.getId());
        dto.setProjectId(projectPlan.getProjectId());
        dto.setTitle(projectPlan.getTitle());
        dto.setFilePath(projectPlan.getFilePath());
        dto.setSampleUrl(projectPlan.getSampleUrl());
        dto.setSampleImg(projectPlan.getSampleImg());
        return dto;
    }
}
