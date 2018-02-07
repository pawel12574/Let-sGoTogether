 angular.module('myApp').controller('userController', function($scope, $rootScope, $timeout, $location, $http, $filter, toastr, Session) {
	 
       var vm=this;
       vm.user={username:'', password:'', name:'', surname:'', birthDate:'', phoneNumber:null}
       vm.passworddto={password:'', passworddre:''}
       vm.username='';
       $rootScope.session=Session;
       
       
       vm.registerUser=registerUser;
       vm.changePassword=changePassword;
       vm.changePasswordv2=changePasswordv2;
       vm.forgotPassword=forgotPassword;
       vm.changeData=changeData;
       vm.loggedUser=angular.copy(Session.data); //copy value instead reference
       vm.loggedUser.birthDate = $filter("date")(vm.loggedUser.birthDate, 'yyyy-MM-dd');
       
       
      
   
       function changeData(loggedUser){
    	   loggedUser.birthDate= $('#dateinput').val(); //update ng-model??check if document get element by id work
    	   
    	   $http.post("user/update", loggedUser).
    	   success(function(data, status, headers, config) {
    		   console.log("change data ok")
    		   Session.data=vm.loggedUser;
    		  }).
    		  error(function(data, status, headers, config) {
    		     alert("Wystąpił błąd");   
    		  });
       }
       
       function forgotPassword(username){
    	   toastr.info("Na podany adres email wysłano link resetujący hasło");
    	   $http.get("resetPassword/"+username).
    	   success(function(data, status, headers, config) {
    		    
    		    $timeout(3000);
    		   
    		    $location.path("/");
    		    
    		  });
    	   
       }
      
      
       function registerUser(user){
    	   
    	   user.birthDate=$scope.birthDate;
    	   toastr.info("Na adres emial wysłano link aktywujący konto!");
    	   $http.post("user/create", user).
    	   success(function(data, status, headers, config) {
    		   
    		   $location.path("/");
    		      
    		  }).
    		  error(function(data, status, headers, config) {
    		     alert("Wystąpił błąd");   
    		  });
    	   
       }
        
       function changePassword(passworddto){
    	   toastr.success("Hasło zostało zmienione, zaloguj się ponownie");
    	   $http.post("user/changePassword", passworddto).
    	      success(function(data, status, headers, config) {
    		 
    		   $timeout(3000);
    		   $location.path("/login");
    		      
    		  }).
    		  error(function(data, status, headers, config) {
    		     alert("Wystąpił błąd");   
    		  });
    	   
       }
       
       
       function changePasswordv2(passworddto){
    	   
    	   $http.post("user/changePasswordv2", passworddto).
    	      success(function(data, status, headers, config) {
    	    	  location.reload();
    
    		  }).
    		  error(function(data, status, headers, config) {
    			  toastr.error("Nieprawidłowe dotychczasowe hasło");   
    		  });
    	   
       }
 
     }
 ).
directive('customzdatetime', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            element.datetimepicker({
               
            }).on('dp.change', function (e) {
                ngModelCtrl.$setViewValue(e.date);
                scope.$apply();
            });
        }
    };
});