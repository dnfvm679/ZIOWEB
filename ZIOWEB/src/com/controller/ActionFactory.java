package com.controller;

import com.action.Action;
import com.action.BackAction;
import com.action.MainAction;
import com.action.board.DeleteIssueAction;
import com.action.board.GetBoardListAction;
import com.action.board.UpdateIssueAction;
import com.action.board.UpdateIssueForm;
import com.action.board.ViewBoardAction;
import com.action.board.WriteIssueAction;
import com.action.board.WriteIssueForm;
import com.action.member.AddUserAction;
import com.action.member.AddUserForm;
import com.action.member.DeleteUserAction;
import com.action.member.LoginAction;
import com.action.member.LoginFormAction;
import com.action.member.LogoutAction;
import com.action.member.SearchUserAction;
import com.action.member.UpdateUserAction;
import com.action.member.UpdateUserForm;
import com.action.member.UserAdminForm;
import com.action.member.ViewUserAction;

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
		case "loginAction":
			action = new LoginAction();
			break;
		case "logout": // Log out
			action = new LogoutAction();
			break;
		case "useradmin": // User Management
			action = new UserAdminForm();
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
		case "getBoardList": // get Board List
			action = new GetBoardListAction();
			break;
		case "writeIssueForm": // go to Insert Form about new Issue
			action = new WriteIssueForm();
			break;
		case "writeIssue": // write Action for new Issue
			action = new WriteIssueAction();
			break;
		case "viewBoard": // Show Detail about specific issue
			action = new ViewBoardAction();
			break;
		case "updateIssueForm": // insert form for update
			action = new UpdateIssueForm();
			break;
		case "updateIssue": // Update Issue
			action = new UpdateIssueAction();
			break;
		case "deleteIssue": // Issue delete Action
			action = new DeleteIssueAction();
			break;
		default:
			break;
		}
		return action;
	}

}
