//package com.smartcampus.campusportal.service;
//
//import com.smartcampus.campusportal.model.*;
//import com.smartcampus.campusportal.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.YearMonth;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class DashboardService {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private LecturerRepository lecturerRepository;
//
//   /* @Autowired
//    private ConsultationRepository consultationRepository;
//
//    @Autowired
//    private RegistrationRepository registrationRepository;
//   8/
//    */
//
//
//    @Autowired
//    private MaintenanceIssueRepository maintenanceIssueRepository;
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    /**
//     * Get counts of all entities in the system
//     * @return Map containing counts of all entities
//     */
//    public Map<String, Object> getAllCounts() {
//        Map<String, Object> counts = new HashMap<>();
//
//        // Basic entity counts
//        long studentCount = studentRepository.count();
//        long lecturerCount = lecturerRepository.count();
//
//        counts.put("students", studentCount);
//        counts.put("lecturers", lecturerCount);
//        // counts.put("consultations", consultationRepository.count());
//        counts.put("maintenance", maintenanceIssueRepository.count());
//
//        // Calculate registrations as the sum of students and lecturers
//        counts.put("registrations", studentCount + lecturerCount);
//
//        // Maintenance status counts
//       /* Map<String, Long> consultationStatus = new HashMap<>();
//        consultationStatus.put("PENDING", consultationRepository.countByStatus("PENDING"));
//        consultationStatus.put("APPROVED", consultationRepository.countByStatus("APPROVED"));
//        consultationStatus.put("COMPLETED", consultationRepository.countByStatus("COMPLETED"));
//        consultationStatus.put("CANCELLED", consultationRepository.countByStatus("CANCELLED"));
//        counts.put("consultationStatus", consultationStatus);
//*/
//        // Maintenance status counts
//        Map<String, Long> maintenanceStatus = new HashMap<>();
//        maintenanceStatus.put("PENDING", maintenanceIssueRepository.countByStatus("PENDING"));
//        maintenanceStatus.put("IN_PROGRESS", maintenanceIssueRepository.countByStatus("IN_PROGRESS"));
//        maintenanceStatus.put("COMPLETED", maintenanceIssueRepository.countByStatus("COMPLETED"));
//        maintenanceStatus.put("REJECTED", maintenanceIssueRepository.countByStatus("REJECTED"));
//        counts.put("maintenanceStatus", maintenanceStatus);
//
//        // Maintenance requests
//        counts.put("maintenanceRequests", maintenanceIssueRepository.count());
//
//        return counts;
//    }
//
//    /**
//     * Get monthly trends for the past 6 months
//     * @return Map containing monthly trends for different entities
//     */
//    public Map<String, List<Map<String, Object>>> getMonthlyTrends() {
//        Map<String, List<Map<String, Object>>> trends = new HashMap<>();
//
//        // Get current date and calculate the past 6 months
//        LocalDate currentDate = LocalDate.now();
//        List<YearMonth> last6Months = new ArrayList<>();
//
//        for (int i = 5; i >= 0; i--) {
//            last6Months.add(YearMonth.from(currentDate.minusMonths(i)));
//        }
//
//        // Initialize trend lists
//        List<Map<String, Object>> studentTrends = new ArrayList<>();
//        List<Map<String, Object>> consultationTrends = new ArrayList<>();
//        List<Map<String, Object>> maintenanceTrends = new ArrayList<>();
//        List<Map<String, Object>> registrationTrends = new ArrayList<>();
//
//        // Populate trend data for each month
//        for (YearMonth yearMonth : last6Months) {
//            String monthLabel = yearMonth.getMonth() + " " + yearMonth.getYear();
//            LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
//            LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);
//
//            // Student trends
//            long monthlyStudentCount = studentRepository.countByCreatedAtBetween(startOfMonth, endOfMonth);
//            Map<String, Object> studentMonthData = new HashMap<>();
//            studentMonthData.put("month", monthLabel);
//            studentMonthData.put("count", monthlyStudentCount);
//            studentTrends.add(studentMonthData);
//
//            // Consultation trends
//            Map<String, Object> consultationMonthData = new HashMap<>();
//            consultationMonthData.put("month", monthLabel);
//            //consultationMonthData.put("count", consultationRepository.countByCreatedAtBetween(startOfMonth, endOfMonth));
//            consultationTrends.add(consultationMonthData);
//
//            // Maintenance trends
//            Map<String, Object> maintenanceMonthData = new HashMap<>();
//            maintenanceMonthData.put("month", monthLabel);
//            maintenanceMonthData.put("count", maintenanceIssueRepository.countByCreatedAtBetween(startOfMonth, endOfMonth));
//            maintenanceTrends.add(maintenanceMonthData);
//
//            // Registration trends - now using the sum of students and lecturers
//            long monthlyLecturerCount = lecturerRepository.countByCreatedAtBetween(startOfMonth, endOfMonth);
//            Map<String, Object> registrationMonthData = new HashMap<>();
//            registrationMonthData.put("month", monthLabel);
//            registrationMonthData.put("count", monthlyStudentCount + monthlyLecturerCount);
//            registrationTrends.add(registrationMonthData);
//        }
//
//        // Add all trend data to the result map
//        trends.put("studentTrends", studentTrends);
//        trends.put("consultationTrends", consultationTrends);
//        trends.put("maintenanceTrends", maintenanceTrends);
//        trends.put("registrationTrends", registrationTrends);
//
//        return trends;
//    }
//
//    /**
//     * Get detailed breakdown of maintenance issues by category
//     * @return Map containing counts by category
//     */
//    public List<Map<String, Object>> getMaintenanceBreakdown() {
//        Map<String, Long> categoryCountMap = maintenanceIssueRepository.findAll().stream()
//                .collect(Collectors.groupingBy(
//                        MaintenanceIssue::getStatus,
//                        Collectors.counting()
//                ));
//
//        List<Map<String, Object>> result = new ArrayList<>();
//        for (Map.Entry<String, Long> entry : categoryCountMap.entrySet()) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("statu", entry.getKey());
//            item.put("count", entry.getValue());
//            result.add(item);
//        }
//
//        return result;
//    }
//
//    /**
//     * Get detailed breakdown of consultations by lecturer
//     * @return List of maps containing lecturer name and count
//     */
//   /* public List<Map<String, Object>> getConsultationsByLecturer() {
//        Map<String, Long> lecturerCountMap = consultationRepository.findAll().stream()
//                .collect(Collectors.groupingBy(
//                        consultation -> consultation.getLecturer().getName(),
//                        Collectors.counting()
//                ));
//
//        List<Map<String, Object>> result = new ArrayList<>();
//        for (Map.Entry<String, Long> entry : lecturerCountMap.entrySet()) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("lecturer", entry.getKey());
//            item.put("count", entry.getValue());
//            result.add(item);
//        }
//
//        return result;
//    }*/
//}