   angular.module('myApp').controller('taxiController', function($scope, $timeout, $location, $http, loader) {
	   
	   var vm=this;
	  
	   vm.taxis=getAllTaxi();
	   vm.taxi={};
	   vm.selectedTaxi={};
	   
	   vm.getAllTaxi=getAllTaxi;
	   vm.save=save;
	   vm.select=select;
       vm.remove=remove;
       vm.update=update;
       vm.changeTaxi=changeTaxi;
       vm.callTo=callTo;
       
       
       function callTo(number){
    	   console.log(number);
    	   window.open('tel:'+number);
    	  
       } 
        
       
       function changeTaxi(taxi){
    		
    		$scope.taxi=taxi;
    		console.log($scope.taxi);
    	}  
          
	   function getAllTaxi(){
		   
		   $http.get("taxi/get/all").
			 then(function(response) {
				
				 vm.taxis=response.data;
				 $scope.taxi = vm.taxis[0];
			});
		}
		
		function select(taxi){
			vm.selectedTaxi=angular.copy(taxi);
			console.log(taxi);
			
		}
		
		function save(taxi){
			$http.post("taxi/add", taxi).
	  	      success(function(data, status, headers, config) {
	  		      console.log('save taxi');
	  		      vm.taxi=null;
	  		      getAllTaxi();
	  		   }).
	  		  error(function(data, status, headers, config) {
	  		     alert("Wystąpił błąd");   
	  		  });
			 	
		}
		
		function update(){
			$http.post("taxi/update", vm.selectedTaxi).
	  	      success(function(data, status, headers, config) {
	  		      console.log('update taxi');
	  		      getAllTaxi();
	  		   }).
	  		  error(function(data, status, headers, config) {
	  		     alert("Wystąpił błąd");   
	  		  });
			 	
		}
		
		function remove(){
		    console.log(functiontofindIndexByKeyValue(vm.taxis, "id", vm.selectedTaxi.id));
			vm.taxis.splice(functiontofindIndexByKeyValue(vm.taxis, "id", vm.selectedTaxi.id),1 );
			$http.delete("taxi/remove/"+vm.selectedTaxi.id).
	 	      success(function(data, status, headers, config) {
	 		      console.log('usunięto Taxi');
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