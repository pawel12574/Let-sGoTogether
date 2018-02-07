<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div ng-controller="taxiController as c" ng-init="c.getAllTaxi()" style="margin-top:5%">

 <div class="bootstrap snippet">
    <div class="row">
            <h1 class="title">
               Taxi
            </h1>
         <button class="btn btn-primary" data-toggle="modal" data-target="#add">Dodaj</button>
        <div id="no-more-tables">
         <form class="adminManage"> Szukaj 
          <p><input type="text" ng-model="filter" class="textbox"></p>
         </form><br>
            <table class="table-bordered table-striped table-condensed cf" style="width: 50%">
            	<thead class="cf">
        			<tr>
        				<th>Firma</th>
        				<th>Nr telefonu</th>
        				<th>Utworzono</th>
        				<th>Akcja</th>
        			</tr>
        		</thead>
        		<tbody>
        		 
                    <tr ng-repeat="t in c.taxis | filter:filter">
        				<td data-title="Firma">{{t.name}}</td>
        				<td data-title="Nr telefonu">{{t.phoneNumber}}</td>
        				<td data-title="Utworzono">{{t.createdDate}}</td>
        				
        				<td data-title="Akcja">
        				   <button class="btn btn-primary" data-toggle="modal" data-target="#edit"   ng-click="c.select(t)">Edytuj</button>
        				   <button class="btn btn-danger"  data-toggle="modal" data-target="#remove" ng-click="c.select(t)">Usuń</button>
        				</td>
        			</tr>
        			
                  
        		</tbody>
        	</table>
        </div>
    </div>
 </div>

    <!-- Modal -->
	<div id="add" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Dodaj</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">

						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Nazwa firmy</label>
							<div class="col-sm-10">
								<input type="text" class="textbox" id="pwd"
									ng-model="c.taxi.name">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Nr telefonu</label>
							<div class="col-sm-10">
								<input type="text" class="textbox" id="pwd"
									ng-model="c.taxi.phoneNumber">
							</div>
						</div>


						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-info"
									data-dismiss="modal" ng-click="c.save(c.taxi)">Zapisz</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal -->
	<div id="edit" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edycja</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">

						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Nazwa firmy</label>
							<div class="col-sm-10">
								<input type="text" class="textbox" id="pwd"
									ng-model="c.selectedTaxi.name">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Nr telefonu</label>
							<div class="col-sm-10">
								<input type="text" class="textbox" id="pwd"
									ng-model="c.selectedTaxi.phoneNumber">
							</div>
						</div>


						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-info"
									data-dismiss="modal" ng-click="c.update()">Zapisz</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
				</div>
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
					<h4 class="modal-title">Czy na pewno usunąć?</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">{{c.selectedTaxi.name}}</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-danger" data-dismiss="modal"
						ng-click="c.remove()">Tak</button>
					<button type="button" class="btn btn-success" data-dismiss="modal">Nie</button>
				</div>
			</div>

		</div>
	</div>
</div>