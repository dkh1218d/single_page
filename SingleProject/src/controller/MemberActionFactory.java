package controller;

import action.Action;
import action.member.MemberEmailAction;
import action.member.MemberEmailProAction;
import action.member.MemberIdSearchAction;
import action.member.MemberIdSearchProAction;
import action.member.MemberId_overlapAction;
import action.member.MemberId_overlapProAction;
import action.member.MemberLoginProAction;
import action.member.MemberLogoutAction;
import action.member.MemberModifyAction;
import action.member.MemberModifyProAction;
import action.member.MemberNewPasswdAction;
import action.member.MemberNewPasswdProAction;
import action.member.MemberPwResetAction;
import action.member.MemberPwResetProAction;
import action.member.MemberPwResetTempAction;
import action.member.MemberSeqAction;
import action.member.MemberSignUpAction;
import action.member.MemberSignUpProAction;
import action.member.MemberTempAction;

public class MemberActionFactory {
	private static MemberActionFactory instance = new MemberActionFactory();
	public static MemberActionFactory getinstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		switch(command){
			case "member_signup" : action = new MemberSignUpAction();					break;
			case "member_signup_pro" : action = new MemberSignUpProAction();			break;
			case "member_login_pro" : action = new MemberLoginProAction();				break;
			case "member_logout" : action = new MemberLogoutAction();					break;
			case "member_modify" : action = new MemberModifyAction();					break;
			case "member_modify_pro" : action = new MemberModifyProAction();			break;
			case "member_id_overlap" : action = new MemberId_overlapAction();			break;
			case "member_id_overlap_pro" : action = new MemberId_overlapProAction();	break;
			case "member_email" : action = new MemberEmailAction();						break;
			case "member_email_pro" : action = new MemberEmailProAction();				break;
			case "member_temp_check" : action = new MemberTempAction();					break;
			case "member_securitynumber" : action = new MemberSeqAction();				break;
			case "member_id_search" : action = new MemberIdSearchAction();				break;
			case "member_id_search_pro" : action = new MemberIdSearchProAction();		break;
			case "member_pw_reset" : action = new MemberPwResetAction();				break;
			case "member_pw_reset_pro" : action = new MemberPwResetProAction();			break;
			case "member_pw_reset_temp" : action = new MemberPwResetTempAction();		break;
			case "member_new_pw" : action = new MemberNewPasswdAction();				break;
			case "member_new_pw_pro" : action = new MemberNewPasswdProAction();			break;
			
		}
		return action;
	}
}
