<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>코로나</title>
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<link href="css/common.css" rel="stylesheet">
	<link href="css/mystyle.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

	<!--modal js  -->
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
	<script src="js/jQurey_modal.js"></script>
	<link rel="stylesheet" href="css/jQurey_modal.css" />

<script>
    $(function(){
    	
      $('.slider').bxSlider({
    	  mode: 'fade',
    	  captions: true
      });
      
      $(".sitemap").click(function() {
			$(".sitewrap").slideDown();
		})
	  $("#close").click(function() {
			$(".sitewrap").slideUp();
		})
		
	  $(".nav > nav > .navi > li").hover(function() {
		  $(this).children(".navi2").stop().slideDown();
	  }, function() {
		  $(this).children(".navi2").stop().slideUp();
	  });
		
    });
    
    function Timer(){
    	var clock = document.getElementById("timer");
    	var time = new Date();
    	clock.innerHTML = time.getMonth()+1+"월"+time.getDate()+"일  "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
    	setTimeout("Timer()", 1000);
    }
    window.onload = Timer;
	
	function modal_login() {
		var userid = $('#modal_id').val();
		var passwd = $('#modal_pw').val();

		var opener = window.dialogArguments;
		$.ajax({
			type : 'POST', 
			url : 'SYKK?command=member_login_pro',
			data : {userid : userid, passwd : passwd},
			success : function(result) {
				if (result > 0) {
					alert("로그인 되었습니다");
	               	window.location.reload();
				}
				if (result==0) {
					alert("비밀번호가 올바르지 않습니다");
				}
				if (result<0){
					alert("존재하지 않는 아이디입니다");
				}
			}
		})
	}
	
	function logout(){
		$.ajax({
			type : 'GET',
			cache : false,
			url : 'SYKK?command=member_logout',
			success : function(sw) {
				if(sw==""){
					alert("로그아웃 실패");
					return;
				}
				var out = JSON.parse(sw);
				if(out.result){
					alert("로그아웃 되었습니다");
	               	window.location.reload();
				}
			}
		})
	}
	
	function onChat(){
		window.open("Chat/chat.jsp", "Chating", "width=560 height=300");
	}
</script>

</head>
<body>
	<div class="header">
		<header>
			<div class="topnav">
				<ul>
					<font color=gray face=MS><B><li id="timer"></li></B></font>
					<c:if test="${empty sessionScope.userid}">
					<li><a href="#modal_login" rel="modal:open">LogIn</a></li>
					<li><a href="SYKK?command=member_signup">SignUp</a></li>
					</c:if>
					<c:if test="${!empty sessionScope.userid}">
					<li><a href="javascript:logout()">LogOut</a></li>
					<li><a href="SYKK?command=member_modify">MyPage</a></li>
					</c:if>
					<li><a href="javascript:onChat()">Chat Room</a></li>
					<li><a href="javascript:void(0)" class="sitemap">SiteMap</a></li>
				</ul>
			</div>
			<div class="navigation">
				<h1 class="logo"><a href="index.jsp">Yuka</a></h1>
				<div class="nav">
					<nav>
						<ul class="navi">
							<li><a href="javascript:void(0)"><strong>紹介</strong></a>
								<ul class="navi2">
									<li><a href="SYKK?command=about_introduce">自己紹介</a></li>
									<!-- <li><a href="SYKK?command=about_ceritificate">免許状</a></li> -->
									<!-- <li><a href="SYKK?command=about_career">経歴</a></li> -->
								</ul>
							</li>
							<li><a href="javascript:void(0)"><strong>ギャラリー</strong></a>
								<ul class="navi2">
									<li><a href="SYKK?command=gallery_sa">留学</a></li>
									<li><a href="SYKK?command=gallery_f">博覧会</a></li>
									<li><a href="SYKK?command=gallery_w">個人作品</a></li>
								</ul>
							</li>
							<li><a href="javascript:void(0)"><strong>趣味</strong></a>
								<ul class="navi2">
									<li><a href="SYKK?command=hobit_leisure">レジャー</a></li>
									<li><a href="SYKK?command=hobit_game">ゲーム</a></li>
									<li><a href="SYKK?command=hobit_travel">旅行</a></li>
								</ul>
							</li>
							<li><a href="javascript:void(0)"><strong>掲示板</strong></a>
								<ul class="navi2">
									<li><a href="SYKK?command=board_free">自由掲示板</a></li>
									<li><a href="SYKK?command=board_qna">QnA掲示板</a></li>
								</ul>
							</li>
							<li><a href="javascript:void(0)"><strong>資料室</strong></a>
								<ul class="navi2">
									<li><a href="SYKK?command=notice_apprise">お知らせ</a></li>
									<li><a href="SYKK?command=notice_reference">添付ファイル</a></li>
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
	</div>
	
	<div class="line"></div>
	
	<div class="sitewrap">
		<span class="fa fa-close" id="close" style="cursor:pointer"></span>
		<div class="inner">
			<span class="map">紹介</span>
			<span class="map">ギャラリー</span>
			<span class="map">趣味</span>
			<span class="map">掲示板</span>
			<span class="map">資料室</span>
		</div>
	</div>
	
	
	<div class="contain" id="modal_login" style="display:none;">
      <div class="write-form"
         style="width:100%; margin: 0 auto; border: 1px solid #ccc; padding: 20px;">
         <form name=log method="post">
         	<input type="hidden" name="command" value="member_login_pro">
            <fieldset>
               <legend class="readonly">LogIn</legend>
               <div id="log_form" style="width:70%;float:left;">
					<br>
					<input type="text" id="modal_id" name="userid" placeholder="Userid">
					<input type="password" name="passwd"  id="modal_pw" placeholder="Password" onkeypress="if(event.keyCode == 13){modal_login(); return; }">
				</div>
               <div
                  style="width: 28%; float: right; padding: 15px; height: 132px; margin-top: 3px;">
                <input type="button" onclick="modal_login()" value="Login" alt="Login" style="background: #FF4000; color: #fff; width: 100%; height: 85%; border: 0 none; cursor: pointer;">
               </div>
               <b id=checkMessage></b>
               <p style="clear: both; padding-top: 20px; text-align: center;"><a href="SYKK?command=member_id_search">Search ID</a> | <a href="SYKK?command=member_pw_reset">Search PW</a> | <a href="SYKK?command=member_signup">Sign Up</a>
               </p>
            </fieldset>
         </form>
      </div>
   </div>
	
	
	
	
	
	