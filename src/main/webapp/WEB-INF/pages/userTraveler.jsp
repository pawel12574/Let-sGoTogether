<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<div class="container" ng-controller="tripTravelerController as c"
	style="margin-top: 5%">
	<div ng-show="!c.listTrip.length">
		<h4>
			<div id="loader" loader-css="ball-pulse" style="display: none"></div>
			<div id="afterLoader" style="display: none; text-align: center;">Nie
				jesteś pasażerem!</div>
		</h4>
	</div>
	<div ng-controller="RatingCtrl as r" ng-repeat="trip in c.listTrip | orderBy: 'tripDate'">
		<div class="span12">
			<ul class="thumbnails">
				<div class="span5 clearfix">
					<div class="thumbnail clearfix">
						<div ng-cloak class="pull-left span2 clearfix"
							ng-attr-id="{{$index}}"
							style="height: 200px; width: 320px; margin-right: 10px"
							ng-if="trip.fromLat!=null">

							<div
								ng-init="initMap($index, trip.fromLat, trip.fromLng, trip.toLat, trip.toLng)"></div>
						</div>

						<div class="caption" class="pull-left">
							<a ui-sref="tripDetails({id :trip.id})"
								class="btn btn-primary icon  pull-right">Szczegóły</a>
							<h4>
								{{trip.date}}
								

							</h4>
							<small><b>{{trip.fromPlace}}<br> 
									{{trip.toPlace}}<br><br>
									<div ng-if="trip.tripDate < c.date">Przejazd zakończony!</div>
									<br>
									  
                                           
											<div ng-init="r.getAvgRating(trip.user.id)" class="col-sm-16">
											 <div ng-init="canRate(trip.id)">
											  <div ng-if="!isReadonly">
											  Oceń użytkownika {{trip.user.name}} {{trip.user.surname | onlyFirst}}
												<rating  ng-model="rate" max="max"
													on-hover="hovering_over(value)" on-leave="overStar = null"
													readonly="isReadonly" ng-click=setRating(trip.id,rate)>
												</rating>
											   </div>	
                                              </div>
                                               <div ng-if="isReadonly">
                                               {{trip.user.name}} {{trip.user.surname | onlyFirst}}
                                                <rating  ng-model="r.avgRating" max="max"
													on-hover="hovering_over(value)" on-leave="overStar = null"
													readonly="isReadonly" ng-click=setRating(trip.id)>
												</rating>
											   </div>	
												<span class="label"
													ng-class="{'label-warning': percent<30, 'label-info': percent>=30 && percent<70, 'label-success': percent>=70}"
													ng-show="overStar && !isReadonly">{{percent}}%</span>

											</div>


										
									</small>
						</div>
					</div>
				</div>

			</ul>
		</div>

	</div>
</div>


