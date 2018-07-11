package com.example.report.report;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/report")
public class ReportServices {

	/**
	 * LOG
	 */
	private static final Logger LOG = Logger.getLogger(ReportServices.class.getName());
	
	/**
	 * @Inject
	 */
	@Autowired
	private ReportRepository reportRepository;
	
	/**
	 * create
	 * 
	 * @param name
	 * 
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> create(@RequestBody final Report report) {
		LOG.info("create: " + report);
		this.reportRepository.insert(new Report(UUID.randomUUID(), report.getName()));
		
		return ResponseEntity.ok("OK");
	}
	
	/**
	 * find
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Report>> findAll() {
		LOG.info("find ALL: ");
		return ResponseEntity.ok(this.reportRepository.findAll());
	}
}
