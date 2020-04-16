package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.brewery.Brewery;
import com.techelevator.model.brewery.JDBCBreweryDAO;

public class JDBCBreweryDAOIntegrationTest extends DAOIntegrationTest {
	
	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private static final String TEST_BREWERY = "MUSTACHE BREWING CO";
	private JDBCBreweryDAO breweryDao;
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/capstone");
		dataSource.setUsername("postrgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		
		//before every test, INSERT a test row using the TEST_COUNTRY before every test
		
		String sqlInsertBrewery = "INSERT INTO brewery "
								+ "(brewery_name, street_address, web_address, phone_number, hours_of_operation, history) "
								+ "VALUES (?, '123 Triskett Road', 'mustachebrewing.com', '555-555-5555', 'Mon-Sun 11-12', 'Est. 1919')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	//instantiate the JdbcTemplate so we can test
		
		jdbcTemplate.update(sqlInsertBrewery, TEST_BREWERY);  //actually INSERT the test data
		
		breweryDao = new JDBCBreweryDAO(dataSource);	//instantiate the DAO containing the methods to test
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test		//testing find all parks method to ensure it returns a list of all parks
	public void returns_all_breweries() {
		List<Brewery> results = breweryDao.getAllBreweries();
		assertNotNull(results);
		assertEquals(11,results.size());
	}

}
