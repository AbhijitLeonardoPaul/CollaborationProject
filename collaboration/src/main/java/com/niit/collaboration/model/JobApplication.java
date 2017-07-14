package com.niit.collaboration.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
public class JobApplication extends BaseDomain implements Serializable {

	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_AUTO_JOB_APP_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
private int id;
	
	private String userid;
	
	private int Jobid;
	
	private String status;	// R = Reject, C = Call for Interview, S = Selected
	
	private String remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getJobid() {
		return Jobid;
	}

	public void setJobid(int jobid) {
		Jobid = jobid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
