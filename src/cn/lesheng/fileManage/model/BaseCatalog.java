package cn.lesheng.fileManage.model;


import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseCatalog implements java.io.Serializable {
	// Fields


		private String fileNo;
		private String content;
		private Integer inputNo;
		private Boolean isCompared=false;
		private String errors;

		// Constructors

		/** default constructor */
		public BaseCatalog() {
		}

		/** full constructor */
		public BaseCatalog(String fileNo, String content, Integer inputNo,
				Boolean isCompared,String errors) {
			this.fileNo = fileNo;
			this.content = content;
			this.inputNo = inputNo;
			this.isCompared = isCompared;
			this.errors = errors;
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
