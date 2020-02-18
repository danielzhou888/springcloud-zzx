package com.zzx.tools.example;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TestEntity implements Serializable{

	private static final long serialVersionUID = -3973702142471853923L;
	private Long id;
	private String gbNo;
	private String gbName;
	private String gbType;
	private String icd10No;
	private String icd10Name;
	private String createdBy;
	private String changedBy;
	private Date createdAt;
	private Date changedAt;
	private Integer status;
	private String icd10HelpNo;
	private Integer version;
}
