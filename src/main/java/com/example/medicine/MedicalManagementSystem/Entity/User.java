package com.example.medicine.MedicalManagementSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@NotNull
	@Column(name = "userId")
	private int userId;
	@NotNull
	@Size(min = 2, message = "First name must have atleast 2 characters")
	@Column(name = "firstName")
	private String firstName;
	@NotNull
	@Size(min = 2, message = "First name must have atleast 2 characters")
	@Column(name = "lastName")
	private String lastName;
	@NotNull
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" , message = "Enter password containing Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:")
	@Column(name = "password")
	private String password;
	@NotBlank
	@Email
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format")
	@Column(name = "mailId")
	private String mailId;
	@NotNull
	@Column(name = "mobileNo")
	private String mobileNo;
	@NotNull
	@Column(name = "address")
	private String address;

	public User() {
		super();
	}

	public User(int userId, String firstName, String lastName, String password, String mailId, String mobileNo,
			String address) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.mailId = mailId;
		this.mobileNo = mobileNo;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", mailId=" + mailId + ", mobileNo=" + mobileNo + ", address=" + address + "]";
	}

}
