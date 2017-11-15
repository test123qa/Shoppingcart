package com.virtualagent.boot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by sahan on 4/9/2016.
 */
@Entity
@Table(name = "shopping_user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String userType;
	private String geoLocation;
	private String tempUserId;
	private String cookieTokenId;
	@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
	private Set<Role> roles;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date DOB;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserPurchaseInfo> purchaseInfoList = new ArrayList<UserPurchaseInfo>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<ShoppingCart> shoppingCarts = new HashSet<>();

	public User(String email, String userName, String password) {
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getTempUserId() {
		return tempUserId;
	}

	public void setTempUserId(String tempUserId) {
		this.tempUserId = tempUserId;
	}
	
	/*@ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getCookieTokenId() {
		return cookieTokenId;
	}

	public void setCookieTokenId(String cookieTokenId) {
		this.cookieTokenId = cookieTokenId;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", userType="
				+ userType + ", geoLocation=" + geoLocation + ", tempUserId=" + tempUserId + ", roles=" + roles
				+ ", DOB=" + DOB + ", cookieTokenId=" + cookieTokenId + "]";
	}

}
