package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByAdminIDAndPassword(Integer adminID, String password);
}
