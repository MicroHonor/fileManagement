package cn.lesheng.fileManage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "HOUSEHOLD_CATALOG")
public class HouseholdCatalog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237444761429143253L;
	private Long id;
	private String fileNo;				//档号
	private String organization;		//全宗单位
	private String archiveNo;			//全宗号
	private String genusNo;				//属类号
	private String kindNo;				//类别号
	private Integer fileYear;			//归档年度
	private String fileDepartment;		//归档部门
	private String retentionPeriod;		//保管期限
	private String catalogNo;			//目录号
	private String dossierNo;			//案卷号
	private String securityLevel;		//保密级别
	private String dossierTitle;		//案卷题名
	private Date beginTime;				//起始时间
	private Date endTime;				//截止时间
	private Integer piecesCount;		//共几件
	private Integer pageCount;			//共几页
	private String recorder;			//著录人
	private Date recordDate;			//著录时间
	private String remarks;				//备注
	private String location;			//存放位置
	private Date createTime;			//录入时间
	private Date updateTime = new Date();			//更新时间
	private String uuid;
	private Integer inputNo;
	private Boolean isCompared =false ;
	private String errors;

	// Constructors

	/** default constructor */
	public HouseholdCatalog() {
	}

	/** full constructor */
	public HouseholdCatalog(String fileNo, String organization,
			String archiveNo, String genusNo, String kindNo, Integer fileYear,
			String fileDepartment, String retentionPeriod, String catalogNo,
			String dossierNo, String securityLevel, String dossierTitle,
			Date beginTime, Date endTime, Integer piecesCount,
			Integer pageCount, String recorder, Date recordDate,
			String remarks, String location, Date createTime, Date updateTime,
			String uuid, Integer inputNo, Boolean isCompared) {
		this.fileNo = fileNo;
		this.organization = organization;
		this.archiveNo = archiveNo;
		this.genusNo = genusNo;
		this.kindNo = kindNo;
		this.fileYear = fileYear;
		this.fileDepartment = fileDepartment;
		this.retentionPeriod = retentionPeriod;
		this.catalogNo = catalogNo;
		this.dossierNo = dossierNo;
		this.securityLevel = securityLevel;
		this.dossierTitle = dossierTitle;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.piecesCount = piecesCount;
		this.pageCount = pageCount;
		this.recorder = recorder;
		this.recordDate = recordDate;
		this.remarks = remarks;
		this.location = location;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.uuid = uuid;
		this.inputNo = inputNo;
		this.isCompared = isCompared;
	}

	// Property accessors
	@SequenceGenerator(name = "generator" ,sequenceName="household_catalog_sequence",allocationSize=1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FILE_NO", length = 50)
	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Column(name = "ORGANIZATION", length = 50)
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "ARCHIVE_NO", length = 50)
	public String getArchiveNo() {
		return this.archiveNo;
	}

	public void setArchiveNo(String archiveNo) {
		this.archiveNo = archiveNo;
	}

	@Column(name = "GENUS_NO", length = 50)
	public String getGenusNo() {
		return this.genusNo;
	}

	public void setGenusNo(String genusNo) {
		this.genusNo = genusNo;
	}

	@Column(name = "KIND_NO", length = 50)
	public String getKindNo() {
		return this.kindNo;
	}

	public void setKindNo(String kindNo) {
		this.kindNo = kindNo;
	}

	@Column(name = "FILE_YEAR", precision = 5, scale = 0)
	public Integer getFileYear() {
		return this.fileYear;
	}

	public void setFileYear(Integer fileYear) {
		this.fileYear = fileYear;
	}

	@Column(name = "FILE_DEPARTMENT", length = 50)
	public String getFileDepartment() {
		return this.fileDepartment;
	}

	public void setFileDepartment(String fileDepartment) {
		this.fileDepartment = fileDepartment;
	}

	@Column(name = "RETENTION_PERIOD", length = 50)
	public String getRetentionPeriod() {
		return this.retentionPeriod;
	}

	public void setRetentionPeriod(String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
	}

	@Column(name = "CATALOG_NO", length = 50)
	public String getCatalogNo() {
		return this.catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	@Column(name = "DOSSIER_NO", length = 50)
	public String getDossierNo() {
		return this.dossierNo;
	}

	public void setDossierNo(String dossierNo) {
		this.dossierNo = dossierNo;
	}

	@Column(name = "SECURITY_LEVEL", length = 20)
	public String getSecurityLevel() {
		return this.securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}

	@Column(name = "DOSSIER_TITLE", length = 50)
	public String getDossierTitle() {
		return this.dossierTitle;
	}

	public void setDossierTitle(String dossierTitle) {
		this.dossierTitle = dossierTitle;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_TIME", length = 7)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "PIECES_COUNT", precision = 5, scale = 0)
	public Integer getPiecesCount() {
		return this.piecesCount;
	}

	public void setPiecesCount(Integer piecesCount) {
		this.piecesCount = piecesCount;
	}

	@Column(name = "PAGE_COUNT", precision = 5, scale = 0)
	public Integer getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Column(name = "RECORDER", length = 32)
	public String getRecorder() {
		return this.recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECORD_DATE", length = 7)
	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "LOCATION", length = 100)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Temporal(TemporalType.DATE)
	@Column(updatable=false,name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "UUID", length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "INPUT_NO", precision = 1, scale = 0)
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

	@Override
	public String toString() {
		return "HouseholdCatalog [id=" + id + ", fileNo=" + fileNo
				+ ", organization=" + organization + ", archiveNo=" + archiveNo
				+ ", genusNo=" + genusNo + ", kindNo=" + kindNo + ", fileYear="
				+ fileYear + ", fileDepartment=" + fileDepartment
				+ ", retentionPeriod=" + retentionPeriod + ", catalogNo="
				+ catalogNo + ", dossierNo=" + dossierNo + ", securityLevel="
				+ securityLevel + ", dossierTitle=" + dossierTitle
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", piecesCount=" + piecesCount + ", pageCount=" + pageCount
				+ ", recorder=" + recorder + ", recordDate=" + recordDate
				+ ", remarks=" + remarks + ", location=" + location
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", uuid=" + uuid + ", inputNo=" + inputNo + ", iscompared="
				+ isCompared + "]";
	}

//	public boolean compare(HouseholdCatalog vo) {
//		return this.id!=vo.getId()&&this.fileNo.equals(vo.getFileNo())
//				&&this.organization.equals(vo.getOrganization())&&this.archiveNo.equals(vo.getArchiveNo())
//				&&this.genusNo.equals(vo.getGenusNo())&&this.kindNo.equals(vo.getKindNo())
//				&&this.fileYear==vo.getFileYear()&&this.fileDepartment.equals(vo.getFileDepartment())
//				&&this.retentionPeriod.equals(vo.getRetentionPeriod())&&this.catalogNo.equals(vo.getCatalogNo())
//				&&this.dossierNo.equals(vo.getDossierNo())&&this.securityLevel.equals(vo.getSecurityLevel())
//				&&this.dossierTitle.equals(vo.getDossierTitle())&&this.beginTime==vo.getBeginTime()
//				&&this.endTime==vo.getEndTime()&&this.piecesCount==vo.getPiecesCount()
//				&&this.pageCount==vo.getPageCount()&&this.recorder.equals(vo.getRecorder())
//				&&this.recordDate==vo.getRecordDate()&&this.remarks.equals(vo.getRemarks())
//				&&this.location.equals(vo.getLocation())&&this.inputNo+vo.getInputNo()==3;
//	}

	public Boolean compare(Object obj) {
		HouseholdCatalog other = (HouseholdCatalog) obj;
		StringBuffer result = new StringBuffer();
		if (archiveNo == null) {
			if (other.archiveNo != null)
				return false;
		} else if (!archiveNo.equals(other.archiveNo))
			return false;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (catalogNo == null) {
			if (other.catalogNo != null)
				return false;
		} else if (!catalogNo.equals(other.catalogNo))
			return false;
		if (dossierNo == null) {
			if (other.dossierNo != null)
				return false;
		} else if (!dossierNo.equals(other.dossierNo))
			return false;
		if (dossierTitle == null) {
			if (other.dossierTitle != null)
				return false;
		} else if (!dossierTitle.equals(other.dossierTitle))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (fileDepartment == null) {
			if (other.fileDepartment != null)
				return false;
		} else if (!fileDepartment.equals(other.fileDepartment))
			return false;
		if (fileNo == null) {
			if (other.fileNo != null)
				return false;
		} else if (!fileNo.equals(other.fileNo))
			return false;
		if (fileYear == null) {
			if (other.fileYear != null)
				return false;
		} else if (!fileYear.equals(other.fileYear))
			return false;
		if (genusNo == null) {
			if (other.genusNo != null)
				return false;
		} else if (!genusNo.equals(other.genusNo))
			return false;
		if (kindNo == null) {
			if (other.kindNo != null)
				return false;
		} else if (!kindNo.equals(other.kindNo))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (pageCount == null) {
			if (other.pageCount != null)
				return false;
		} else if (!pageCount.equals(other.pageCount))
			return false;
		if (piecesCount == null) {
			if (other.piecesCount != null)
				return false;
		} else if (!piecesCount.equals(other.piecesCount))
			return false;
		if (recordDate == null) {
			if (other.recordDate != null)
				return false;
		} else if (!recordDate.equals(other.recordDate))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (retentionPeriod == null) {
			if (other.retentionPeriod != null)
				return false;
		} else if (!retentionPeriod.equals(other.retentionPeriod))
			return false;
		if (securityLevel == null) {
			if (other.securityLevel != null)
				return false;
		} else if (!securityLevel.equals(other.securityLevel))
			return false;
		return false;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	

}