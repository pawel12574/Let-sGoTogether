<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<script type="text/javascript">
    $(function () {
    	 $('#date').datetimepicker({
         	locale: 'pl',
         	sideBySide: true
         });
   
         $('#date').data("DateTimePicker").minDate(new Date());
       
         $('#date').attr("placeholder", "Kiedy?").val("").focus().blur();
      
      
    });
</script>
<div ng-controller="notificationController as n">
  <div ng-repeat="note in n.similarTripNotification">
    <div ng-init="n.showNotificationForSimilar(note.trip.fromPlace, note.trip.toPlace)"></div>
  </div>

  <div ng-repeat="note in n.updatedTripNotification">
    <div ng-init="n.showNotificationForUpdated(note.trip.fromPlace, note.trip.toPlace)"></div>
  </div>
</div>

<div ng-controller="findTripController" style=" margin-top:10%;">
 
	<br> <br>
  <form name="form" data-ng-submit="submit()" data-ng-controller="findTripController" class="destinations-form">
                      
                     <div class="input-line"  ng-class="{'form-group':true, 'has-error':form.from.$dirty && form.from.$invalid || form.to.$dirty && form.to.$invalid}">
                        <input type="text" name="from" id="from" vs-google-autocomplete vs-autocomplete-validator required ng-model="from" value="" class="form-input check-value input-find" placeholder="Skąd?" />
                         
                        <input type="text" name="to" id="to" vs-google-autocomplete vs-autocomplete-validator required ng-model="to" value="" class="form-input check-value input-find date" placeholder="Dokąd?" />
                        <input type="text" data-date-format="YYYY-MM-DD" placeholder="Kiedy?" id="date" class="form-input check-value input-find"/>
                          <br>
                        <div ng-show="(form.from.$invalid) && (form.from.$dirty)">
                             <span class="help-block"><b>Nie znaleziono adresu</b></span>
                        </div>
                        <div ng-show="(form.to.$invalid) && (form.to.$dirty)">     
                             <span class="help-block"><b>Nie znaleziono adresu</b></span>
                        </div>
                            <input id="fromLat" type="hidden" ng-model="fromLat" />
                            <input id="fromLng" type="hidden" ng-model="fromLng" />
                            <input id="toLat"   type="hidden" ng-model="toLat"/>
                            <input id="toLng"   type="hidden" ng-model="toLng"/>
                            <br>
                            <input type="range"  id="rangeFrom" min="100" max="3000" step="10" value="500" data-rangeSlider style="display: inline-block;"/>
                            <input type="range"  id="rangeTo"   min="100" max="3000" step="10" value="500" data-rangeSlider style="display: inline-block;"/>
                         <input type="submit" id="submit" value={{sendButton}} class="form-submit btnFind btn-special"/>
                           <br><br>
                           <div ng-show="!trips.length && result"><div id="afterLoader" style="display:none">Brak przejazdów dla podanej trasy</div><div id="loader" loader-css="ball-pulse" style="display:none; text-align: center;"></div></div>
                            <p ng-init="initMap()">Przeciągnij punkty na mapie aby znaleźć przejazd, lub wpisz adres.</p>
                            <div id="map" style="height:80%" ></div>
                      </div>   
                     
                     
                     
                   
 
    <div ng-controller="RatingCtrl as r" ng-repeat="trip in trips | orderBy: 'tripDate'" class="card horizontal">
      <div class="card-image">
        <img ng-if="trip.user.avatar" ng-src="data:image/JPEG;base64,{{trip.user.avatar}}"  class="img-circle img-sm" style="padding-left: 13%; margin-top: 25px;" />
        <span ng-cloak ng-if="!trip.user.avatar" class="glyphicon glyphicon-user defaultAvatar" aria-hidden="true" style="padding-left: 40%; margin-top: 25px; font-size: 30;"></span>
            
            <div ng-init="r.getAvgRating(trip.user.id)">
        <br>
         <p style="text-align:center;"> &nbsp{{trip.user.name}}&nbsp{{trip.user.surname | onlyFirst}}<br>
         {{trip.user.age}}lat<br>
          <div style="padding-left: 10px;" class="col-sm-16">
                    <rating 
                            ng-model="r.avgRating" 
                            max="max" 
                            on-hover="hovering_over(value)" 
                            on-leave="overStar = null" 
                            readonly="true"
                             >
                    </rating>
                   
                     
                    
            </div>
          
          
           
        </div>  
      </div>
      <div class="card-stacked">
        <div class="card-content">
          
          <p style="display:inline;"><img src="resources/img/greenPoint.png"> {{trip.fromPlace}}</p> 
          <p style="display:inline;">, {{trip.date}}</p>
           
          <p style="padding-top:7px;"><img src="resources/img/redPoint.png"> {{trip.toPlace}}, {{trip.endDate}}</p>
          <p style="padding-top:7px;"><img src="resources/img/route.png"> {{trip.distance*0.001 | number:2}} km</p>
          <p ng-if="trip.taxiTrip" style="padding-top:7px;"><img src="resources/img/taxi.jpg"> ~{{trip.taxiPrice}}zł/trasa</p>
          <p ng-if="!trip.taxiTrip" style="padding-top:7px;"><img src="resources/img/car.png"> {{trip.price}}zł/osoba</p>
        </div>
        <div class="card-action">
          <a ui-sref="tripDetails({id :trip.id})">Przejdź</a>
        </div>
      </div>
    </div>

                     
         
  </form>

	
 </div>
          

          


