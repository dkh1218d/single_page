package controller;

import action.Action;
import action.gallery.f.GalleryFAction;
import action.gallery.f.GalleryFDeleteAction;
import action.gallery.f.GalleryFModifyAction;
import action.gallery.f.GalleryFModifyProAction;
import action.gallery.f.GalleryFViewAction;
import action.gallery.f.GalleryFWriteAction;
import action.gallery.f.GalleryFWriteProAction;
import action.gallery.sa.GallerySaAction;
import action.gallery.sa.GallerySaDeleteAction;
import action.gallery.sa.GallerySaModifyAction;
import action.gallery.sa.GallerySaModifyProAction;
import action.gallery.sa.GallerySaViewAction;
import action.gallery.sa.GallerySaWriteAction;
import action.gallery.sa.GallerySaWriteProAction;
import action.gallery.w.GalleryWAction;
import action.gallery.w.GalleryWDeleteAction;
import action.gallery.w.GalleryWModifyAction;
import action.gallery.w.GalleryWModifyProAction;
import action.gallery.w.GalleryWViewAction;
import action.gallery.w.GalleryWWriteAction;
import action.gallery.w.GalleryWWriteProAction;

public class GalleryActionFactory {
	private static GalleryActionFactory instance = new GalleryActionFactory();
	public static GalleryActionFactory getinstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		switch(command){
			case "gallery_sa": action = new GallerySaAction(); 						break;
			case "gallery_sa_write" : action = new GallerySaWriteAction();			break;
			case "gallery_sa_write_pro" : action = new GallerySaWriteProAction();	break;
			case "gallery_sa_view" : action = new GallerySaViewAction();			break;
			case "gallery_sa_modify" : action = new GallerySaModifyAction();		break;
			case "gallery_sa_modify_pro" : action = new GallerySaModifyProAction();	break;
			case "gallery_sa_delete" : action = new GallerySaDeleteAction();		break;
			
			case "gallery_w": action = new GalleryWAction();						break;
			case "gallery_w_write" : action = new GalleryWWriteAction();			break;
			case "gallery_w_write_pro" : action = new GalleryWWriteProAction();		break;
			case "gallery_w_view" : action = new GalleryWViewAction();				break;
			case "gallery_w_modify" : action = new GalleryWModifyAction();			break;
			case "gallery_w_modify_pro" : action = new GalleryWModifyProAction();	break;
			case "gallery_w_delete" : action = new GalleryWDeleteAction();			break;
			
			case "gallery_f": action = new GalleryFAction();						break;
			case "gallery_f_write" : action = new GalleryFWriteAction();			break;
			case "gallery_f_write_pro" : action = new GalleryFWriteProAction();		break;
			case "gallery_f_view" : action = new GalleryFViewAction();				break;
			case "gallery_f_modify" : action = new GalleryFModifyAction();			break;
			case "gallery_f_modify_pro" : action = new GalleryFModifyProAction();	break;
			case "gallery_f_delete" : action = new GalleryFDeleteAction();			break;
		}
		return action;
	}
}
