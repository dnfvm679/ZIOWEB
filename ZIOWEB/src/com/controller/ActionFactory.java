package com.controller;

import com.action.Action;
import com.action.BackAction;
import com.action.MainAction;
import com.action.member.AddUserAction;
import com.action.member.AddUserForm;
import com.action.member.DeleteUserAction;
import com.action.member.LoginAction;
import com.action.member.LoginFormAction;
import com.action.member.LogoutAction;
import com.action.member.SearchUserAction;
import com.action.member.UpdateUserAction;
import com.action.member.UpdateUserForm;
import com.action.member.UserManagementForm;
import com.action.member.ViewUserAction;
import com.action.request.DeleteRequestAction;
import com.action.request.FinishRequestAction;
import com.action.request.FinishRequestForm;
import com.action.request.GetPeriodRequestNum;
import com.action.request.GetRequestListAction;
import com.action.request.ProcessChangeAction;
import com.action.request.ProcessChangeForm2;
import com.action.request.UpdateRequestAction;
import com.action.request.UpdateRequestForm;
import com.action.request.ViewRequestAction;
import com.action.request.WriteRequestAction;
import com.action.request.WriteRequestForm;
import com.action.request.getRequestNum;

public class ActionFactory {
	private static ActionFactory af = new ActionFactory();

	private ActionFactory() {
	};

	public static ActionFactory getInstance() {
		return af;
	}

	public Action getAction(String cmd) {
		Action action = null;
		switch (cmd) {
		case "main":
			action = new MainAction();
			break;
		case "loginform":
			action = new LoginFormAction(); // LoginPage
			break;
		case "loginAction": // Execute Login
			action = new LoginAction();
			break;
		case "logout": // Log out
			action = new LogoutAction();
			break;
		case "userManagement": // User Management
			action = new UserManagementForm();
			break;
		case "addUser": // Create New user
			action = new AddUserAction();
			break;
		case "addUserForm": // show new user input form
			action = new AddUserForm();
			break;
		case "userSearch": // search specific user
			action = new SearchUserAction();
			break;
		case "viewUser": // show user's detail
			action = new ViewUserAction();
			break;
		case "updateUserForm": // show update form for update user info
			action = new UpdateUserForm();
			break;
		case "updateUser": // update user's info
			action = new UpdateUserAction();
			break;
		case "deleteUser": // User delete Action
			action = new DeleteUserAction();
			break;
		case "back": // go to previous page
			action = new BackAction();
			break;
		case "getRequestList": // get Request List
			action = new GetRequestListAction();
			break;
		case "writeRequestForm": // go to Insert Form about new Request
			action = new WriteRequestForm();
			break;
		case "writeRequest": // write Action for new Request
			action = new WriteRequestAction();
			break;
		case "viewRequest": // Show Detail about specific Request
			action = new ViewRequestAction();
			break;
		case "updateRequestForm": // insert form for update
			action = new UpdateRequestForm();
			break;
		case "updateRequest": // Update Request
			action = new UpdateRequestAction();
			break;
		case "deleteRequest": // Request delete Action
			action = new DeleteRequestAction();
			break;
		case "processChangeForm":
			action = new ProcessChangeForm2();
			break;
		case "processChange":
			action = new ProcessChangeAction();
			break;
		case "getRequestNum":
			action = new getRequestNum();
			break;

		case "finishRequestForm":
			action = new FinishRequestForm();
			break;
		case "finishRequest":
			action = new FinishRequestAction();
			break;
		case "getPeriodRequestNum":
			action = new GetPeriodRequestNum();
			break;
		default:
			break;
		}
		return action;
	}

}
