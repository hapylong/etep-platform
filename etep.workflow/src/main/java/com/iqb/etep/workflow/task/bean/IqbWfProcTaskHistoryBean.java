
package com.iqb.etep.workflow.task.bean;

import java.io.Serializable;

public class IqbWfProcTaskHistoryBean implements Serializable{

    private static final long serialVersionUID = -7770104776972370654L;

    private String procInstId;
    private String procCtaskid;
    private String procCtaskcode;
    private String procCtaskname;
    private String procTaskAssignee;
    private String procLicensor;
    private String procTaskCommitter;
    private String procTaskCommittime;
    private String procTaskAssigntime;
    private String procTaskEndtime;
    private String procTaskApprStatus;
    private String procTaskApprOpinion;

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcCtaskid() {
        return procCtaskid;
    }

    public void setProcCtaskid(String procCtaskid) {
        this.procCtaskid = procCtaskid;
    }

    public String getProcCtaskcode() {
        return procCtaskcode;
    }

    public void setProcCtaskcode(String procCtaskcode) {
        this.procCtaskcode = procCtaskcode;
    }

    public String getProcCtaskname() {
        return procCtaskname;
    }

    public void setProcCtaskname(String procCtaskname) {
        this.procCtaskname = procCtaskname;
    }

    public String getProcTaskAssignee() {
        return procTaskAssignee;
    }

    public void setProcTaskAssignee(String procTaskAssignee) {
        this.procTaskAssignee = procTaskAssignee;
    }

    public String getProcLicensor() {
        return procLicensor;
    }

    public void setProcLicensor(String procLicensor) {
        this.procLicensor = procLicensor;
    }

    public String getProcTaskCommitter() {
        return procTaskCommitter;
    }

    public void setProcTaskCommitter(String procTaskCommitter) {
        this.procTaskCommitter = procTaskCommitter;
    }

    public String getProcTaskCommittime() {
        return procTaskCommittime;
    }

    public void setProcTaskCommittime(String procTaskCommittime) {
        this.procTaskCommittime = procTaskCommittime;
    }

    public String getProcTaskAssigntime() {
        return procTaskAssigntime;
    }

    public void setProcTaskAssigntime(String procTaskAssigntime) {
        this.procTaskAssigntime = procTaskAssigntime;
    }

    public String getProcTaskEndtime() {
        return procTaskEndtime;
    }

    public void setProcTaskEndtime(String procTaskEndtime) {
        this.procTaskEndtime = procTaskEndtime;
    }

    public String getProcTaskApprStatus() {
        return procTaskApprStatus;
    }

    public void setProcTaskApprStatus(String procTaskApprStatus) {
        this.procTaskApprStatus = procTaskApprStatus;
    }

    public String getProcTaskApprOpinion() {
        return procTaskApprOpinion;
    }

    public void setProcTaskApprOpinion(String procTaskApprOpinion) {
        this.procTaskApprOpinion = procTaskApprOpinion;
    }
}
