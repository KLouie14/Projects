package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.JdbcSnippetDao;
import com.techelevator.model.Snippet;
import com.techelevator.model.SnippetCreationException;

@RestController
@RequestMapping("/api/snippets")
@CrossOrigin
public class SnippetController {
	
    private AuthProvider authProvider;
    
    private JdbcSnippetDao snippetDao;
    
    @Autowired
    public SnippetController(AuthProvider authProvider, JdbcSnippetDao snippetDao) {
    	this.authProvider = authProvider;
    	this.snippetDao = snippetDao;
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public List<Snippet> list() throws UnauthorizedException {
        if(!authProvider.userHasRole(new String[] {"user"})) {
            throw new UnauthorizedException();
        }
        return snippetDao.getSnippets(authProvider.getCurrentUser().getId());
    }
    
    @RequestMapping(path="/", method=RequestMethod.POST)
    public String save(@Valid @RequestBody Snippet snippet, BindingResult result) throws SnippetCreationException {
    	if(result.hasErrors()) {
            String errorMessages = "";
            for(ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new SnippetCreationException(errorMessages);
    	}
    	snippet.setUserId(authProvider.getCurrentUser().getId());
    	snippetDao.save(snippet);
    	return "{\"success\":true}";
    }

    @RequestMapping(path="/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id)  throws SnippetNotFoundException {
    	Snippet snippet = snippetDao.findById(id);
    	if(snippet != null && snippet.getUserId() == authProvider.getCurrentUser().getId()) {
    		snippetDao.delete(id);
    	} else {
    		throw new SnippetNotFoundException(id, "Snippet not found!");
    	}
    }
    
    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    public Snippet getSnippet(@PathVariable Long id) throws SnippetNotFoundException {
    	Snippet snippet = snippetDao.findById(id);
    	if(snippet != null && snippet.getUserId() == authProvider.getCurrentUser().getId()) {
    		return snippet;
    	} else {
    		throw new SnippetNotFoundException(id, "Snippet not found!");
    	}
    }
    
    @RequestMapping(path="/{id}", method=RequestMethod.PUT)
    public void update(@PathVariable Long id,@Valid @RequestBody Snippet snippetForm, BindingResult result) throws SnippetNotFoundException {
    	Snippet snippet = snippetDao.findById(id);
    	if(snippet != null && snippet.getUserId() == authProvider.getCurrentUser().getId()) {
    		snippetDao.update(id,snippetForm);
    	} else {
    		throw new SnippetNotFoundException(id, "Snippet not found!");
    	}
    }
}
