package cn.lesheng.fileManage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "T_FILE")
public class TFile implements java.io.Serializable,Comparable<TFile> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 109836956446414963L;
	private Long id;
	private String documentUUID;
	private String fileName;
	private Long fileSize;
	private String filePath;

	// Constructors

	/** default constructor */
	public TFile() {
	}

	/** full constructor */
	public TFile(String documentUUID, String fileName, Long fileSize,
			String filePath) {
		this.documentUUID = documentUUID;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
	}
	
	public TFile(MultipartFile filedata,String documentUUID){
		this.documentUUID = documentUUID;
		this.fileName = filedata.getName();
		this.fileSize=filedata.getSize();
	}

	// Property accessors
	@SequenceGenerator(name = "generator",sequenceName="T_FILE_SEQUENCE",allocationSize=1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DOCUMENT_UUID", length = 50)
	public String getDocumentUUID() {
		return this.documentUUID;
	}

	public void setDocumentUUID(String documentUUID) {
		this.documentUUID = documentUUID;
	}

	@Column(name = "FILE_NAME", length = 100)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_SIZE", precision = 10)
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_PATH", length = 250)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ ((fileSize == null) ? 0 : fileSize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TFile other = (TFile) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileSize == null) {
			if (other.fileSize != null)
				return false;
		} else if (!fileSize.equals(other.fileSize))
			return false;
		return true;
	}


	@Override
	public int compareTo(TFile o) {
		int result = this.fileName.compareTo(o.getFileName());
		if(result==0){
			result = this.fileSize.compareTo(o.getFileSize());
		}
		return result;
	}

}