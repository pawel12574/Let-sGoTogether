angular.module('myApp').directive('emailUnique', function($http, $q) {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelController) {
            ngModelController.$asyncValidators.emailUnique = function(modelValue, viewValue) {
            	var vm=this;
            	vm.user={};
            	var value = modelValue || viewValue;
            	
            	return $http.post('user/emailIsExist',{username: viewValue}).
            	 then(function(response) {
            	       vm.user=response.data;
            	       if(vm.user.username===viewValue)
            	       return $q.reject('exists');
            	       else return true;
            	     }, function rejected() {
            	       //email does not exist, return true
            	       return true;
            	     });
                    
                
             };
         }
     };
 })
 
 .directive('emailNotExist', function($http, $q) {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelController) {
            ngModelController.$asyncValidators.emailNotExist = function(modelValue, viewValue) {
            	var vm=this;
            	vm.user={};
            	var value = modelValue || viewValue;
            	
            	return $http.post('user/emailIsExist',{username: viewValue}).
            	 then(function(response) {
            	       vm.user=response.data;
            	       if(vm.user.username!=viewValue)
            	       return $q.reject('exists');
            	       else return true;
            	     }, function rejected() {
            	       
            	       return true;
            	     });
                    
                
             };
         }
     };
 })
 
 
 .directive('confirmPassword', function(){
	 
	 return {
		    require: "ngModel",
		    scope: {
		        otherModelValue: "=confirmPassword"
		    },
		    link: function(scope, element, attributes, ngModel) {

		        ngModel.$validators.confirmPassword = function(modelValue) {
		            return modelValue == scope.otherModelValue;
		        };

		        scope.$watch("otherModelValue", function() {
		            ngModel.$validate();
		        });
		    }
		  };
	 
	 
 });