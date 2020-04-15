package com.techelevator.npgeek.model.survey;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDao implements SurveyDao {

	JdbcTemplate jdbcTemplate;
	
	/* ctor taking in datasource object have Spring MVC Dependency Inject it via @Autowired */
	
	@Autowired
	public JDBCSurveyDao (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/*saveSurvey method - able to get values from object that's passed in to insert into the database */
	@Override
	public void saveSurvey(Survey survey) {
		String sqlSaveSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) values(?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveSurvey, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
	}
}
