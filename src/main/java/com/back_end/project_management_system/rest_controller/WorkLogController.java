package com.back_end.project_management_system.rest_controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.dto.WorkLogDTO;
import com.back_end.project_management_system.entity.WorkLog;
import com.back_end.project_management_system.service.WorkLogService;

@RestController
@RequestMapping("/worklogs")
public class WorkLogController {
	
	@Autowired
	WorkLogService workLogService;
	
	@PostMapping("")
	public ResponseEntity<?> addWorkLog(@Valid @RequestBody WorkLogDTO workLogDTO) {
		
		WorkLog workLog = workLogService.addWorkLog(workLogDTO);
		
		return ResponseEntity.ok(workLog);
	}
	
	@PutMapping("/{worklogId}")
	public ResponseEntity<?> updateWorkLog(@Valid @RequestBody WorkLogDTO workLogDTO, @PathVariable int worklogId) {
		
		WorkLog workLog = workLogService.updateWorkLog(workLogDTO, worklogId);
		
		return ResponseEntity.ok(workLog);
	}
	
	@DeleteMapping("/{worklogId}")
	public ResponseEntity<?> deleteWorkLog(@PathVariable int worklogId, HttpServletRequest request) {

		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		
		int deletedIssueId = workLogService.deleteWorkLog(worklogId, jwtToken);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("issueId", deletedIssueId);
		return ResponseEntity.ok(response);
	}

}
