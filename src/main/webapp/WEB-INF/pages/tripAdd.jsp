<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container" ng-controller="tripMapController as c" style="padding-top:4%">
 <div id="map" style="width: 100%; height: 70%; display:block"></div>
 <br>
 <label>Dodaj przejazd, wybierz z mapy punkt startowy i kóncowy twojej podróży, oraz kiedy Chcesz jechać.
 Możesz również wpisać adres w polu formularza.</label><br>

 <div>
 <div>

 
      <input type="hidden" id="duration" />
      <input type="hidden" id="distance" />
      
   <form data-ng-submit="c.submitTrip()" class="form">
      <input type="hidden" id="fromLng"/>
      <input type="hidden" id="fromLat"/>
      <input type="hidden" id="toLng"/>
      <input type="hidden" id="toLat"/>
      <input type="hidden" id="duration"/>
      <input type="hidden" id="distance"/>
      <input type="hidden" id="taxiPrice"/>
      <input type="hidden" id="time"/>
      
      <input type="text" id="tripDate" class="textbox" data-date-format="YYYY-MM-DD HH:mm" required/> 
      <input vs-google-autocomplete vs-autocomplete-validator ng-model="from" name="from" id="fromAddress" class="textbox" placeholder="Adres odjazdu" required/>
       <div ng-show="(form.from.$invalid) && (form.from.$dirty)">
          <span class="help-block"><b>Nie znaleziono adresu</b></span>
       </div>
      <input  vs-google-autocomplete vs-autocomplete-validator ng-model="to"  name="to" id="destAddress" class="textbox" placeholder="Adres docelowy" required/>
       <div ng-show="(form.to.$invalid) && (form.to.$dirty)">     
          <span class="help-block"><b>Nie znaleziono adresu</b></span>
       </div>
      <label>
       <input type="checkbox" ng-model="checked" id="taxi" value="taxi"> Przejazd taksówką
      </label>
      
       <div ng-show="checked" id="taxiData"></div>
      
      <div ng-if="!checked">
        <input type="number" id="freeSeat" class="textbox" placeholder="Wolne miejsca w samochodzie" required/>
        <input type="number" id="price" class="textbox" placeholder="Cena za miejsce" required/>
      </div>
      <input type="hidden" id="freeSeat" />
      <input type="hidden" id="price"/>
      <textarea style="height: 15%" id="description" type="textbox" class="textbox" placeholder="Dodaj krótką informację"/> 
      <button type="submit" value="Submit"  ng-disabled="c.isDisabled" class="button">Dodaj</button> 
   </form>

 </div>
</div>
<script type="text/javascript">
    $(function () {
    	 $('#tripDate').datetimepicker({
         	locale: 'pl',
         	inline: true,
         	sideBySide: true,
         
         });
         $('#tripDate').data("DateTimePicker").minDate(new Date());
       
         $('#tripDate').attr("placeholder", "Kiedy jedziesz?").val("").focus().blur();
        
        //$('#tripDate').focus();
    });
</script>