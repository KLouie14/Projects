package com.techelevator.npgeek.model.user;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.npgeek.authentication.PasswordHasher;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    /**
     * Create a new user dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for users.
     *
     * @param dataSource an SQL data source
     * @param passwordHasher an object to salt and hash passwords
     */
    @Autowired
    public JdbcUserDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }

    /**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     * @param userName the user name to give the new user
     * @param password the user's password
     * @param role the user's role
     * @return the new user
     */
    @Override
    public User saveUser(String userName, String password, String passwordHint, String email, LocalDateTime lastLogin) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO users(username, passwordhash, salt, passwordhint, emailaddress, lastlogin) VALUES (?, ?, ?, ?, ?, ?) RETURNING userid", Long.class,
                userName, hashedPassword, saltString, passwordHint, email, lastLogin);

        User newUser = new User();
        newUser.setId(newId);
        newUser.setUsername(userName);

        return newUser;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(newPassword, salt);
        String saltString = new String(Base64.encode(salt));

        jdbcTemplate.update("UPDATE users SET passwordhash=?, salt=? WHERE userid=?", hashedPassword, saltString, user.getId());
    }

    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    @Override
    public User getValidUserWithPassword(String userName, String password) {
        String sqlSearchForUser = "SELECT * FROM users WHERE UPPER(username) = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
        if (results.next()) {
            String storedSalt = results.getString("salt");
            String storedPassword = results.getString("passwordhash");
            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
            if (storedPassword.equals(hashedPassword)) {
                return mapResultToUser(results);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Get all of the users from the database.
     * @return a List of user objects
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sqlSelectAllUsers = "SELECT * FROM users";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);

        while (results.next()) {
            User user = mapResultToUser(results);
            users.add(user);
        }

        return users;
    }
    
    
	@Override
	public LocalDateTime getLastLogin() {
		
		try {
			String sqlGetLastLogin = "SELECT lastlogin from users order by lastlogin desc limit 1";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLastLogin);
			while(results.next()) {
			return results.getTimestamp("lastlogin").toLocalDateTime();
			}
		}
		catch(Exception e) {
			return LocalDateTime.now();
		}
		return LocalDateTime.now();
	}
    

    private User mapResultToUser(SqlRowSet results) {
        User user = new User();
        user.setId(results.getLong("userid"));
        user.setUsername(results.getString("username"));
        user.setEmail(results.getString("emailaddress"));
        user.setPasswordHint(results.getString("passwordhint"));
        return user;
    }

	@Override
	public String getPasswordHintByUsername(String userName) {
		String hint = "";
		String sqlGetPasswordHint = "Select passwordhint from users where UPPER(username) =  ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPasswordHint, userName.toUpperCase());
		while (results.next()) {
			hint = results.getString("passwordhint");
		}
		return hint;
		
				
	}
	}




