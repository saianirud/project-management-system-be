package com.back_end.project_management_system.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.entity.WorkLog;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jpa_repository.WorkLogRepository;

@Repository
public class WorkLogDAO {
	
	@Autowired
	WorkLogRepository workLogRepository;

	public WorkLog addWorkLog(WorkLog workLog) {
		
		return workLogRepository.save(workLog);
	}
	
	public WorkLog getWorkLog(int id) {
		
		Optional<WorkLog> worklog = workLogRepository.findById(id);
		
		if (!worklog.isPresent()) {
			throw new ProjectException("Worklog doesn't exists");
		}
		
		return worklog.get();
	}
	
	public int deleteWorkLog(int worklogId) {
		
		workLogRepository.deleteById(worklogId);
		
		return worklogId;
	}
}
