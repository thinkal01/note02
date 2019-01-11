package com.note.common.freemarker.crud.orm;

import java.util.Date;
import javax.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class User {
	
	private Integer id;
	@NotEmpty
	private String account;
	@Email
	@NotEmpty
	private String email;
	private Integer sex;
	private Position position;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdDate;
	@NumberFormat(pattern="###,###.#")
	@DecimalMin("2000")
	private Double salary;
	
	public User() {
	}
	public User(Integer id, String account, String email, Integer sex,
			Position position, Date createdDate, Double salary) {
		this.id = id;
		this.account = account;
		this.email = email;
		this.sex = sex;
		this.position = position;
		this.createdDate = createdDate;
		this.salary = salary;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", email=" + email
				+ ", sex=" + sex + ", position=" + position + ", createdDate="
				+ createdDate + ", salary=" + salary + "]";
	}

}
