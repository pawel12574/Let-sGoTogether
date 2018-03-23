angular.module('myApp').controller('addTripSdController', function($scope, $timeout, $location, $http, toastr) {
	
		
	 var vm=this;
	 vm.addTripSd=addTripSd; //method
	 var trip={};
	 
	function addTripSd(){
		 
		  trip={
				 fromLat:document.getElementById('fromLat').value,
		         fromLng:document.getElementById('fromLng').value,
		         toLat:document.getElementById('toLat').value,
		         toLng:document.getElementById('toLng').value,
		         dateBegin:moment(document.getElementById('tripDateBegin').value),
		         dateEnd:moment(document.getElementById('tripDateEnd').value),
		         freeSeat:document.getElementById('freeSeat').value,
		         fromPlace:document.getElementById('fromAddress').value,
		         toPlace:document.getElementById('destAddress').value
		        // description:document.getElementById('description').value
		         };
		 
		 $http.post("tripSd/addTrip", trip).
  	      success(function(data, status, headers, config) {
  		      console.log('ok');
  		      toastr.info("Zostaniesz powiadomiony o nowych przejazdach");
  		      vm.isDisabled=true;
  		  }).
  		  error(function(data, status, headers, config) {
  		     alert("Wystąpił błąd");   
  		  });
		 
	 }
	     
	   function initialize() {
			var map;  
			var directionsService = new google.maps.DirectionsService();
			var directionsDisplay = new google.maps.DirectionsRenderer({
			    draggable: true
			  });

		     map = new google.maps.Map(document.getElementById("map"),{
				 //zoom: 12,
				 //mapTypeId:'terrain',
				// center: {lat: 53.1537918, lng: 23.1010507}
			 });
			 google.maps.event.trigger(this.map, 'resize');
			 directionsDisplay.setMap(map);
			  
			
			  document.getElementById('fromAddress').value='Wiejska, Białystok, Polska';
			  document.getElementById('destAddress').value='Warszawska 6, 15-062 Białystok, Polska';
		      calculateAndDisplayRoute(directionsService, directionsDisplay);
		      var onChangeHandler = function() {
		    	   
		    	    calculateAndDisplayRoute(directionsService, directionsDisplay);
		    	  };
		      document.getElementById('fromAddress').value='';
			  document.getElementById('destAddress').value='';  
		      document.getElementById('fromAddress').addEventListener('change', onChangeHandler);
		      document.getElementById('destAddress').addEventListener('change', onChangeHandler);


		      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
		    	 
			      directionsService.route({
					   origin:  document.getElementById('fromAddress').value,
					   destination: document.getElementById('destAddress').value,
					   travelMode: google.maps.DirectionsTravelMode.DRIVING
					    
			       }, function(response, status) {
			       if (status == google.maps.DirectionsStatus.OK) {
			        directionsDisplay.setDirections(response);
			        google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
			         directions = directionsDisplay.getDirections();
			        
			    // Display the distance:
					document.getElementById('distance').value = directions.routes[0].legs[0].distance.value;
				// Display the duration:
					document.getElementById('duration').value = directions.routes[0].legs[0].duration.value;
			        document.getElementById('fromLat').value = directions.routes[0].legs[0].start_location.lat();
			        document.getElementById('fromLng').value = directions.routes[0].legs[0].start_location.lng();
			        document.getElementById('toLat').value = directions.routes[0].legs[0].end_location.lat();
			        document.getElementById('toLng').value = directions.routes[0].legs[0].end_location.lng();
			      
			     
			        var fromAddress = document.getElementById('fromLat').value+", "+ document.getElementById('fromLng').value;
			        var destAddress = document.getElementById('toLat').value+", "+ document.getElementById('toLng').value;
			       
			        var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + fromAddress + "&key=AIzaSyAHKAiVjd7CTF1M5PF7g2G_i3cftTquKiU";
			        $.getJSON(url, function (data) {
			           
			                var address = data.results[0].formatted_address;
			                document.getElementById('fromAddress').value = address;
			            
			        })
			        url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + destAddress + "&key=AIzaSyAHKAiVjd7CTF1M5PF7g2G_i3cftTquKiU";
			        $.getJSON(url, function (data) {
			            
			                var address = data.results[0].formatted_address;
			                document.getElementById('destAddress').value = address;
			            
			        })
			        
			      })
			    } else {
			      //alert("directions request failed:" + status)
			    }
			  });
		      }
		
		}
		$scope.initMap = function(){
			document.getElementById('map');
			$timeout(initialize());
			google.maps.event.addDomListener(window, "load", initialize);
			
		}
	
});
