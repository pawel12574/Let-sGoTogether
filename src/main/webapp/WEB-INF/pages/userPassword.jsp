<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div ng-controller="userController as c">



 <form name="form" data-ng-submit="!form.$pending && form.$valid && c.changePasswordv2(c.passworddto)" class="form">
    <legend>Zmień hasło</legend>
  
  <input type="password" ng-model="c.passworddto.password" name="password" required ng-model-options="{ debounce: 500 }" placeholder="Stare hasło" class="textbox" />
   <div ng-messages="form.password.$error" >
    <p ng-show="form.password.$touched && form.password.$error.required">Wprowadź hasło</p>
   </div>
   
   <input type="password" ng-model="c.passworddto.passwordre" name="passwordre" ng-minlength="6" required ng-model-options="{ debounce: 500 }" placeholder="Nowe hasło" class="textbox" />
   <div ng-messages="form.passwordre.$error" >
    <p ng-show="form.passwordre.$touched && form.passwordre.$error.required">Wprowadź nowe hasło</p>
    <p ng-show="form.passwordre.$error.minlength">Minimalna długość hasła to 6 znaków</p>
   </div>
   
  <button type="submit" value="Submit" ng-disabled="form.$invalid || form.$pending" class="button">Zmień</button>
 </form>
</div>  

