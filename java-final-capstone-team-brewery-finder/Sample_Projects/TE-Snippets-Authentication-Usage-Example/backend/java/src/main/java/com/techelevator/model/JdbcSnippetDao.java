package com.techelevator.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSnippetDao implements SnippetDao {
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcSnippetDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public List<Snippet> getSnippets(Long userid) {
        List<Snippet> snippets = new ArrayList<Snippet>();
        String sql = "SELECT id,description,filename,sourceCode,tags FROM snippets where user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userid);

        while(results.next()) {
            Snippet snippet = mapResultToSnippet(results);
            snippets.add(snippet);
        }

        return snippets;
	}
	
	/**
	 * Save a new snippet to the database
	 * @param snippet The new snippet to save
	 */
	public Snippet save(Snippet snippet) {
        long newId = jdbcTemplate.queryForObject("INSERT INTO snippets(description, filename, tags, sourcecode, user_id) "
        		+ "VALUES (?, ?, ?, ?, ?) RETURNING id", Long.class, snippet.getDescription(), snippet.getFilename(), snippet.getTags(), snippet.getSourceCode(), snippet.getUserId());
        snippet.setUserId(newId);
        return snippet;
	}
	
	/**
	 * Delete the snippet with the given id.
	 */
	public void delete(Long id) {
		String sql = "DELETE FROM snippets where id = ?";
		jdbcTemplate.update(sql,id);
	}

    private Snippet mapResultToSnippet(SqlRowSet results) {
    	Snippet snippet = new Snippet();
    	snippet.setId(results.getLong("id"));
    	snippet.setDescription(results.getString("description"));
    	snippet.setFilename(results.getString("filename"));
    	snippet.setSourceCode(results.getString("sourcecode"));
    	snippet.setTags(results.getString("tags"));
        return snippet;
    }

	public Snippet findById(Long id) {
		String sql = "SELECT id, description, filename, tags, sourcecode, user_id FROM snippets where id = ?";
		return jdbcTemplate.queryForObject(sql,  new SnippetMapper(), id);
	}
	
	public void update(Long id, Snippet snippet) {
		jdbcTemplate.update("UPDATE snippets SET filename=?, description=?, tags=?, sourcecode=? WHERE id=?",
				snippet.getFilename(),snippet.getDescription(),snippet.getTags(),snippet.getSourceCode(),
				snippet.getId());
	}
	
	private static final class SnippetMapper implements RowMapper<Snippet> {
        public Snippet mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Snippet snippet = new Snippet();
        	snippet.setId(rs.getLong("id"));
        	snippet.setDescription(rs.getString("description"));
        	snippet.setFilename(rs.getString("filename"));
        	snippet.setSourceCode(rs.getString("sourcecode"));
        	snippet.setTags(rs.getString("tags"));
        	snippet.setUserId(rs.getLong(("user_id")));
            return snippet;
        }
    }
	
}
