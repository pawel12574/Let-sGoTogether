<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div ng-controller="tripManageController as c" ng-init="c.getAllUserTrip()" style="margin-top:10%">
 <div ng-show="!c.trips.length"><h4> <div id="loader" loader-css="ball-pulse" style="display:none"></div><div id="afterLoader" style="display:none; text-align: center;">Brak dodanych przejazdów</div></h4></div> 
  <div ng-repeat="t in c.trips | orderBy: 'tripDate'">

   <div class="grids-example" style="background: rgb(239, 236, 236); margin: 4px;">
     <div class="pure-g">
         <div class="pure-u-1-3" style="text-align:left; padding-left:7px"><p>Z {{t.fromPlace}}<br>Do {{t.toPlace}}</p></div>
         <div class="pure-u-1-3"><p>{{t.date}}</p></div>
         <div class="pure-u-1-3"><p><a ui-sref="tripDetails({id :t.id})">
          <button type="button" class="btn btn-info">Przejdź</button>
          </a>
          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#edit" ng-click="c.select(t)">edytuj</button>
          <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#remove" ng-click="c.select(t)">usuń</button>
          </p>
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
      <label class="control-label col-sm-2" for="tripDate">Data i czas</label>
      <div class="col-sm-10">
        <input type="text" id="tripDate" ng-model="c.selectedTrip.tripDate" class="textbox" data-date-format="YYYY-MM-DD HH:mm" required>
      </div>
    </div> 
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Skąd</label>
      <div class="col-sm-10">          
        <input type="text" class="textbox" id="pwd"  ng-model="c.selectedTrip.fromPlace">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Dokąd</label>
      <div class="col-sm-10">          
        <input type="text" class="textbox" id="pwd"  ng-model="c.selectedTrip.toPlace">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Opis</label>
      <div class="col-sm-10">          
        <input type="text" class="textbox" id="pwd"  ng-model="c.selectedTrip.description">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Typ przejazdu</label>
      <div class="col-sm-10">  
        <div>Taxi        
        <input type="radio"  id="pwd" ng-value="true"  ng-model="c.selectedTrip.taxiTrip" style="margin-left:46px"></div>
        <div>Samochód
        <input type="radio"  id="pwd" ng-value="false" ng-model="c.selectedTrip.taxiTrip"></div>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd"><br>Cena</label>
      <div class="col-sm-10">          
        <input type="text" class="textbox" id="pwd" ng-pattern="/^[0-9]*$/" ng-model="c.selectedTrip.price" placeholder="zł" style="width:50px"> zł
      </div>
    </div>
    
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success" data-dismiss="modal" ng-click="c.update()">Zapisz</button>
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
        <h4 class="modal-title">Czy na pewno usunąć przejazd</h4>
      </div>
      <div class="modal-body">
       <form class="form-horizontal">
    {{c.selectedTrip.fromPlace}} -> {{c.selectedTrip.toPlace}}
    
  
    
  </form>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-default" data-dismiss="modal" ng-click="c.remove()">Tak</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Nie</button>
      </div>
    </div>

  </div>
</div>
</div>
<script type="text/javascript">
    $(function () {
    	 $('#tripDate').datetimepicker({
         	locale: 'pl',
         	inline: true,
         	sideBySide: true
         });
         $('#tripDate').data("DateTimePicker").minDate(new Date());
       
    });
</script>