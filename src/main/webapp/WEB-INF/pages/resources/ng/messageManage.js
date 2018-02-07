angular.module('myApp').controller('messageManageController', function($scope, $rootScope, $timeout, $location, $stateParams, $http, loader) {
	   

    var vm=this;
    vm.select=select;
    vm.updateMessage=updateMessage;
    
    
    
    function select(message){
    	
		vm.selectedMessage=angular.copy(message);
		
    }
    
    function updateMessage(){
		$http.post("message/update", vm.selectedMessage).
  	      success(function(data, status, headers, config) {
  	    	$http.get("chat/get/"+$stateParams.id).
 	           then(function(response) {
 	           $scope.messages=response.data;
				
		    });
  		      //get message przerzucić do serwisu
  		   }).
  		  error(function(data, status, headers, config) {
  		     alert("Wystąpił błąd");   
  		  });
		 
		
		
	}
})

.service('messageService', function($stateParams, $http) {
   
	  var vm=this;
	  vm.getChat=getChat;
	  vm.addMessage=addMessage;
      vm.message={contents:'', tripId:$stateParams.id};
      
    
      function getChat(){
	      $http.get("chat/get/"+$stateParams.id).
	         then(function(response) {
	    	  return response.data;
			
	      });
	  }
	
	  function addMessage(message){
    	   
    	     $http.post("message/add", message).
    	     success(function(data, status, headers, config) {
    		        console.log('ok');
    		        getChat();
    		        
    		    }).
    		    error(function(data, status, headers, config) {
    		       alert("Wystąpił błąd");   
    		    });
      }

});