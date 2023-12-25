package phoenix.AM_PM.domain.projectplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import phoenix.AM_PM.domain.projectplan.dto.ProjectPlanDTO;
import phoenix.AM_PM.domain.projectplan.entity.ProjectPlan;
import phoenix.AM_PM.domain.projectplan.repository.ProjectPlanRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class ProjectPlanService {

    @Autowired
    private ProjectPlanRepository projectPlanRepository;
    private final Path srsLocation = Paths.get("Server/src/main/resources/static/img/plan");
    private final Path erdLocation = Paths.get("Server/src/main/resources/static/img/plan");

    private final Path usecaseLocation = Paths.get("Server/src/main/resources/static/img/plan");
    private final Path uiLocation = Paths.get("Server/src/main/resources/static/img/plan");

    private final Path planLocation = Paths.get("Server/src/main/resources/static/img/plan");

    public void createDefaultProjectPlans(int projectId) {
        createDefaultPlan( projectId, "srs", "/resources/static/img/plan/default-srs-image.png", "https://www.google.com/intl/ko_kr/sheets/about/");
        createDefaultPlan( projectId, "erd", "/static/img/plan/default-erd-image.png", "https://www.erdcloud.com/");
        createDefaultPlan( projectId, "usecase", "/resources/static/img/plan/default-usecase-image.png", "https://example.com/usecase");
        createDefaultPlan( projectId, "ui", "/resources/static/img/plan/default-ui-image.png", "https://www.figma.com/");

    }


    public ProjectPlanDTO createNewPage(String title, int projectId) {
        // 중복 제목 검사
        if (projectPlanRepository.existsByProjectIdAndTitle(projectId, title)) {
            throw new RuntimeException("이미 존재하는 페이지 제목입니다.");
        }
        // 새 ProjectPlan 객체 생성
        ProjectPlan newPage = ProjectPlan.builder()
                .projectId(projectId)
                .title(title)
                .filePath("") // 필요에 따라 설정
                .sampleUrl("") // 필요에 따라 설정
                .sampleImg("") // 필요에 따라 설정
                .build();

        // DB에 저장
        ProjectPlan savedPage = projectPlanRepository.save(newPage);

        // DTO 변환 후 반환
        return convertToDTO(savedPage);
    }

    public ProjectPlanDTO getExampleByProjectIdAndId(int projectId, int id) {
        ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndId(projectId, id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 프로젝트 아이디 또는 아이디: " + projectId + ", " + id));
        return convertToDTO(projectPlan);
    }

    public ProjectPlanDTO getFindById(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id not found"));
        return convertToDTO(projectPlan);
    }


    public List<ProjectPlanDTO> getEtcPagesByProjectId(int projectId) {
        List<ProjectPlan> projectPlans = projectPlanRepository.findByProjectId(projectId);
        return projectPlans.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    // projectid get
    public ProjectPlanDTO getProjectPlanByProjectId(int id) {
        ProjectPlan projectPlan = projectPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + id));
        return convertToDTO(projectPlan);
    }

    public ProjectPlanDTO getSrsExampleByProjectId(int projectId) {
        ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndTitle(projectId, "SRS")
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + projectId));
        return convertToDTO(projectPlan);
    }
    public ProjectPlanDTO getErdExampleByProjectId(int projectId) {
        ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndTitle(projectId, "ERD")
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + projectId));
        return convertToDTO(projectPlan);
    }

    public ProjectPlanDTO getUsecaseExampleByProjectId(int projdctId) {
        ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndTitle(projdctId, "USECASE")
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + projdctId));
        return convertToDTO(projectPlan);
    }

    public ProjectPlanDTO getUiExampleByProjectId(int projdctId) {
        ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndTitle(projdctId, "UI")
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디: " + projdctId));
        return convertToDTO(projectPlan);
    }


    public void storeSrs(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setErdPath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, srsLocation, setErdPath);
    }

    public void storeErd(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setErdPath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, erdLocation, setErdPath);
    }

    public void storeUsecase(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setUsecasePath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, usecaseLocation, setUsecasePath);
    }

    public void storeUi(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setUiPath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, uiLocation, setUiPath);
    }

    public void storeImage(int id, int projectId, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setFilePath = ProjectPlan::setFilePath;
        storeFileByIdAndProjectId(id, projectId, file, uiLocation, setFilePath);
    }



    private void storeFileByIdAndProjectId(int id, int projectId, MultipartFile file, Path location, BiConsumer<ProjectPlan, String> filePathSetter) {
        try {
            if (!Files.exists(location)) {
                Files.createDirectories(location);
            }

            Path destinationFile = location.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            ProjectPlan projectPlan = projectPlanRepository.findByIdAndProjectId(id, projectId)
                    .orElseThrow(() -> new IllegalArgumentException("ID와 프로젝트 ID에 해당하는 프로젝트 기획이 존재하지 않습니다: " + id + ", " + projectId));

            filePathSetter.accept(projectPlan, destinationFile.toString());

            String sampleImgPath = "/img/plan/" + file.getOriginalFilename();
            projectPlan.setSampleImg(sampleImgPath);

            projectPlanRepository.save(projectPlan);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file", e);
        }
    }







    public void updateErd(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setErdPath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, erdLocation, setErdPath);
    }


    public void updateUsecase(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setErdPath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, usecaseLocation, setErdPath);
    }



    public void updateUi(int projectId, String title, MultipartFile file) {
        BiConsumer<ProjectPlan, String> setErdPath = ProjectPlan::setFilePath;
        storeFileByProjectIdAndTitle(projectId, title, file, uiLocation, setErdPath);
    }


    private void storeFileByProjectIdAndTitle(int projectId, String title, MultipartFile file,
                                              Path location, BiConsumer<ProjectPlan, String> filePathSetter) {
        try {
            if (!Files.exists(location)) {
                Files.createDirectories(location);
            }

            Path destinationFile = location.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndTitle(projectId, title)
                    .orElseThrow(() -> new IllegalArgumentException("프로젝트 아이디: " + projectId + "분류" + title));

            filePathSetter.accept(projectPlan, destinationFile.toString());

            String sampleImgPath = "/img/plan/" + file.getOriginalFilename();
            projectPlan.setSampleImg(sampleImgPath);

            projectPlanRepository.save(projectPlan);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file", e);
        }
    }




    public ProjectPlanDTO createNewEtcPage(int projectId, MultipartFile file, String sampleUrl, String sampleImg, String title) throws IOException {
        String filePath = null;
        if (file != null && !file.isEmpty()) {
            Path destinationDirectory = Paths.get("uploads");
            if (!Files.exists(destinationDirectory)) {
                Files.createDirectories(destinationDirectory);
            }
            String originalFilename = file.getOriginalFilename();
            Path destinationFilePath = destinationDirectory.resolve(originalFilename);
            Files.copy(file.getInputStream(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            filePath = destinationFilePath.toString();
        }

        ProjectPlan projectPlan = new ProjectPlan();
        projectPlan.setProjectId(projectId);
        projectPlan.setTitle(title);
        projectPlan.setSampleUrl(sampleUrl);
        projectPlan.setSampleImg(sampleImg);
        projectPlan.setFilePath(filePath); // 여기서 null이 될 수도 있음

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


    public void deleteProjectPlanByTitle(String title) {
        List<ProjectPlan> projectPlans = projectPlanRepository.findByTitle(title);

        if (projectPlans.isEmpty()) {
            throw new IllegalArgumentException("Invalid title: " + title);
        }

        for (ProjectPlan projectPlan : projectPlans) {
            if (projectPlan.getTitle().equalsIgnoreCase("srs") ||
                    projectPlan.getTitle().equalsIgnoreCase("erd") ||
                    projectPlan.getTitle().equalsIgnoreCase("usecase") ||
                    projectPlan.getTitle().equalsIgnoreCase("ui")) {
                throw new IllegalStateException("SRS, ERD, USECASE, UI의 페이지는 삭제할 수 없습니다.");
            } else {
                projectPlanRepository.delete(projectPlan);
            }
        }
    }



    public ProjectPlanDTO updateSampleUrl(int projectId, String title, String newSampleUrl) {
        ProjectPlan projectPlan = projectPlanRepository.findByProjectIdAndTitle(projectId, title)
                .orElseThrow(() -> new IllegalArgumentException("No project plan found with ID: " + projectId + " and title: " + title));
        projectPlan.setSampleUrl(newSampleUrl);
        ProjectPlan updatedProjectPlan = projectPlanRepository.save(projectPlan);
        return convertToDTO(updatedProjectPlan);
    }


    private void createDefaultPlan( int projectId, String title, String sampleImg, String sampleUrl) {
        boolean exists = projectPlanRepository.existsByProjectIdAndTitle(projectId, title);
        if (!exists) {
            ProjectPlan projectPlan = new ProjectPlan();
            projectPlan.setProjectId(projectId);
            projectPlan.setTitle(title);
            projectPlan.setSampleImg(sampleImg);
            projectPlan.setSampleUrl(sampleUrl);
            projectPlanRepository.save(projectPlan);
        }
    }

    public ProjectPlanDTO getCustomTypeExampleByProjectId(String title, int projectId) {
        // 데이터베이스 또는 저장소에서 title과 projectId에 해당하는 데이터 조회
        // 예시: title에 해당하는 특정 리소스를 조회하는 쿼리
        Optional<ProjectPlan> optionalProjectPlan = projectPlanRepository.findByTitleAndProjectId(title, projectId);

        if (optionalProjectPlan.isPresent()) {
            ProjectPlan projectPlan = optionalProjectPlan.get();
            // 조회된 데이터를 DTO로 변환
            return convertToDTO(projectPlan);
        } else {
            // 해당 title에 대한 데이터가 없는 경우
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found for title: " + title);
        }
    }

    public void storeCustomType(int projectId, String title, MultipartFile file, String type) {
        Path location = determineLocationByType(type); // 각 타입에 따른 파일 저장 경로를 결정하는 메소드

        BiConsumer<ProjectPlan, String> filePathSetter = (projectPlan, path) -> {
            projectPlan.setFilePath(path);
            // 필요한 경우 type에 따라 추가적인 설정을 할 수 있습니다.
        };

        storeFileByProjectIdAndTitle(projectId, title, file, location, filePathSetter);
    }
    private Path determineLocationByType(String type) {
        // 모든 파일을 'Server/src/main/resources/static/img/plan' 폴더에 저장
        return Paths.get("Server/src/main/resources/static/img/plan");
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
