   angular.module('myApp').controller('tripSdManageController', function($scope, $timeout, $location, $http, loader) {
	   
	   var vm=this;
	   $scope.date=new Date();
	   vm.getAllUserTripSd=getAllUserTripSd;
	   vm.getAllTripSd=getAllTripSd;
	   vm.selectedTripSd={};
	  
	   vm.select=select;
	   vm.remove=remove;
       vm.update=update;
       vm.updateAdmin=updateAdmin;
	  
      
       
	   function getAllUserTripSd(){
		 loader.show();
  		 $http.get("tripsd/alluser").
  		  then(function(response) {
  			
  			return vm.allUserTripSd = response.data;
  			
  		 });
    	 }
		
		function getAllTripSd(){
			$http.get("tripssd/all").
			 then(function(response) {
				console.log('all tripSd');
				return vm.trips = response.data;
			});
		}
		
		function select(tripSd){
			  vm.selectedTripSd= angular.copy(tripSd);
			  vm.selectedTripSd.tripDate=new Date(tripSd.tripDate);
			  console.log(vm.selectedTripSd);
		 }
		
		function update(){
			$http.post("tripSd/update", vm.selectedTripSd).
	  	      success(function(data, status, headers, config) {
	  		      console.log('update trip');
	  		      getAllUserTripSd();
	  		   }).
	  		  error(function(data, status, headers, config) {
	  		     alert("Wystąpił błąd");   
	  		  });
			 
		}
		
		function updateAdmin(){
			$http.post("tripSd/update", vm.selectedTripSd).
	  	      success(function(data, status, headers, config) {
	  		      console.log('update trip');
	  		      getAllTripSd();
	  		   }).
	  		  error(function(data, status, headers, config) {
	  		     alert("Wystąpił błąd");   
	  		  });
			
		}
		
		 function remove(id){// obiekt przechodzi, id nie do sprawdzenia
			 console.log(vm.selectedTripSd);
			 vm.allUserTripSd.splice(functiontofindIndexByKeyValue(vm.allUserTripSd, "id", vm.selectedTripSd.id),1 );
			 $http.delete("tripSd/remove/"+vm.selectedTripSd.id).
	 	      success(function(data, status, headers, config) {
	 		      console.log('usunięto');
	 		   }).
	 		  error(function(data, status, headers, config) {
	 		     alert("Wystąpił błąd");   
	 		  });
		 }
		 
		 function functiontofindIndexByKeyValue(arraytosearch, key, valuetosearch) {
			    for (var i = 0; i < arraytosearch.length; i++) {
				    if (arraytosearch[i][key] == valuetosearch) {
				       return i;
				    }
				}
				return null;
		 }
});
		
		