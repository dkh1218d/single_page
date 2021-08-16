package controller;

import action.Action;
import action.about.introduce.AboutIntroduceAction;
import action.about.introduce.AboutIntroduceAnnouncementAction;
import action.about.introduce.AboutIntroduceAnnouncementProAction;
import action.about.introduce.AboutIntroduceDeleteAction;
import action.about.introduce.AboutIntroduceEducationAction;
import action.about.introduce.AboutIntroduceEducationProAction;
import action.about.introduce.AboutIntroduceJobAction;
import action.about.introduce.AboutIntroduceJobProAction;
import action.about.introduce.AboutIntroducePhotoAction;
import action.about.introduce.AboutIntroducePhotoProAction;

public class AboutActionFactory {
	private static AboutActionFactory instance = new AboutActionFactory();
	public static AboutActionFactory getinstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		switch(command){
			case "about_introduce" : action = new AboutIntroduceAction();									break;
			case "about_introduce_announcement" : action = new AboutIntroduceAnnouncementAction();			break;
			case "about_introduce_announcement_pro" : action = new AboutIntroduceAnnouncementProAction();	break;
			case "about_introduce_photo" : action = new AboutIntroducePhotoAction();						break;
			case "about_introduce_photo_pro" : action = new AboutIntroducePhotoProAction();					break;
			case "about_introduce_education" : action = new AboutIntroduceEducationAction();				break;
			case "about_introduce_education_pro" : action = new AboutIntroduceEducationProAction();			break;
			case "about_introduce_job" : action = new AboutIntroduceJobAction();							break;
			case "about_introduce_job_pro" : action = new AboutIntroduceJobProAction();						break;
			case "about_introduce_del" : action = new AboutIntroduceDeleteAction();							break;
		}
		return action;
	}
}
