package com.example.demo.web.rest;

import com.example.demo.entity.Report;
import com.example.demo.entity.enumeration.Status;
import com.example.demo.repository.ReportRepository;
import com.example.demo.web.rest.errors.BadRequestAlertException;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Transactional
@Log4j2
public class ReportResource {

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/reports")
    public ResponseEntity<Report> createReport(@Valid @RequestBody Report report) throws URISyntaxException {
        log.debug("REST request to save Report : {}", report);
        if (report.getId() != null) {
            throw new BadRequestAlertException("Um novo report nao pode ter um ID", "usuario", "idExists");
        }
        Report result = reportRepository.save(report);
        return ResponseEntity.created(new URI("/api/reports/" + result.getId()))
                .header("id", result.getId().toString())
                .body(result);
    }

    @PutMapping("/reports")
    public ResponseEntity<Report> updateReport(@Valid @RequestBody Report report) throws URISyntaxException {
        log.debug("REST request to update Report : {}", report);
        if (report.getId() == null) {
            throw new BadRequestAlertException("Id inválido", "reports", "idNull");
        }
        report.setStatus(Status.CONCLUIDO);
        Report result = reportRepository.save(report);
        return ResponseEntity.ok()
                .header("id", result.getId().toString())
                .body(result);
    }

    @GetMapping("/reports")
    public List<Report> getAllReports() {
        log.debug("REST request to get all Reports");
        return reportRepository.findAll();
    }


    @GetMapping("/reports/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        log.debug("REST request to get Report : {}", id);
        return reportRepository.findById(id)
                .map((report) -> ResponseEntity.ok().body(report))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        log.debug("REST request to delete Report : {}", id);
        reportRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .header("id", id.toString())
                .build();
    }
}
