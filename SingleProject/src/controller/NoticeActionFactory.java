package controller;

import action.Action;
import action.notice.apprise.NoticeAppriseAction;
import action.notice.apprise.NoticeAppriseDeleteAction;
import action.notice.apprise.NoticeAppriseModifyAction;
import action.notice.apprise.NoticeAppriseModifyProAction;
import action.notice.apprise.NoticeAppriseViewAction;
import action.notice.apprise.NoticeAppriseWriteAction;
import action.notice.apprise.NoticeAppriseWriteProAction;
import action.notice.reference.NoticeReferenceAction;
import action.notice.reference.NoticeReferenceDeleteAction;
import action.notice.reference.NoticeReferenceDownloadAction;
import action.notice.reference.NoticeReferenceModifyAction;
import action.notice.reference.NoticeReferenceModifyProAction;
import action.notice.reference.NoticeReferenceViewAction;
import action.notice.reference.NoticeReferenceWriteAction;
import action.notice.reference.NoticeReferenceWriteProAction;

public class NoticeActionFactory {
	private static NoticeActionFactory instance = new NoticeActionFactory();
	public static NoticeActionFactory getinstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		switch(command){
			case "notice_apprise" : action = new NoticeAppriseAction();						break;
			case "notice_apprise_write" : action = new NoticeAppriseWriteAction();				break;
			case "notice_apprise_write_pro" : action = new NoticeAppriseWriteProAction();		break;
			case "notice_apprise_view" : action = new NoticeAppriseViewAction();				break;
			case "notice_apprise_modify" : action = new NoticeAppriseModifyAction();			break;
			case "notice_apprise_modify_pro" : action = new NoticeAppriseModifyProAction();	break;
			case "notice_apprise_delete" : action = new NoticeAppriseDeleteAction();			break;
			
			case "notice_reference" : action = new NoticeReferenceAction();					break;
			case "notice_reference_write" : action = new NoticeReferenceWriteAction();			break;
			case "notice_reference_write_pro" : action = new NoticeReferenceWriteProAction();	break;
			case "notice_reference_view" : action = new NoticeReferenceViewAction();			break;
			case "notice_reference_modify" : action = new NoticeReferenceModifyAction();		break;
			case "notice_reference_modify_pro" : action = new NoticeReferenceModifyProAction();break;
			case "notice_reference_delete" : action = new NoticeReferenceDeleteAction();		break;
			case "notice_reference_download" : action = new NoticeReferenceDownloadAction();	break;
		}
		return action;
	}
}
