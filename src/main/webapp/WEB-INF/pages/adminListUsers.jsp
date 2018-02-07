<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div ng-controller="userManageController as c" ng-init="c.getAllUsers()" style="margin-top:5%">


 <div class=" bootstrap snippet">
    <div class="row">
            <h1 class="title">
               Użytkownicy
            </h1>
        <form class="adminManage"> 
          Szukaj <p><input type="text" ng-model="filter" class="textbox"></p>
       </form>
        <div id="no-more-tables">
            <table class="table-bordered table-striped table-condensed cf">
            	<thead class="cf">
        			<tr>
        				<th>Email</th>
        				<th>Nazwisko</th>
        				<th>Imię</th>
        				<th>Wiek</th>
        				<th>Data rejestracji</th>
        				<th>Status</th>
        				<th>Akcja</th>
        			</tr>
        		</thead>
        		<tbody>
        		 
                    <tr ng-repeat="u in c.users | filter:filter">
        				<td data-title="Email">{{u.username}}</td>
        				<td data-title="Imię">{{u.name}}</td>
        				<td data-title="Nazwisko">{{u.surname}}</td>
        				<td data-title="Wiek">{{u.age}}</td>
        				<td data-title="Data rejestracji">{{u.created}}</td>
        				<td data-title="Status"><div ng-if="u.accountNonLocked"><button class="btn btn-warning" ng-click="c.lockUser(u)">Zablokuj</button></div><div ng-if="!u.accountNonLocked"><button  class="btn btn-danger" ng-click="c.lockUser(u)">Odblokuj</button></div></td>
        				<td data-title="Akcja">
        				   <button class="btn btn-primary" ng-click="c.changePrivileges(u)"><div ng-if="u.role.role==2">+Admin</div><div ng-if="u.role.role==1">-Admin</div></button>
        		     <!--  <button class="btn btn-danger" data-toggle="modal" data-target="#remove" ng-click="c.select(u)">Usuń</button>  -->
        				</td>
        				
        			</tr>
        			
                  
        		</tbody>
        	</table>
        </div>
    </div>
 </div>
 


<!-- Modal -->
 <div id="remove" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Czy na pewno usunąć użytkownika</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
          {{c.selectedUser.username}}
    
  
    
  </form>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger" data-dismiss="modal" ng-click="c.remove()">Tak</button>
        <button type="button" class="btn btn-success" data-dismiss="modal">Nie</button>
      </div>
    </div>

  </div>
 </div>
</div>