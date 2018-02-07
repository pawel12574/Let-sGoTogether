   angular.module('myApp').controller('tripManageController', function($scope, $timeout, $filter, $location, $http, loader) {
	   
	   var vm=this;
	   $scope.date=new Date();
	   vm.trips=[];
	   vm.selectedTrip={};
	   
	   vm.getAllUserTrip=getAllUserTrip;
	   vm.getAllTrip=getAllTrip;
	   vm.select=select;
       vm.remove=remove;
       vm.update=update;
       vm.updateAdmin=updateAdmin;
	  //vm.trips=getAllUserTrip();
		
       
       
		function getAllUserTrip(){
		   loader.show();
		   $http.get("trip/alluser").
			 then(function(response) {
				console.log('user trip');
				return vm.trips=response.data;
			});
		}
		
		function getAllTrip(){
			$http.get("trip/all").
			 then(function(response) {
				 console.log('all trip');
				return vm.trips=response.data;
			});
		}
		
		function select(trip){
			vm.selectedTrip=angular.copy(trip);
			vm.selectedTrip.tripDate=new Date(trip.tripDate);
			//vm.selectedTrip.tripDate = fixDate(vm.selectedTrip.tripDate);
			vm.selectedTrip.tripDate = $filter("date")(vm.selectedTrip.tripDate, 'yyyy-MM-dd HH:mm');
			
			console.log(trip);
		}
		
		function fixDate(date){
			date = new Date(date);
			date = date.getTime() + 120*60000;//+2h
			return date;
		}
		/*
		function select(trip){
			vm.selectedTrip=trip;
			vm.selectedTrip.tripDate = new Date(vm.selectedTrip.tripDate);
			vm.selectedTrip.tripDate = moment(vm.selectedTrip.tripDate).format("YYYY-MM-DD HH:mm");
			
		}*/
		
		function update(){
			vm.selectedTrip.tripDate = moment($('#tripDate').val());
			
			$http.post("trip/update", vm.selectedTrip).
	  	      success(function(data, status, headers, config) {
	  		      console.log('update trip');
	  		      getAllUserTrip();
	  		   }).
	  		  error(function(data, status, headers, config) {
	  		     alert("Wystąpił błąd");   
	  		  });
			 
			
			
		}
		
		function updateAdmin(){
			vm.selectedTrip.tripDate = moment(vm.selectedTrip.tripDate);
			
			$http.post("trip/update", vm.selectedTrip).
	  	      success(function(data, status, headers, config) {
	  		      console.log('update trip');
	  		      getAllTrip();
	  		   }).
	  		  error(function(data, status, headers, config) {
	  		     alert("Wystąpił błąd");   
	  		  });
			 
			vm.trips=getAllUserTrip();
			
		}
		
		function remove(){
		    console.log(functiontofindIndexByKeyValue(vm.trips, "id", vm.selectedTrip.id));
			vm.trips.splice(functiontofindIndexByKeyValue(vm.trips, "id", vm.selectedTrip.id),1 );
			$http.delete("trip/remove/"+vm.selectedTrip.id).
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