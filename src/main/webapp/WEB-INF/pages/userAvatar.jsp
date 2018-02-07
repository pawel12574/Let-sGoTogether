<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div ng-controller="uploadAvatarController as c" >
	
	<div class="form">
		<legend>Dodaj avatar</legend>
		<input type="file" file-model="myFile" check-upload-picture="" />
		
		<button ng-click="c.upload()" ng-show="myFile" class="button" style="height: inherit; margin-top: 5px;
		 margin-bottom: 5px;">Dodaj</button><div ng-show="myFile">Rozmiar {{size | number:2 }}KB</div>
		(maksymalnie 15KB)
	</div>
	
	<div class="form">
        <legend>Usuń avatar</legend>
        
		<button ng-click="c.defaultAvatar()" class="button" ng-disabled="c.isDisabled" style="height: inherit;">Usuń</button>
		<div ng-cloak ng-if="session.data.avatar">
           <img ng-src="data:image/JPEG;base64,{{session.data.avatar}}"  />
        </div>
        
		<div ng-show="c.isDeleted"><label>Usunięto avatar</label></div>
		
	</div>
</div>