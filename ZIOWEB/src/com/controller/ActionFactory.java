package com.controller;

import com.action.Action;
import com.action.AddUserAction;
import com.action.AddUserForm;
import com.action.LoginAction;
import com.action.LoginFormAction;
import com.action.LogoutAction;
import com.action.MainAction;
import com.action.SearchUserAction;
import com.action.TestAction;
import com.action.UserAdminForm;

public class ActionFactory {
	private static ActionFactory af = new ActionFactory();
	private ActionFactory() {};
	public static ActionFactory getInstance() {
		return af;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("main")) {
			action = new MainAction(); // MainPage
		} else if(cmd.equals("loginform")) {
			action = new LoginFormAction(); // LoginPage 
		} else if(cmd.equals("loginAction")) {
			action = new LoginAction();
		} else if(cmd.equals("logout")) {
			action = new LogoutAction();
		} else if(cmd.equals("useradmin")) {
			action = new UserAdminForm();
		} else if(cmd.equals("addUser")) {
			action = new AddUserAction();
		} else if(cmd.equals("addUserForm")) {
			action = new AddUserForm();
		} else if(cmd.equals("userSearch")) {
			action = new SearchUserAction();
		}
		return action;
	}

}
