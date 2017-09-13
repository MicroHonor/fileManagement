package cn.lesheng.fileManage.model;


import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * InnerCatalog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INNER_CATALOG")
public class InnerCatalog  extends BaseCatalog {

	private Long id;
	private Integer imagesCount=0;
	
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
	@Column(name = "IMAGES_COUNT", precision = 5, scale = 0)
	public Integer getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(Integer imagesCount) {
		this.imagesCount = imagesCount;
	}
	
}