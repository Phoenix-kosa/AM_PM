package phoenix.AM_PM.domain.projectplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import phoenix.AM_PM.domain.projectplan.dto.ProjectPlanDTO;
import phoenix.AM_PM.domain.projectplan.service.ProjectPlanService;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/plan")
public class ProjectPlanController {

    @Autowired
    private ProjectPlanService projectPlanService;




    @GetMapping("/project-plan/{id}")
    public ResponseEntity<ProjectPlanDTO> getProjectPlan(@PathVariable(name="id") int id) {
        ProjectPlanDTO projectPlanDTO = projectPlanService.getProjectPlanById(id);
        return ResponseEntity.ok(projectPlanDTO);
    }

    @GetMapping("/erd-example")
    public ResponseEntity<ProjectPlanDTO> getErdExample(@RequestParam("id") int id) {
        ProjectPlanDTO projectPlanDTO = projectPlanService.getErdExample(id);
        return ResponseEntity.ok(projectPlanDTO);
    }

    @PostMapping("/user-erd")
    public ResponseEntity<?> uploadErd(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
        projectPlanService.storeErd(id, file);
        return ResponseEntity.ok("ERD가 성공적으로 업로드 되었습니다");
    }

    @PutMapping("/user-erd/{id}")
    public ResponseEntity<?> updateErd(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        projectPlanService.storeErd(id, file);
        return ResponseEntity.ok("ERD가 성공적으로 수정되었습니다.");
    }

    @GetMapping("/usecase-example")
    public ResponseEntity<ProjectPlanDTO> getUsecaseExample(@RequestParam("id") int id) {
        ProjectPlanDTO projectPlanDTO = projectPlanService.getUsecaseExample(id);
        return ResponseEntity.ok(projectPlanDTO);
    }

    @PostMapping("/user-usecase")
    public ResponseEntity<?> uploadUsecase(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
        projectPlanService.storeUsecase(id, file);
        return ResponseEntity.ok("USECASE가 성공적으로 업로드 되었습니다.");
    }

    @PutMapping("/user-usecase/{id}")
    public ResponseEntity<?> updateUsecase(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        projectPlanService.storeUsecase(id, file);
        return ResponseEntity.ok("USECASE가 성공적으로 수정 되었습니다");
    }

    @GetMapping("/ui-example")
    public ResponseEntity<ProjectPlanDTO> getUiExample(@RequestParam("id") int id) {
        ProjectPlanDTO projectPlanDTO = projectPlanService.getUiExample(id);
        return ResponseEntity.ok(projectPlanDTO);
    }

    @PostMapping("/user-ui")
    public ResponseEntity<?> uploadUi(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
        projectPlanService.storeUi(id, file);
        return ResponseEntity.ok("UI가 성공적으로 업로드되었습니다.");
    }

    @PutMapping("/user-ui/{id}")
    public ResponseEntity<?> updateUi(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        projectPlanService.storeUi(id, file);
        return ResponseEntity.ok("UI가 성공적으로 수정 되었습니다.");
    }

    @PostMapping("/create-page")
    public ResponseEntity<?> createNewPage(
            @RequestParam("projectId") int projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("sampleUrl") String sampleUrl,
            @RequestParam("sampleImg") String sampleImg) {
        try {
            ProjectPlanDTO projectPlanDTO = projectPlanService.createNewEtcPage(projectId, file, sampleUrl, sampleImg);
            return ResponseEntity.ok(projectPlanDTO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("새 페이지를 만드는 중 오류가 발생했습니다. " + e.getMessage());
        }
    }

    @GetMapping("/etc-pages/{projectId}")
    public ResponseEntity<List<ProjectPlanDTO>> getAllEtcPages(@PathVariable int projectId) {
        List<ProjectPlanDTO> etcPages = projectPlanService.getAllEtcPages(projectId);
        return ResponseEntity.ok(etcPages);
    }


    @PutMapping("/update-etc/{id}")
    public ResponseEntity<?> updateEtcPage(@PathVariable int id,
                                           @RequestParam("sampleUrl") String newSampleUrl,
                                           @RequestParam("sampleImg") String newSampleImg) {
        try {
            ProjectPlanDTO updatedProjectPlanDTO = projectPlanService.updateEtcPage(id, newSampleUrl, newSampleImg);
            return ResponseEntity.ok(updatedProjectPlanDTO);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\n" +
                    "페이지 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectPlan(@PathVariable int id) {
        projectPlanService.deleteProjectPlan(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-url/{id}")
    public ResponseEntity<?> updateSampleUrl(@PathVariable int id, @RequestBody Map<String, String> request) {
        try {
            String newSampleUrl = request.get("newSampleUrl");
            ProjectPlanDTO updatedProjectPlan = projectPlanService.updateSampleUrl(id, newSampleUrl);
            return ResponseEntity.ok(updatedProjectPlan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("샘플 URL 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

}
