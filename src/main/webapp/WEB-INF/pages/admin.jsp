<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div class="nav-side-menu">
    <div class="brand">Panel administracyjny</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content" style="border-radius: 4px;
    margin-right: 6px;"></i>
  
        <div class="menu-list">
  
            <ul id="menu-content" class="menu-content collapse out">
                <li ui-sref="admin.listTrip">
                  <a>
                  <i class="fa fa-car"></i>Przejazdy
                  </a>
                 </li>

                
                 <li ui-sref="admin.listTripSd">
                  <a>
                   <i class="fa fa-car"></i>Przejazdy poszukiwane
                  </a>
                 </li>

                 <li ui-sref="admin.listUsers">
                  <a>
                  <i class="glyphicon glyphicon-user"></i>Użytkownicy
                  </a>
                 </li>
                 
                 <li ui-sref="admin.listTaxi">
                  <a>
                  <i class="glyphicon glyphicon-earphone"></i>Taxi
                  </a>
                 </li>
                 
                 
            </ul>
     </div>
</div>

<div ui-view class="admin-ui-view"></div> 
