<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, model.notice.apprise.*, 
    model.hobit.leisure.*, model.hobit.game.*, model.hobit.travel.*, model.board.qna.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>

<%
	List<appriseVO> apprise_list = appriseDAO.getinstance().Index_List();
	leisureVO leisure_vo = leisureDAO.getinstance().Index_List();
	gameVO game_vo = gameDAO.getinstance().Index_List();
	travelVO travel_vo = travelDAO.getinstance().Index_List();
	List<qnaVO> qna_list = qnaDAO.getinstance().Index_List();

	request.setAttribute("apprise_list", apprise_list);
	request.setAttribute("leisure_vo", leisure_vo);
	request.setAttribute("qna_list", qna_list);
	request.setAttribute("game_vo", game_vo);
	request.setAttribute("travel_vo", travel_vo);
%>

<div class="indexgallery">
	<div class="indextitle">
		<h2>
			マイ<br>ギャラリー
		</h2>
		<span><a href="SYKK?command=gallery_sa">MORE</a></span>
	</div>
	<ul>
		<c:if test="${!empty leisure_vo}">
			<li><a href="SYKK?command=hobit_leisure"><img
					src="hobit/leisure/${leisure_vo.photo}"></a></li>
		</c:if>
		<c:if test="${!empty game_vo}">
			<li><a href="SYKK?command=hobit_game"><img
					src="hobit/game/${game_vo.photo}"></a></li>
		</c:if>
		<c:if test="${!empty travel_vo}">
			<li><a href="SYKK?command=hobit_travel"><img
					src="hobit/travel/${travel_vo.photo}"></a></li>
		</c:if>
	</ul>
</div>
<div class="bbs_wrap">
	<div class="bbs_left">
		<h2 class="title">お知らせ</h2>
		<ul>
			<c:if test="${!empty apprise_list}">
				<c:forEach items="${apprise_list}" var="apprise">
					<li><a
						href="SYKK?command=notice_apprise_view&idx=${apprise.idx}">${apprise.subject}</a></li>
				</c:forEach>
			</c:if>
		</ul>
		<a href="SYKK?command=notice_apprise"><span
			class="fa fa-plus plus"></span></a>
	</div>

	<div class="bbs_right">
		<h2 class="title">QnA掲示板</h2>
		<ul>
			<c:if test="${!empty qna_list}">
				<c:forEach items="${qna_list}" var="qna">
					<li><a href="SYKK?command=board_qna_view&idx=${qna.idx}">${qna.subject}</a></li>
				</c:forEach>
			</c:if>
		</ul>
		<a href="SYKK?command=board_qna"><span class="fa fa-plus plus"></span></a>
	</div>
</div>

<%@ include file="footer.jsp"%>













