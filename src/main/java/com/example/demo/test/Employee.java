package com.example.demo.test;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_tbl")
public class Employee {
    @Id
    @Column(name="empno")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String empname;
    private boolean isChecked;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmpname() {
        return empname;
    }
    public void setEmpname(String empname) {
        this.empname = empname;
    }
    public boolean getIsChecked() {return isChecked;}
    public void setIsChecked(boolean b) {this.isChecked = b;}
}