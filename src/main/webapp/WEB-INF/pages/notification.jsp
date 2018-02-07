<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<div ng-controller="notificationController as c" style="margin-top: 10%">
	

		<div ng-controller="notificationManageController as t"
			ng-show="!c.allNotification.length">
			<h4>
				<div id="loader" loader-css="ball-pulse" style="display: none"></div>
				<div id="afterLoader" style="display: none; text-align: center;">Brak powiadomień</div>
			</h4>
		</div>

		<div class="container">
			<div class="row">
				<div ng-repeat="n in c.allNotification | orderBy: 'created'">
                    <div class="col-sm-4">
						<div class="tile red">
							<h3 class="titles">Przejazd</h3>
							<p>
								{{n.trip.fromPlace}}<br> {{n.trip.toPlace}}<br>
								{{n.trip.date}}

								<button ui-sref="tripDetails({id :n.trip.id})"
									class="btn btn-note" ng-click="c.confirmNotification(n.id)"
									ng-show="n">Pokaż przejazd</button>
								<button ng-click="c.removeNotification(n)" class="btn btn-note">Usuń</button>	
							</p>
						</div>
					</div>
			  </div>
			</div>

		  </div>

		 

</div>
