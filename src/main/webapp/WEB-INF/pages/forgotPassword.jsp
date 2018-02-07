<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="container" ng-controller="userController as c" style="margin-top:9%">

 <legend>Podaj adres email podany przy rejestracji</legend>
  <form name="form" data-ng-submit="!form.$pending && form.$valid && c.forgotPassword(c.username)" class="form" style="padding: 0px; max-width: 405px; float: inherit;">
  
  
  <input type="email" ng-model="c.username" name="username" required email-Not-Exist ng-model-options="{ debounce: 500 }" placeholder="Adres Email" class="textbox" />
   <div ng-messages="form.username.$error" >
    <p ng-show="form.username.$touched && form.username.$error.required">Wprowadź adres email</p>
    <div ng-message="emailNotExist">Brak takiego adresu email</div>
    <p ng-show="form.username.$error.email ">Wprowadź poprawny adres email</p>
  </div>
   
  <button type="submit" value="Submit" ng-disabled="form.$invalid || form.$pending" class="button">Wyślij</button>
 </form>

</div>  