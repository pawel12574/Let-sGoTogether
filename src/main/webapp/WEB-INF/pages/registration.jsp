<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<br><br>
<div class="container" ng-controller="userController as c">
	
 <form name="form" data-ng-submit="!form.$pending && form.$valid && c.registerUser(c.user)" class="form">
   <legend>Nowy użytkownik</legend>
   
  <input type="email" ng-model="c.user.username" name="username" required email-Unique ng-model-options="{ debounce: 500 }" placeholder="Adres Email" class="textbox" />
   <div ng-messages="form.username.$error" >
    <p ng-show="form.username.$touched && form.username.$error.required">Wprowadź adres email</p>
    <div ng-message="emailUnique">Ten email jest już użyty</div>
    <p ng-show="form.username.$error.email ">Wprowadź poprawny adres email</p>
  </div>
  
  <input type="password" ng-model="c.user.password" ng-minlength="6" name="password" class="textbox" required  placeholder="Hasło"/>
   <p ng-show="form.password.$touched && form.password.$error.required">Wprowadź hasło</p>
   <p ng-show="form.password.$error.minlength">Minimalna długość hasła to 6 znaków</p>
   
  <input type="password"  ng-model="c.user.passwordRe" ng-minlength="6" name="passwordRe" class="textbox" confirm-password="c.user.password" required placeholder="Potwierdź hasło"/>
   <p ng-show="form.passwordRe.$touched && form.passwordRe.$error.required"></p>
  
   <div ng-if="form.passwordRe.$touched" ng-messages="form.passwordRe.$error">
    <div ng-message="confirmPassword">Popraw hasło</div>
   </div>
  
  <input type="text"  ng-model="c.user.name" ng-minlength="1" name="name" class="textbox"  required placeholder="Imię"/>
   <p ng-show="form.name.$touched && form.name.$error.required">Uzupełnij imię</p>
   
  <input type="text"  ng-model="c.user.surname" ng-minlength="1" name="surname" class="textbox"  required placeholder="Nazwisko"/>
   <p ng-show="form.surname.$touched && form.surname.$error.required">Uzupełnij nazwisko</p>
  
  
  <input type="text" id="dateinput" class="textbox" customzdatetime data-date-format="YYYY-MM-DD" required ng-model="birthDate" name="birthDate"/> 

   <p ng-show="form.birthDate.$touched && form.birthDate.$error.required">Uzupełnij datę urodzenia</p>
   <p ng-show="form.birthDate.$error.date">Nieprawidłowy format daty</p>
  
  
  <input type="number"  ng-model="c.user.phoneNumber" name="phoneNumber" class="textbox"  ng-minlength="9" ng-maxlength="10" required placeholder="Numer telefonu "/>
   <p ng-show="form.phoneNumber.$touched && form.phoneNumber.$error.required">Uzupełnij nr telefonu</p>
   <p ng-show="form.phoneNumber.$error.minlength">Nieprawidłowy nr telefonu</p>
   <p ng-show="form.phoneNumber.$error.maxlength">Nieprawidłowy nr telefonu</p>
  
  
 <button type="submit" value="Submit" ng-disabled="form.$invalid || form.$pending" class="button">Rejestracja</button> </form>


<script type="text/javascript">
    $(function () {
    	 $('#dateinput').datetimepicker({
         	locale: 'pl',
         	inline: true,
         	sideBySide: true
         });
         $('#dateinput').data("DateTimePicker").maxDate(new Date());
       
         $('#dateinput').attr("placeholder", "Data urodzenia").val("").focus().blur();
        
         
    });
</script>
</div>
  

