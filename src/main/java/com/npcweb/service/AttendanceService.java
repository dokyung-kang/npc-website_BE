package com.npcweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npcweb.domain.Attendance;
import com.npcweb.repository.AttendanceRepository;

@Service
public class AttendanceService {
	@Autowired AttendanceRepository attendanceRepo;
	
	public void insert(Attendance a) {
		attendanceRepo.save(a);
	}
	
	public Attendance getAttendance(long attendance_id) {
		return attendanceRepo.findById(attendance_id).get();
	}
}
