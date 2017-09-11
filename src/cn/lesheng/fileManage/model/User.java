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
 * User entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name="T_USER"
)

public class User  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String name;
     private String username;
     private String password;
     private Integer orderType;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String name, String username, String password, Integer orderType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.orderType = orderType;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="ID", unique=true, nullable=false, scale=0)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="NAME", length=32)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="USERNAME", length=32)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="PASSWORD", length=32)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="ORDER_TYPE", precision=1, scale=0)

    public Integer getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
   








}