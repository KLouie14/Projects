package com.techelevator.model;

import java.util.List;

public interface SnippetDao {
	
	public List<Snippet> getSnippets(Long userid);
	
	public Snippet save(Snippet snippet);
	
	public void delete(Long id);
	
	public Snippet findById(Long id);
	
	public void update(Long id, Snippet snippet);

}
