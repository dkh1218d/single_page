package controller;

import action.Action;
import action.board.free.BoardFreeAction;
import action.board.free.BoardFreeDeleteAction;
import action.board.free.BoardFreeModifyAction;
import action.board.free.BoardFreeModifyProAction;
import action.board.free.BoardFreeRecommentAction;
import action.board.free.BoardFreeReplyAction;
import action.board.free.BoardFreeReplyDeleteAction;
import action.board.free.BoardFreeViewAction;
import action.board.free.BoardFreeWriteAction;
import action.board.free.BoardFreeWriteProAction;
import action.board.qna.BoardQnAAction;
import action.board.qna.BoardQnADeleteAction;
import action.board.qna.BoardQnAModifyAction;
import action.board.qna.BoardQnAModifyProAction;
import action.board.qna.BoardQnAReplyAction;
import action.board.qna.BoardQnAReplyProAction;
import action.board.qna.BoardQnAViewAction;
import action.board.qna.BoardQnAWriteAction;
import action.board.qna.BoardQnAWriteProAction;

public class BoardActionFactory {
	private static BoardActionFactory instance = new BoardActionFactory();
	public static BoardActionFactory getinstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		switch(command){
			case "board_qna": action = new BoardQnAAction(); 							break;
			case "board_qna_write" : action = new BoardQnAWriteAction();				break;
			case "board_qna_write_pro" : action = new BoardQnAWriteProAction();			break;
			case "board_qna_view" : action = new BoardQnAViewAction();					break;
			case "board_qna_delete" : action = new BoardQnADeleteAction();				break;
			case "board_qna_reply" : action = new BoardQnAReplyAction();				break;
			case "board_qna_reply_pro" : action = new BoardQnAReplyProAction();			break;
			case "board_qna_modify" : action = new BoardQnAModifyAction();				break;
			case "board_qna_modify_pro" : action = new BoardQnAModifyProAction();		break;
	
			case "board_free": action = new BoardFreeAction(); 							break;
			case "board_free_write" : action = new BoardFreeWriteAction();				break;
			case "board_free_write_pro" : action = new BoardFreeWriteProAction();		break;
			case "board_free_view" : action = new BoardFreeViewAction();				break;
			case "board_free_delete" : action = new BoardFreeDeleteAction();			break;
			case "board_free_reply" : action = new BoardFreeReplyAction();				break;
			case "board_free_recomment" : action = new BoardFreeRecommentAction();		break;
			case "board_free_reply_delete" : action = new BoardFreeReplyDeleteAction();	break;
			case "board_free_modify" : action = new BoardFreeModifyAction();			break;
			case "board_free_modify_pro" : action = new BoardFreeModifyProAction();		break;
		}
		
		return action;
	}
}
