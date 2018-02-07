<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div ng-controller="tripSdManageController as c" ng-init="c.getAllUserTripSd()" style="margin-top:10%;">
 <div ng-show="!c.allUserTripSd.length"><h4><div id="loader" loader-css="ball-pulse" style="display:none"></div><div id="afterLoader" style="display:none; text-align: center;">Brak dodanych przejazdów</div></h4> </div>
  <div ng-repeat="t in c.allUserTripSd"> 
 
    <div class="grids-example" style="background: rgb(239, 236, 236); margin: 4px;">
      <div class="pure-g">
          <div class="pure-u-1-3" style="text-align:left; padding-left:7px"><p>Z {{t.fromPlace}}<br>Do {{t.toPlace}}</p></div>
          <div class="pure-u-1-3"><p>{{t.dateBegin}} - {{t.dateEnd}}</p></div>
          <div class="pure-u-1-3"><p><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#remove" ng-click="c.select(t)">Usuń</button></p></div>
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
    
        {{c.selectedTripSd.fromPlace}} -> {{c.selectedTripSd.toPlace}}
  
    
       </form>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger" data-dismiss="modal" ng-click="c.remove(selectedTripSd)">Tak</button>
        <button type="button" class="btn btn-success" data-dismiss="modal">Nie</button>
      </div>
    </div>

  </div>
 </div>

</div>
