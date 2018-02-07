<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="nav-side-menu">
    <div class="brand">Ustawienia konta</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content" style="border-radius: 4px;
    margin-right: 6px;"></i>
  
        <div class="menu-list">
  
            <ul id="menu-content" class="menu-content collapse out">
                <li ui-sref="user.password">
                  <a>
                  <i class="fa  fa-lg"><span class="glyphicon glyphicon-user"></span></i>Zmiana hasła
                  </a>
                 </li>

                
                 <li ui-sref="user.avatar">
                  <a>
                  <i class="fa  fa-lg"><span class="glyphicon glyphicon-user"></span></i>Avatar
                  </a>
                 </li>

                 <li ui-sref="user.data">
                  <a>
                  <i class="fa  fa-lg"><span class="glyphicon glyphicon-user"></span></i>Edycja danych
                  </a>
                 </li>
            </ul>
     </div>
</div>

<div ui-view class="user-ui-view" ></div>

 