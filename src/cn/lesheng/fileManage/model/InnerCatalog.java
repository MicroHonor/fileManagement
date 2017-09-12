package cn.lesheng.fileManage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * InnerCatalog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INNER_CATALOG")
public class InnerCatalog implements java.io.Serializable {

	// Fields

	private Long id;
	private String fileNo;
	private String content;
	private Integer inputNo;
	private Boolean isCompared=false;
	private String errors;

	// Constructors

	/** default constructor */
	public InnerCatalog() {
	}

	/** full constructor */
	public InnerCatalog(String fileNo, String content, Integer inputNo,
			Boolean isCompared) {
		this.fileNo = fileNo;
		this.content = content;
		this.inputNo = inputNo;
		this.isCompared = isCompared;
	}

	// Property accessors
	@SequenceGenerator(name = "generator",sequenceName="inner_catalog_sequence",allocationSize=1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FILENO", length = 50)
	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Lob
	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "INPUTNO", precision = 1, scale = 0)
	public Integer getInputNo() {
		return this.inputNo;
	}

	public void setInputNo(Integer inputNo) {
		this.inputNo = inputNo;
	}

	@Column(name = "ISCOMPARED", precision = 1, scale = 0)
	public Boolean getIsCompared() {
		return this.isCompared;
	}

	public void setIsCompared(Boolean isCompared) {
		this.isCompared = isCompared;
	}

	@Column(name = "ERRORS",length=4000)
	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}


}