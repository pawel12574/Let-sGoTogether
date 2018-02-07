<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div ng-controller = "userController as c"  class="form">
  
 <legend>Edycja danych</legend>
  <form name="form" class="form" data-ng-submit="!form.$pending && form.$valid && c.changeData(c.loggedUser)">
  
  <label>Imię</label>
   <input type="text" ng-model="c.loggedUser.name" name="name" class="textbox" required/>
    <div ng-messages="form.name.$error" >
     <p ng-show="form.name.$touched && form.name.$error.required">Pole nie może być puste</p>
    </div>
  
  <label>Nazwisko</label> 
   <input type="text" ng-model="c.loggedUser.surname" name="surname" class="textbox" required/>
    <div ng-messages="form.name.$error" >
     <p ng-show="form.name.$touched && form.name.$error.required">Pole nie może być puste</p>
    </div>   
    
  <label>Numer telefonu</label>  
   <input type="text" ng-model="c.loggedUser.phoneNumber" name="phoneNumber" class="textbox"  ng-minlength="9" ng-maxlength="10" pattern="[0-9]+" required/>
    <div ng-messages="form.phoneNumber.$error" >
     <p ng-show="form.phoneNumber.$touched && form.phoneNumber.$error.required">Pole nie może być puste</p>
     <p ng-show="!form.phoneNumber.$valid">Tylko cyfry</p>
     <p ng-show="form.phoneNumber.$error.minlength">Nieprawidłowy nr telefonu</p>
     <p ng-show="form.phoneNumber.$error.maxlength">Nieprawidłowy nr telefonu</p>
    </div>
   
   <label>Data Urodzenia</label> 
   
    <input type="text" class="textbox" id="dateinput" data-date-format="YYYY-MM-DD" ng-model="c.loggedUser.birthDate"  />
    
   <button type="submit" value="Submit" ng-disabled="form.$invalid || form.$pending" class="button">Zmień</button>
  </form> 
</div>

<script type="text/javascript">
    $(function () {
    	 $('#dateinput').datetimepicker({
         	locale: 'pl',
         	inline: true,
         	sideBySide: true
         });
         $('#dateinput').data("DateTimePicker").maxDate(new Date());
       
         $('#dateinput').attr("placeholder", "Data urodzenia?").val("").focus().blur();
         
        
         
    });
</script>