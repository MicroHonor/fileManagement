package cn.lesheng.fileManage.model;
// default package


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Image entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name="T_IMAGE"
)

public class Image  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String fileNo;
     private String imageName;
     private String imagePath;


    // Constructors

    /** default constructor */
    public Image() {
    }

    
    /** full constructor */
    public Image(String fileNo, String imageName, String imagePath) {
        this.fileNo = fileNo;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator",sequenceName="T_IMAGE_SEQUENCE",allocationSize=1)
    @Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    @Column(name="ID", unique=true, nullable=false, scale=0)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="FILENO", length=50)

    public String getFileNo() {
        return this.fileNo;
    }
    
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }
    
    @Column(name="IMAGE_NAME", length=100)

    public String getImageName() {
        return this.imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    @Column(name="IMAGE_PATH", length=250)

    public String getImagePath() {
        return this.imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}