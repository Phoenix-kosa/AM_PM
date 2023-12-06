package phoenix.AM_PM.domain.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phoenix.AM_PM.domain.notice.dto.NoticeDTO;
import phoenix.AM_PM.domain.notice.entity.Notice;
import phoenix.AM_PM.domain.notice.repository.NoticeRepository;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @PostMapping
    public ResponseEntity<String> addNotice(@RequestBody NoticeDTO dto) {
        Notice noticeEntity = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        noticeRepository.save(noticeEntity);
        return ResponseEntity.ok("successful");
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable int noticeId) {
        try {
            noticeRepository.deleteById(noticeId);
            return ResponseEntity.ok("successful");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed");
        }
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<String> editNotice(@PathVariable int noticeId, @RequestBody NoticeDTO dto) {

        return ResponseEntity.ok("successful");
    }

    @GetMapping
    public ResponseEntity<String> getNotice() {
        return ResponseEntity.ok("successful");
    }
}
