<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="container" sec:authorize="hasAuthority('CHANGE_PASSWORD_PRIVILEGE')" ng-controller="userController as c" style="margin-top:9%">

<legend>Wpisz nowe hasło</legend>
 <form name="form" data-ng-submit="!form.$pending && form.$valid && c.changePassword(c.passworddto)" class="form" style="padding: 0px;">
  
  <input type="password" ng-model="c.passworddto.password" ng-minlength="6" name="password" class="textbox" required  placeholder="Hasło"/>
   <p ng-show="form.password.$touched && form.password.$error.required">Wprowadź hasło</p>
   <p ng-show="form.password.$error.minlength">Minimalna długość hasła to 6 znaków</p>
   
  <input type="password"  ng-model="c.passworddto.passwordre" ng-minlength="6" name="passwordRe" class="textbox" confirm-password="c.passworddto.password" required placeholder="Potwierdź hasło"/>
   <p ng-show="form.passwordRe.$touched && form.passwordRe.$error.required"></p>
  
   <div ng-if="form.passwordRe.$touched" ng-messages="form.passwordRe.$error">
    <div ng-message="confirmPassword">Popraw hasło</div>
   </div>
   
  <button type="submit" value="Submit" ng-disabled="form.$invalid || form.$pending" class="button">Zapisz nowe hasło</button>
 </form> 
 
 </div>