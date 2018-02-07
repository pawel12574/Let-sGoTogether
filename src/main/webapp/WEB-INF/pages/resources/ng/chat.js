/* angular.module('myApp').controller('chatController', function($scope,  $location, $http) {
	 
       var vm=this;
       vm.messages=[];
       vm.getMessages=getMessages;
       vm.msg={"contents":contents};
    
      
       function getMessages(id){
    	   
    	   $http.get("message/get"+id).
    	   then(function(response) {
				
				vm.messages=response.data;
			   
    		      
    		  }).
    		  error(function(data, status, headers, config) {
    		     alert("Wystąpił błąd");   
    		  });
    	   }
       
       function addMessage(msg){
    	   
    	   $http.post("message/add", msg).
    	   success(function(data, status, headers, config) {
    		      console.log('ok');
    		      
    		  }).
    		  error(function(data, status, headers, config) {
    		     alert("Wystąpił błąd");   
    		  });
       }
       
       
 
     }
 );*/