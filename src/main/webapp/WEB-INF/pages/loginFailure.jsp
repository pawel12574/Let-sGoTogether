<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<br><br>

	<form  action="j_spring_security_check" method="POST" class="form">
		
			<legend>Nieprawidłowy login lub hasło, spróbuj ponownie</legend>
			
		
			<label class="control-label" for="j_username">Email</label>:
			<input id="j_username" name="j_username" class="textbox" type="text" />
			
			
			
			<label for="j_password">Hasło</label>:
			<input id="j_password" name="j_password" class="textbox" type="password"/>
			
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<p><input type="submit" class="button" value="Zaloguj się"/></p>
			
	</form>
	
