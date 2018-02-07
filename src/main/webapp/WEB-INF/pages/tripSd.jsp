<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div class="container" ng-controller="addTripSdController as ctrl" style="padding-top:4%" ng-class="{'form-group':true, 'has-error':form.from.$dirty && form.from.$invalid || form.to.$dirty && form.to.$invalid}">
 <div ng-init="initMap()" id="map" style="width: 100%; height: 70%; display:block"></div>
 
 <br>
 <label>Dodaj poszukiwany przejazd. Zostaniesz powiadomiony, kiedy ktoś doda przejazd, który Cię interesuje.<br>
 Wybierz z mapy punkt startowy i kóncowy twojej podróży, oraz kiedy Chcesz jechać.</label>
 <div>

 
      <input type="hidden" id="duration" />
      <input type="hidden" id="distance" />
      
   <form name="form" data-ng-submit="ctrl.addTripSd()" class="form">
      <input type="hidden" id="fromLng"/>
      <input type="hidden" id="fromLat"/>
      <input type="hidden" id="toLng"/>
      <input type="hidden" id="toLat"/>
    
     <div style="overflow:hidden;">
    
         <input id="tripDateBegin" class="textbox" data-date-format="YYYY-MM-DD" required>
                
         <input id="tripDateEnd" class="textbox" data-date-format="YYYY-MM-DD" required>
               
     </div>
  
      <input vs-google-autocomplete vs-autocomplete-validator ng-model="from" name="from" id="fromAddress" class="textbox" placeholder="Adres odjazu" required/>
       <div ng-show="(form.from.$invalid) && (form.from.$dirty)">
          <span class="help-block"><b>Nie znaleziono adresu</b></span>
       </div>
   
      <input vs-google-autocomplete vs-autocomplete-validator ng-model="to"  name="to" id="destAddress" class="textbox" placeholder="Adres docelowy" required/>
       <div ng-show="(form.to.$invalid) && (form.to.$dirty)">     
         <span class="help-block"><b>Nie znaleziono adresu</b></span>
       </div>
      
      <input type="number" id="freeSeat" class="textbox" placeholder="Ile miejsc potrzebujesz?" required/>
       
     <!--  <textarea id="description" class="textbox" style="height: 15%" placeholder="podaj dodatkowe informacje"></textarea> --> 
      
      <button type="submit" value="Submit"  ng-disabled="ctrl.isDisabled" class="button">Dodaj</button> 
   </form>

 </div>
</div>
<script type="text/javascript">
    $(function () {
    	 $('#tripDateBegin').datetimepicker({
         	locale: 'pl',
         	inline: true,
         	sideBySide: true
         });
         $('#tripDateBegin').data("DateTimePicker").minDate(new Date());
       
        
        $('#tripDateEnd').datetimepicker({
        	locale: 'pl',
        	inline: true,
        	sideBySide: true
        });
        $('#tripDateEnd').data("DateTimePicker").minDate(new Date());
      
       
        
        $('#tripDateBegin').attr("placeholder", "Od kiedy?").val("").focus().blur();
        $('#tripDateEnd').attr("placeholder", "Do kiedy?").val("").focus().blur();
      //  $('#tripDateBegin').focus();
        
    });
</script>