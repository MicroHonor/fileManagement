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
	private String fileno;
	private String content;
	private Boolean inputno;
	private Boolean iscompared;

	// Constructors

	/** default constructor */
	public InnerCatalog() {
	}

	/** full constructor */
	public InnerCatalog(String fileno, String content, Boolean inputno,
			Boolean iscompared) {
		this.fileno = fileno;
		this.content = content;
		this.inputno = inputno;
		this.iscompared = iscompared;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
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
	public String getFileno() {
		return this.fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
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
	public Boolean getInputno() {
		return this.inputno;
	}

	public void setInputno(Boolean inputno) {
		this.inputno = inputno;
	}

	@Column(name = "ISCOMPARED", precision = 1, scale = 0)
	public Boolean getIscompared() {
		return this.iscompared;
	}

	public void setIscompared(Boolean iscompared) {
		this.iscompared = iscompared;
	}

}