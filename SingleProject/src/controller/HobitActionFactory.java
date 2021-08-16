package controller;

import action.Action;
import action.hobit.game.HobitGameAction;
import action.hobit.game.HobitGameDeleteAction;
import action.hobit.game.HobitGameModifyAction;
import action.hobit.game.HobitGameModifyProAction;
import action.hobit.game.HobitGameWriteAction;
import action.hobit.game.HobitGameWriteProAction;
import action.hobit.leisure.HobitLeisureAction;
import action.hobit.leisure.HobitLeisureDeleteAction;
import action.hobit.leisure.HobitLeisureModifyAction;
import action.hobit.leisure.HobitLeisureModifyProAction;
import action.hobit.leisure.HobitLeisureWriteAction;
import action.hobit.leisure.HobitLeisureWriteProAction;
import action.hobit.travel.HobitTravelAction;
import action.hobit.travel.HobitTravelDeleteAction;
import action.hobit.travel.HobitTravelModifyAction;
import action.hobit.travel.HobitTravelModifyProAction;
import action.hobit.travel.HobitTravelWriteAction;
import action.hobit.travel.HobitTravelWriteProAction;

public class HobitActionFactory {
	private static HobitActionFactory instance = new HobitActionFactory();
	public static HobitActionFactory getinstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		switch(command){
			case "hobit_leisure": action = new HobitLeisureAction(); 						break;
			case "hobit_leisure_write" : action = new HobitLeisureWriteAction();			break;
			case "hobit_leisure_write_pro" : action = new HobitLeisureWriteProAction();		break;
			case "hobit_leisure_delete" : action = new HobitLeisureDeleteAction();			break;
			case "hobit_leisure_modify" : action = new HobitLeisureModifyAction();			break;
			case "hobit_leisure_modify_pro" : action = new HobitLeisureModifyProAction();	break;
			
			case "hobit_game": action = new HobitGameAction(); 								break;
			case "hobit_game_write" : action = new HobitGameWriteAction();					break;
			case "hobit_game_write_pro" : action = new HobitGameWriteProAction();			break;
			case "hobit_game_delete" : action = new HobitGameDeleteAction();				break;
			case "hobit_game_modify" : action = new HobitGameModifyAction();				break;
			case "hobit_game_modify_pro" : action = new HobitGameModifyProAction();			break;
			
			case "hobit_travel": action = new HobitTravelAction(); 							break;
			case "hobit_travel_write" : action = new HobitTravelWriteAction();				break;
			case "hobit_travel_write_pro" : action = new HobitTravelWriteProAction();		break;
			case "hobit_travel_delete" : action = new HobitTravelDeleteAction();			break;
			case "hobit_travel_modify" : action = new HobitTravelModifyAction();			break;
			case "hobit_travel_modify_pro" : action = new HobitTravelModifyProAction();		break;
		}
		return action;
	}
}
