   angular.module('myApp').controller('userManageController', function($scope, $timeout, $location, $http) {
	   
	   var vm=this;
	   $scope.date=new Date();
	   
	   vm.getAllUsers=getAllUsers;
	   vm.selectedUser={};
	   vm.users=[];
	  
	   vm.select=select;
	   vm.lockUser=lockUser;
       vm.remove=remove;
       vm.changePrivileges=changePrivileges;
	  
	   
		
		function getAllUsers(){
			$http.get("users/all").
			 then(function(response) {
				 console.log('all users');
				return vm.users=response.data;
			});
		}
		
		function select(user){
			  vm.selectedUser = angular.copy(user);
	          console.log(vm.selectedUser);
		 }
		
        function remove(){
        	vm.users.splice(functiontofindIndexByKeyValue(vm.users, "id", vm.selectedUser.id),1 );
			
			$http.delete("users/remove/"+vm.selectedUser.id).
	 	      success(function(data, status, headers, config) {
	 		      
	 		  }).
	 		  error(function(data, status, headers, config) {
	 		     alert("Wystąpił błąd");   
	 		  });
			
			vm.selectedUser={};
		}
		
		 function lockUser(user){// obiekt przechodzi, id nie??? 
			 user.accountNonLocked = !user.accountNonLocked;
			 
			 $http.get("users/lock/"+user.id).
	 	      success(function(data, status, headers, config) {
	 		      console.log(user);
	 		      
	 		   }).
	 		  error(function(data, status, headers, config) {
	 		     alert("Wystąpił błąd");   
	 		  });
		 }
		 
		 function changePrivileges(user){
			 console.log(user);
		      if(user.role.role<2)
		    	  user.role.role++;
		      else{
		    	  user.role.role--;
		      }
		      
			 $http.get("users/changePrivileges/"+user.id).
	 	      success(function(data, status, headers, config) {
	 		      
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
		
		