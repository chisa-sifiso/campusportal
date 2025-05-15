package com.smartcampus.campusportal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Module")
public class CourseModule {
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getModuleID() {
        return moduleID;
    }

    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Id
    @Column(name = "ModuleID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleID;

    private String moduleName;

    private String moduleCode;
}
