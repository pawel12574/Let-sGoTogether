<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div ng-cloak  ng-controller="tripController as c" style=margin-top:10%;>

  <div id="fh5co-portfolio" ng-controller="taxiController as t" ng-cloak>
			<div class="fh5co-portfolio-item ">
				<div id="tripMap"  class="fh5co-portfolio-figure animate-box fadeIn animated"  ng-if="c.trip.fromLat!=null" ><div ng-init="initMap()"></div></div>
				<div ng-cloak class="fh5co-portfolio-description">
					<h2>Przejazd</h2>
					  <p style="display:inline; margin: 7;"><img src="resources/img/smallGreenPoint.png"> {{c.trip.fromPlace}}</p>
                      <p style="padding-top:7px; margin: 7;"><img src="resources/img/smallRedPoint.png"> {{c.trip.toPlace}}</p>
                      <p style="border-top:1px solid rgba(160, 160, 160, 0.63); margin: 7;"></p>
                      <p style="padding-top:7px; margin: 7;"><img src="resources/img/smallRoute.png"> {{c.trip.distance*0.001 | number:2}} km</p>
                      <p ng-if="!c.trip.taxiTrip" style="margin: 4;"><img src="resources/img/money.jpg" style="padding-right: 3;"> {{c.trip.price}}zł za miejsce</p>
                      <p ng-if="c.trip.freeSeat!=0" style="margin: 7;"><img src="resources/img/seat.jpg" style="margin-left: -3; ">&nbsp&nbsp{{c.trip.freeSeat}} wolne miejsca <br></p>
                      <p ng-if="c.trip.freeSeat==0" style="margin: 7;"><img src="resources/img/seat.jpg" style="margin-left: -3; ">&nbsp&nbspBrak miejsc <br></p>
                      <p style="margin: 7;"><img src="resources/img/calendar.jpg" style="padding-right:5px; ">  {{c.trip.date}}</p>
                      <p style="margin: 7;" ng-if="c.trip.taxiTrip"><img src="resources/img/taxi-iconS.png"> Przejazd Taxi </p><br>
                      <p style="border-top:1px solid rgba(160, 160, 160, 0.63)"></p>
                      <p ng-if="c.trip.taxiTrip" style="margin:5"> Organizator &nbsp{{c.trip.user.name}}&nbsp{{c.trip.user.surname | onlyFirst}}</p>
                      <div ng-if="c.confirmed.message=='Potwierdzony'">
                       <div class="pure-g" style="height: 30px;">
                        <div class="pure-u-4-5">
                         <p> 
            
                            <button ng-init="show=false" ng-click="show=!show" class="pure-button" ng-class="{'pure-button-active': show==true}" type="button" data-toggle="collapse">
                             Pokaż szczegóły
                            </button>
                         </p>
           
                         <div ng-show="show">
                          <div class="card card-block" style="padding-top: 8px; padding-left: 8px; z-index: 9; color: black; background-color: rgba(195, 195, 195, 0.93);">
             
                          Email {{c.trip.user.username}}<br>
                          Nr telefonu {{c.trip.user.phoneNumber}}
                          </div>
                         </div>
                        </div>
                       </div>
                      </div>
                      <p ng-if="c.trip.taxiTrip && c.trip.user.username==session.data.username" style="margin:5"><select class="selectbox" ng-change="t.changeTaxi(taxi)" ng-model="taxi" ng-options="taxi.name for taxi in t.taxis"></select><br> <button class="pure-button" ng-click="t.callTo(taxi.phoneNumber)"><span class="glyphicon glyphicon-earphone" ></span> Nr telefonu {{taxi.phoneNumber}}</button></p>
                      <p ng-if="c.tripExpired"><b>Podróż rozpoczęta/zakończona</b></p>
                    
                      <p ng-if="!c.trip.taxiTrip" style="margin:5"><img src="resources/img/car.png"> Kierowca &nbsp{{c.trip.user.name}}&nbsp{{c.trip.user.surname | onlyFirst}}</p>
                       <div ng-if="c.trip.tripContainsUser">Jesteś pasażerem!</div>                       
                      
                       <div ng-if="c.confirmed.message=='Potwierdzony'" style="color:green;">
                        {{c.confirmed.message}}!
                       </div>
                       <div ng-if="c.confirmed.message=='Czekaj na potwierdzenie'" style="color:red;">
                        {{c.confirmed.message}}!
                       </div>
                       
					<p>
					  <sec:authorize access="isAuthenticated()">
				                                                              
                          <button ng-disabled="c.addUserButton" id="addUserButton" ng-click="c.addUser(c.trip)"  style="display:none" class="button">Chcę jechać</button>
                          <button ng-disabled="c.removeUserButton" id="removeUserButton" ng-click="c.removeUser(c.trip.id)"  style="display:none" class="button">Nie jadę</button>
                      </sec:authorize>
                    </p>
                    <div id="userNonLocked" style="color:red; display:none">Użytkownik zablokowany</div>
				</div>
			</div>
   </div>
    
    
       
   <div ng-cloak ng-controller="tripTravelerController as ctrl" ng-init="ctrl.getTravelers()" class="passenger-list" >
    <div >
     <div ng-repeat="u in ctrl.listUsers"  class="grids-example" style="background: rgb(239, 236, 236); margin: 4px; max-width: 900px;">
       <div class="pure-g" style="height: 80px;">
          <div class="pure-u-4-5">
          <p> 
             <img ng-if="u.avatar" ng-src="data:image/JPEG;base64,{{u.avatar}}"  class="img-circle img-sm" style="margin-right: 10px;" />
            
             <span ng-cloak ng-if="!u.avatar" class="glyphicon glyphicon-user defaultAvatar" aria-hidden="true" style="margin-right: 10px; font-size: 17;"></span>
            {{u.name}} {{u.surname}}
           <button ng-init="show=false" ng-click="show=!show" class="pure-button" ng-class="{'pure-button-active': show==true}" type="button" data-toggle="collapse">
             Pokaż szczegóły
           </button>
          </p>
           
          
           <div ng-show="show">
            <div class="card card-block" style="padding-top: 8px; z-index: 9; color: black; background-color: rgba(195, 195, 195, 0.93);">
              Wiek {{u.age}} lat<br>
              Email {{u.username}}<br>
              Nr telefonu {{u.phoneNumber}}
            </div>
           </div>
          </div>
          <div class="pure-u-1-5"><button ng-click="c.confirm(u.id)" ng-disabled="u.confirmedTraveler || c.isDisabled" class="button">Potwierdź</button></div>
          
       </div>
      </div>
    </div>
   </div> 
      
  <div class="description panel panel-default description">
			<div class="panel-leftheading" style="padding-left: 20px;">
				<h3 class="panel-lefttitle">Opis</h3>
				
			</div>
			<div style="padding-left:10px; padding-right:70%;"><p style="border-top:1px solid rgba(160, 160, 160, 0.63)"></p></div>
			<div class="panel-rightbody" style="padding: 20px;">
				<p>{{c.trip.description}}
			</div>
			<div class="clearfix"></div>
  </div>
  
  
  	<!--Widget body-->
   <div ng-controller="messageManageController as m" ng-init="c.getChat()" id="demo-chat-body" class="collapse in  chat-body">
    			<div class="nano has-scrollbar" style="height:200px">
    				<div class="nano-content pad-all" tabindex="0" style="right: -17px; outline: none;">
    					<ul class="list-unstyled media-block">
    						
    						<div ng-show="!messages.length"><h4><div id="loader" loader-css="ball-pulse" style="display:none"></div><div id="afterLoader" style="display:none">Brak wiadomości</div></h4> </div>
    						<li class="mar-btm" ng-repeat="message in messages">
    						   
    							<div ng-class="{mediaright:'<sec:authentication property="principal.username" />'=='{{message.author.username}}', medialeft:'<sec:authentication property="principal.username"/>'!='{{message.author.username}}'}"    >
    								 <div ng-if="message.author.avatar">
    								  <img ng-src="data:image/JPEG;base64,{{message.author.avatar}}" class="img-circle img-sm" alt="Profile Picture">
    								 </div> 
    								 <div ng-cloak ng-if="!message.author.avatar">
                                      <span class="glyphicon glyphicon-user defaultAvatar" aria-hidden="true"></span>
                                     </div>
    							</div>
    							<div class="media-body pad-hor" ng-class="{speechright:'<sec:authentication property="principal.username"/>'=='{{message.author.username}}', speechleft:'<sec:authentication property="principal.username"/>'!='{{message.author.username}}'}">
								 <div data-toggle="modal" ng-click="m.select(message)"  style="cursor: pointer;" data-target="#-{{message.author.username==session.data.username}}" class="speech">
									<p class="media-heading">{{message.author.name}}
										{{message.author.surname | onlyFirst}}</p>
									<p>{{message.contents}}</p>
									<p class="speech-time">
										<i class="fa fa-clock-o fa-fw"></i> {{message.date}}

									</p>
								 </div>
							    </div> 
							     <!-- Modal edit -->
							    <div id="-true" class="modal fade" role="dialog"><!-- if messageAuthor==session.user|return true -->
								  <div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Edycja</h4>
										</div>
										<div class="modal-body">
											<form class="form-horizontal">

												<div class="form-group">
													<label class="control-label col-sm-2" for="pwd">Treść</label>
													<div class="col-sm-10">
														<input type="text" class="textbox" id="pwd"
															ng-model="m.selectedMessage.contents">
													</div>
												</div>


												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<button type="submit" class="btn btn-info"
															data-dismiss="modal" ng-click="m.updateMessage()">Zapisz</button>
													</div>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Zamknij</button>
										</div>
									 </div>

								 </div>
							</div>
						</li>
    						
    					</ul>
    				</div>
    			 <div class="nano-pane"><div class="nano-slider" style="height: 141px; transform: translate(0px, 0px);"></div></div>
    	        </div>
  
  
  
   <form name="form" data-ng-submit="!form.$pending && form.$valid && c.addMessage(c.message)" class="form">
  
    <input type="textbox" ng-model="c.message.contents" name="contents" required placeholder="Wiadomość" class="textbox" />
    <button type="submit" value="Submit" ng-disabled="form.$invalid || form.$pending" class="button">Wyślij wiadomość</button> 
    
   </form>
  
   <!--init map after load trip  -->
   

    
  
  
  </div>
  
 
  
  

</div>
