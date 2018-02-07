<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html ng-app="myApp">

<head>
	<title>Let'sGoTogether</title>
  <!--  <base href="/web/"> -->
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" >
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	
	<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
	<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
	<link rel="stylesheet" href="resources/css/baby-blue.css">
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="resources/css/style.css"> 
	<link rel="stylesheet" href="resources/css/login-modal.css"> 
	<link rel="stylesheet" href="resources/css/findForm.css" />
	<link rel="stylesheet" href="resources/css/materialize.css" />
	<link rel="stylesheet" href="resources/css/registrationForm.css" />
	<link rel="stylesheet" href="resources/css/google.css"/> 
	<link rel="stylesheet" href="resources/css/chat.css"/>
	<link rel="stylesheet" href="resources/css/notification.css"/>
	<link rel="stylesheet" href="resources/css/rangeSlider.css"/>
	<link rel="stylesheet" href="resources/css/adminMenu.css"/>
	<link rel="stylesheet" href="resources/css/adminPages.css"/>
	<link rel="stylesheet" href="resources/css/tooltip.css"/>
	<link rel="stylesheet" href="resources/css/admin.css"/>
	<link rel="stylesheet" href="resources/css/loader.css" />
	<link rel="stylesheet" href="resources/css/tripDetails.css" />
	<link rel="stylesheet" href="resources/css/confirmTrip.css" />
	<link rel="stylesheet" href="resources/css/avatar.css" />
	<link rel="stylesheet" href="resources/css/left-side-menu.css" />
	<link href="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
	
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="https://unpkg.com/angular-toastr/dist/angular-toastr.tpls.js"></script>
	<script src="http://angular-ui.github.io/ui-router/release/angular-ui-router.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-resource.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
    <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.13.4.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGq-qP3nxn_YmZQAPQ4IYuIcCZGK4X2Wk&libraries=places"></script>
    
    
    <link rel="stylesheet" href="https://unpkg.com/angular-toastr/dist/angular-toastr.css" />
    <script src="resources/ng/app.js"></script>
    <script src="resources/ng/trip.js"></script>
    <script src="resources/ng/vs-google-autocomplete.js"></script>
    <script src="resources/ng/vs-autocomplete-validator.js"></script>
    <script src="resources/ng/directive/registerValidator.js"></script>
    <script src="resources/ng/directive/upload.js"></script>
    <script src="resources/ng/user.js"></script>
    <script src="resources/ng/tripSd.js"></script>
    <script src="resources/ng/tripManage.js"></script>
    <script src="resources/ng/notification.js"></script>
    <script src="resources/ng/rating.js"></script>
    <script src="resources/ng/taxi.js"></script>
    <script src="resources/ng/tripSdManage.js"></script>
    <script src="resources/ng/tripManage.js"></script>
    <script src="resources/ng/userManage.js"></script>
    <script src="https://cdn.rangetouch.com/0.0.9/rangetouch.js"></script>
    <script src="http://momentjs.com/downloads/moment-with-locales.min.js"></script>
    <script src="resources/ng/angular-loaders.min.js"></script>
    <script src="resources/ng/loaderService.js"></script>
    <script src="resources/ng/notificationManage.js"></script>
    <script src="resources/ng/session.js"></script>
    <script src="resources/ng/filter/onlyFirst.js"></script>
    <script src="resources/ng/reservation.js"></script>
    <script src="resources/ng/messageManage.js"></script>
    <script src="resources/ng/reservationService.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    
     <style type="text/css">
    [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
        display: none !important;
     }
    ::-moz-placeholder {
        opacity: 0,8;
     }
   
     input:invalid {
        box-shadow: none;
    }
    .datepicker .datepickerown
    .bootstrap-datetimepicker-widget .datepicker-years thead,
    .bootstrap-datetimepicker-widget .datepicker-years .disabled {
     display: none;
    }
    </style>
   <!-- <script type="text/javascript">
    /*! modernizr 3.5.0 (Custom Build) | MIT *
     * https://modernizr.com/download/?-canvas-formvalidation-input-inputtypes-oninput-placeholder-setclasses !*/
    !function(e,t,n){function i(e,t){return typeof e===t}function a(){var e,t,n,a,o,r,s;for(var l in u)if(u.hasOwnProperty(l)){if(e=[],t=u[l],t.name&&(e.push(t.name.toLowerCase()),t.options&&t.options.aliases&&t.options.aliases.length))for(n=0;n<t.options.aliases.length;n++)e.push(t.options.aliases[n].toLowerCase());for(a=i(t.fn,"function")?t.fn():t.fn,o=0;o<e.length;o++)r=e[o],s=r.split("."),1===s.length?Modernizr[s[0]]=a:(!Modernizr[s[0]]||Modernizr[s[0]]instanceof Boolean||(Modernizr[s[0]]=new Boolean(Modernizr[s[0]])),Modernizr[s[0]][s[1]]=a),d.push((a?"":"no-")+s.join("-"))}}function o(e){var t=f.className,n=Modernizr._config.classPrefix||"";if(c&&(t=t.baseVal),Modernizr._config.enableJSClass){var i=new RegExp("(^|\\s)"+n+"no-js(\\s|$)");t=t.replace(i,"$1"+n+"js$2")}Modernizr._config.enableClasses&&(t+=" "+n+e.join(" "+n),c?f.className.baseVal=t:f.className=t)}function r(){return"function"!=typeof t.createElement?t.createElement(arguments[0]):c?t.createElementNS.call(t,"http://www.w3.org/2000/svg",arguments[0]):t.createElement.apply(t,arguments)}function s(){var e=t.body;return e||(e=r(c?"svg":"body"),e.fake=!0),e}function l(e,n,i,a){var o,l,d,u,p="modernizr",c=r("div"),v=s();if(parseInt(i,10))for(;i--;)d=r("div"),d.id=a?a[i]:p+(i+1),c.appendChild(d);return o=r("style"),o.type="text/css",o.id="s"+p,(v.fake?v:c).appendChild(o),v.appendChild(c),o.styleSheet?o.styleSheet.cssText=e:o.appendChild(t.createTextNode(e)),c.id=p,v.fake&&(v.style.background="",v.style.overflow="hidden",u=f.style.overflow,f.style.overflow="hidden",f.appendChild(v)),l=n(c,e),v.fake?(v.parentNode.removeChild(v),f.style.overflow=u,f.offsetHeight):c.parentNode.removeChild(c),!!l}var d=[],u=[],p={_version:"3.5.0",_config:{classPrefix:"",enableClasses:!0,enableJSClass:!0,usePrefixes:!0},_q:[],on:function(e,t){var n=this;setTimeout(function(){t(n[e])},0)},addTest:function(e,t,n){u.push({name:e,fn:t,options:n})},addAsyncTest:function(e){u.push({name:null,fn:e})}},Modernizr=function(){};Modernizr.prototype=p,Modernizr=new Modernizr;var f=t.documentElement,c="svg"===f.nodeName.toLowerCase();Modernizr.addTest("canvas",function(){var e=r("canvas");return!(!e.getContext||!e.getContext("2d"))}),Modernizr.addTest("placeholder","placeholder"in r("input")&&"placeholder"in r("textarea"));var v=r("input"),m="autocomplete autofocus list placeholder max min multiple pattern required step".split(" "),h={};Modernizr.input=function(t){for(var n=0,i=t.length;i>n;n++)h[t[n]]=!!(t[n]in v);return h.list&&(h.list=!(!r("datalist")||!e.HTMLDataListElement)),h}(m);var y="search tel url email datetime date month week time datetime-local number range color".split(" "),g={};Modernizr.inputtypes=function(e){for(var i,a,o,r=e.length,s="1)",l=0;r>l;l++)v.setAttribute("type",i=e[l]),o="text"!==v.type&&"style"in v,o&&(v.value=s,v.style.cssText="position:absolute;visibility:hidden;",/^range$/.test(i)&&v.style.WebkitAppearance!==n?(f.appendChild(v),a=t.defaultView,o=a.getComputedStyle&&"textfield"!==a.getComputedStyle(v,null).WebkitAppearance&&0!==v.offsetHeight,f.removeChild(v)):/^(search|tel)$/.test(i)||(o=/^(url|email)$/.test(i)?v.checkValidity&&v.checkValidity()===!1:v.value!=s)),g[e[l]]=!!o;return g}(y);var b=function(){function e(e,t){var a;return e?(t&&"string"!=typeof t||(t=r(t||"div")),e="on"+e,a=e in t,!a&&i&&(t.setAttribute||(t=r("div")),t.setAttribute(e,""),a="function"==typeof t[e],t[e]!==n&&(t[e]=n),t.removeAttribute(e)),a):!1}var i=!("onblur"in t.documentElement);return e}();p.hasEvent=b;var C=p.testStyles=l;Modernizr.addTest("oninput",function(){var n,i=r("input");if(i.setAttribute("oninput","return"),b("oninput",f)||"function"==typeof i.oninput)return!0;try{var a=t.createEvent("KeyboardEvent");n=!1;var o=function(e){n=!0,e.preventDefault(),e.stopPropagation()};a.initKeyEvent("keypress",!0,!0,e,!1,!1,!1,!1,0,"e".charCodeAt(0)),f.appendChild(i),i.addEventListener("input",o,!1),i.focus(),i.dispatchEvent(a),i.removeEventListener("input",o,!1),f.removeChild(i)}catch(s){n=!1}return n}),Modernizr.addTest("formvalidation",function(){var t=r("form");if(!("checkValidity"in t&&"addEventListener"in t))return!1;if("reportValidity"in t)return!0;var n,i=!1;return Modernizr.formvalidationapi=!0,t.addEventListener("submit",function(t){(!e.opera||e.operamini)&&t.preventDefault(),t.stopPropagation()},!1),t.innerHTML='<input name="modTest" required="required" /><button></button>',C("#modernizr form{position:absolute;top:-99999em}",function(e){e.appendChild(t),n=t.getElementsByTagName("input")[0],n.addEventListener("invalid",function(e){i=!0,e.preventDefault(),e.stopPropagation()},!1),Modernizr.formvalidationmessage=!!n.validationMessage,t.getElementsByTagName("button")[0].click()}),i}),a(),o(d),delete p.addTest,delete p.addAsyncTest;for(var E=0;E<Modernizr._q.length;E++)Modernizr._q[E]();e.Modernizr=Modernizr}(window,document);</script>
	 -->
</head>
<body ng-cloak ng-controller="userController as c">
	<nav id="nav" class="navbar navbar-inverse navbar-fixed-top">
	
		<div class="container">
			<div class="navbar-header">
			  <a ui-sref="welcome"><img src="resources/img/logo.png" style="padding-top: 12px;"></a>
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>                        
				</button>
			</div>   
			
			<div class="collapse navbar-collapse" id="myNavbar">
			
				<ul class="nav navbar-nav">
				<li>
			  <sec:authorize access="isAuthenticated()">
				<div class="dropdown">
				 <button class="dropdown-toggle" type="button" id="menu2" data-toggle="dropdown">Dodaj trasę
				 <span class="caret"></span></button>
				  <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
				    <li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="tripAdd">Dodaj przejazd</a></li>
					<li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="tripSdAdd">Dodaj przejazd, którego szukasz</a></li>
					</ul>
				</div>
			  </sec:authorize>	
				</li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="!isAuthenticated()">
                     
                    <li><a ui-sref="registration" ><span class="glyphicon glyphicon-user"></span> Rejestracja</a></li>
                    <li><a  onclick="document.getElementById('loginForm').style.display='block'"><span style="width:auto" class="glyphicon glyphicon-log-in"></span> Zaloguj się</a></li>
                   
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                    
                    <li>
                    <div class="dropdown">
                      <button class="dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">
                        <div ng-cloak ng-if="session.data.avatar">
                           <img ng-src="data:image/JPEG;base64,{{session.data.avatar}}"  class="img-circle img-sm" />
                         <sec:authentication property="principal.username" />
                        </div>
                        <div ng-cloak ng-if="!session.data.avatar">
                           <span class="glyphicon glyphicon-user defaultAvatar" aria-hidden="true"></span>
                         <sec:authentication property="principal.username" />
                        </div>
 
                        <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                       <li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="notification">Powiadomienia</a></li>
                       <li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="trips">Organizator</a></li>
                       <li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="userTraveler">Pasażer</a></li>
                       <li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="tripSdGet">Przejazdy poszukiwane</a></li>
                       <li role="presentation"><a role="menuitem" tabindex="-1" ui-sref="user.avatar">Ustawienia konta</a></li>
                      
                      </ul>
                    </div>
                    </li>
                    <li><a href="./logout"><span class="glyphicon glyphicon-log-out" style="padding-right: 6px;"></span>Wyloguj</a></li>
                    
                    </sec:authorize>
                </ul>
			</div>   
		</div>
	</nav>    
	<br>
	<br>
	
	 	
<div ui-view></div>



	<div id="loginForm" class="modalLogin">

		<form class="modalLogin-content animate"
			action="j_spring_security_check" method="POST">

			<span
				onclick="document.getElementById('loginForm').style.display='none'"
				class="close" title="Zamknij">&times;</span>


			<div id="container">
				<label><b>Email&nbsp;</b></label> <input type="text"
					placeholder="Email" name="j_username" id="input-login"
					style="width: 60%" class="textboxLogin" required> <br>
				<label><b>Hasło&nbsp; </b></label> <input type="password"
					placeholder="Hasło" name="j_password" style="width: 60%"
					class="textboxLogin" required> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" id="button-login" class="button">Login</button>
				<br>
				
				<input type="checkbox" tabindex="3" class="" name="remember-me"
						id="remember"> <label for="remember"> Zapamiętaj mnie</label>
				
			</div>

			<div id="container">
				<button type="button"
					onclick="document.getElementById('loginForm').style.display='none'"
					class="cancelbtn">Zamknij</button>
				<span class="psw" style="color: #fff;" onclick="document.getElementById('loginForm').style.display='none'"><a
					ui-sref="forgotPassword"><b>Nie pamiętasz hasła?</b></a></span>
			</div>
		</form>
	</div>
<!-- <script type="text/javascript">
 f = navigator.userAgent.search("Firefox");
 if(f> -1) {
	  alert("Zalecana przeglądarka wspierająca HTML5, proszę skorzystać z Google Chrome lub Opera")
	} -->
 </script>
	<script type="text/javascript" src="resources/js/loginForm.js"></script>	
    <script type="text/javascript" src="resources/js/menuClose.js"></script>
		
</body>
</html>