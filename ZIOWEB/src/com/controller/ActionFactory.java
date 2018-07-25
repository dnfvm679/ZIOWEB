package com.controller;

import com.action.Action;
import com.action.AddUserAction;
import com.action.AddUserForm;
import com.action.BackAction;
import com.action.DeleteAction;
import com.action.LoginAction;
import com.action.LoginFormAction;
import com.action.LogoutAction;
import com.action.MainAction;
import com.action.SearchUserAction;
import com.action.UpdateUserAction;
import com.action.UpdateUserForm;
import com.action.UserAdminForm;
import com.action.ViewUserAction;

public class ActionFactory {
	private static ActionFactory af = new ActionFactory();

	private ActionFactory() {
	};

	public static ActionFactory getInstance() {
		return af;
	}

	public Action getAction(String cmd) {
		Action action = null;
		if (cmd.equals("main")) {
			action = new MainAction(); // MainPage
		} else if (cmd.equals("loginform")) {
			action = new LoginFormAction(); // LoginPage
		} else if (cmd.equals("loginAction")) {
			action = new LoginAction();
		} else if (cmd.equals("logout")) { // Log out
			action = new LogoutAction();
		} else if (cmd.equals("useradmin")) { // User Management
			action = new UserAdminForm();
		} else if (cmd.equals("addUser")) { // Create New user
			action = new AddUserAction();
		} else if (cmd.equals("addUserForm")) { // show new user input form
			action = new AddUserForm();
		} else if (cmd.equals("userSearch")) { // search specific user
			action = new SearchUserAction();
		} else if (cmd.equals("viewUser")) { // show user's detail
			action = new ViewUserAction();
		} else if (cmd.equals("updateUserForm")) { // show update form for update user info
			action = new UpdateUserForm();
		} else if (cmd.equals("updateUser")) { // update user's info
			action = new UpdateUserAction();
		} else if (cmd.equals("deleteUser")) {
			action = new DeleteAction();
		} else if (cmd.equals("back")) { // go to previous page
			action = new BackAction();
		}
		return action;
	}

}
