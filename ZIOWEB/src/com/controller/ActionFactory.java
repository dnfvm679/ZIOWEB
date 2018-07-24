package com.controller;

import com.action.Action;
import com.action.LoginAction;
import com.action.LoginFormAction;
import com.action.TestAction;

public class ActionFactory {
	private static ActionFactory af = new ActionFactory();
	private ActionFactory() {};
	public static ActionFactory getInstance() {
		return af;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("test")) {
			action = new TestAction(); // TestPage
		} else if(cmd.equals("loginform")) {
			action = new LoginFormAction(); // LoginPage 
		} else if(cmd.equals("loginAction")) {
			action = new LoginAction();
		}
		return action;
	}

}
