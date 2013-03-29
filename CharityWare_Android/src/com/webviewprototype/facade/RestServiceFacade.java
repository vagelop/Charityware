package com.webviewprototype.facade;

import java.util.ArrayList; 
import java.util.List;
import java.util.Map;

import env.Entities.Charity;
import env.Entities.FilledForm;
import env.Entities.Form;
import env.Entities.FormFields;
import env.Entities.User;

public interface RestServiceFacade {
	public List<Form> getFormEntities(String username);
	public User validateUser(String username, String password);
    public Boolean setFilledForm( List<FilledForm> forms);
    public List<Charity> getCharities ();
}
