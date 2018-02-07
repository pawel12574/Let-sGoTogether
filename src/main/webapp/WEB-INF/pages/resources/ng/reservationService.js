angular.module('myApp').service('reservationService', function($stateParams, $http) {

			var vm = this;
			vm.isConfirmed = isConfirmed;
			vm.confirm = confirm;
			vm.remove = remove;
			vm.add = add;

			function add(){
			
				$http.get("reservation/add/" + $stateParams.id).then(
						function(response) {
						
						});
			}
			function isConfirmed() {//for traveler
				$http.get("reservation/isConfirmed/" + $stateParams.id).then(
						function(response) {
						
							vm.confirmed = response.data;

						});
				 return vm.confirmed
			}

			function confirm(idUser) {// for author

				  $http.get("reservation/confirm/" + $stateParams.id + "/"+idUser)
				       .then(function(response) {
							
						}, function(response) {
							alert('Wystąpił błąd');
						});
			}
			  
			function remove(idTrip){
		      $http.get("reservation/remove/"+ idTrip).
	    	   success(function(data, status, headers, config) {
	    		     
	    		  }).
	    		  error(function(data, status, headers, config) {
	    		     alert("Wystąpił błąd");   
	    		  });
			}
			
});