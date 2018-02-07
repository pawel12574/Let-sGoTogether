angular.module('myApp').controller('ReservationController', function($rootScope, $timeout, $stateParams, $scope, $http) {
 
    var vm=this;
    vm.isConfirmed=isConfirmed;
    vm.confirm=confirm;
    vm.isConfirmedTimeout = isConfirmedTimeout;   
    
    isConfirmed();
  
    
    function isConfirmed(){//for traveler
     $http.get("reservation/isConfirmed/"+$stateParams.id).
	   then(function(response) {
		    console.log("check reservation");
		    vm.confirmed = response.data;
		    
	   });
    }
    
    function isConfirmedTimeout(){
      $timeout(function(){check()}, 100);
    	function check(){
    	  $http.get("reservation/isConfirmed/"+$stateParams.id).
      	   then(function(response) {
      		    console.log("check reservation timeout");
      		    vm.confirmed = response.data;
      		    
      	   });
      }
    }
    
    function confirm(idUser){//for author
    	
        $http.get("reservation/confirm/"+$stateParams.id+"/"+idUser).
   	     then(function(response) {
   	    	console.log("confirmed "+ idUser);
   	    	vm.isDisabled=true;
   	    }, function(response) {
	      alert('Wystąpił błąd');
	      });
    }
  
  
});