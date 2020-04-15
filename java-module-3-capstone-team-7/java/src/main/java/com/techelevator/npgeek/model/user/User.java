package com.techelevator.npgeek.model.user;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * User
 */
public class User {
    @NotBlank(message = "Username is required")
    @Length(min=4, max=20, message="Username must be between 4-20 characters.")
    private String username;

    private long id;

    @NotBlank(message = "Password is required")
    @Length(min=4, max=20, message="Password must be between 8-20 characters.")
    private String password;
    private String confirmPassword;

    private boolean passwordMatching;
    
    @NotBlank(message="Please enter password hint.")
    @Length(min=4, max=20, message="Password hint must be between 5-30 characters.")
    private String passwordHint;
    
    @NotBlank(message="Email address is required")
	@Email(message="Email must be a valid email address")
    private String email;
    
    

    public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}