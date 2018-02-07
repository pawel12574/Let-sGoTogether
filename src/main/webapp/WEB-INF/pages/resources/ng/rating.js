angular.module('myApp').controller('RatingCtrl', function($scope, $state, $http, $cookies, $timeout, $stateParams) {
	 
	var vm=this;
	vm.rating={rate:'', userId:'', tripId:''};
	vm.avgRating='';
	vm.getAvgRating=getAvgRating;
	
	
	
	function getAvgRating(id){       //user id
		$http.get("rating/user/"+id).
 	     then(function(response) {
				
				vm.avgRating=response.data;
				console.log("rating "+vm.avgRating);
		});
		
	}
	
	//endDate<now
	 $scope.rate = 0;
	 $scope.max = 5;
	 $scope.isReadonly=true;
	 $scope.percent;
	 $scope.hoveringOver = function(value) {
	    $scope.overStar = value;
	    $scope.percent = 100 * (value / $scope.max);
	 };

	 $scope.ratingStates = [
	    {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
	    {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
	    {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
	    {stateOn: 'glyphicon-heart'},
	    {stateOff: 'glyphicon-off'}
	 ];
	 
	 $scope.isTripRated = function(id){
		 $scope.cookie = $cookies.get(id); 
		 console.log("isInCookie "+$scope.cookie);
		 if($scope.cookie){
		    $scope.isReadonly = true;
		 } 
		
	 }
      
      $scope.canRate = function(id){
		 console.log(id);
		 $http.get("rating/isAvaiable/"+id).
 	     then(function(response) {
 	    	  $scope.isReadonly = !response.data;
			  console.log("isReadonly "+ id+" " + $scope.isReadonly);
				
		});
		 
	 }
	 
 
	 $scope.setRating = function(tripId, rate){
		if (!$scope.isReadonly) {
			console.log(rate, tripId);
		    rating = {rate : rate, tripId : tripId};
					$http.post("rating/user/add", rating).then(
							function(response) {

								console.log("rating send");
							});
			$cookies.put($stateParams.id, true);
			$scope.isReadonly = true;
		}
		 
	 }
	 
	 $scope.$watch('rate', function(value, rating) {
		console.log('watch');
		});
	 
});