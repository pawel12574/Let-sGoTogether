angular.module('myApp').controller('findTripController', function($rootScope, $scope, $timeout, $location, $http, loader) {
	   
	        var vm = this;
		    $rootScope.tripList = [];
			$scope.sendButton = 'Szukaj';
            $scope.date = new Date();
            $scope.result=false;
           /* var isChromium = window.chrome,
            winNav = window.navigator,
            vendorName = winNav.vendor,
            isOpera = winNav.userAgent.indexOf("OPR") > -1,
            isIEedge = winNav.userAgent.indexOf("Edge") > -1,
            isIOSChrome = winNav.userAgent.match("CriOS");

        if(isIOSChrome){
           // is Google Chrome on IOS
        
        } else { 
           alert("Używasz przeglądarki, która nie gwarantuje poprawnego wyświetlania elementów aplikacji.\nZalecana przeglądarka to Google Chrome");
        }
           */
            $scope.submit = function() {
				loader.show();
				console.log(document.getElementById("date").value);
				var tripForm = {
						"fromLat" : document.getElementById("fromLat").value,
						"fromLng" : document.getElementById("fromLng").value,
						"toLat" : document.getElementById("toLat").value,
						"toLng" : document.getElementById("toLng").value,
						"rangeFrom" : document.getElementById("rangeFrom").value,
						"rangeTo" : document.getElementById("rangeTo").value,
						"date": document.getElementById("date").value
				};
				console.log(tripForm);
				var response = $http.post("trip/find", tripForm);
				
				response.success(function(data, status, headers, config) {
					$rootScope.tripList.push(data);
					$rootScope.trips = angular.copy(data);
					if($rootScope.trips)
						$scope.result=true;
					
				 //   $location.path("/trips");
					
				});
				response.error(function(data, status, headers, config) {
					alert("Wystąpił błąd");
				});
				
				//Empty list data after process
				$rootScope.tripList = [];
				//document.getElementById("loader").style.display = "none";
				
			};
			
			
			var rangeInputA = document.getElementById("rangeFrom");
			var rangeInputB = document.getElementById("rangeTo");
			
			
			//SEARCH TRIP
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
			  
			/*var request = {
					    origin: new google.maps.LatLng(53.1537918, 23.1010507),
					    destination: new google.maps.LatLng(53.117996, 23.1508361),
					    travelMode: google.maps.DirectionsTravelMode.DRIVING
					  };*/
					 
		    //calculateAndDisplayRoute(directionsService, directionsDisplay);
			  document.getElementById('from').value='Wiejska, Białystok, Polska';
			  document.getElementById('to').value='Warszawska 6, 15-062 Białystok, Polska';
		      calculateAndDisplayRoute(directionsService, directionsDisplay);
		      var onChangeHandler = function() {
		    	   
		    	    calculateAndDisplayRoute(directionsService, directionsDisplay);
		    	  };
		      document.getElementById('from').value='';
			  document.getElementById('to').value='';  
		      document.getElementById('from').addEventListener('change', onChangeHandler);
		      document.getElementById('to').addEventListener('change', onChangeHandler);
		      
		      var cityCircleAa = new google.maps.Circle({
		            strokeColor: '#09D05B',
		            strokeOpacity: 0.8,
		            strokeWeight: 2,
		            fillColor: '#09D05B',
		            fillOpacity: 0.35,
		            map: map,
		            center:({lat: 53.1164578, lng: 23.144865600000003}),
		            radius:500
		          });
			  
			   var cityCircleBb = new google.maps.Circle({
		            strokeColor: '#FF0000',
		            strokeOpacity: 0.8,
		            strokeWeight: 2,
		            fillColor: '#FF0000',
		            fillOpacity: 0.35,
		            map: map,
		            center:({lat: 53.1357143, lng: 23.166683499999976}),
		            radius:500
		          });
			  
			    rangeInputA.addEventListener('input', function() {
		           cityCircleAa.setRadius(Number(this.value)); 
		          
		         });
			    rangeInputB.addEventListener('input', function() {
			           cityCircleBb.setRadius(Number(this.value)); 
			         });
			    var cityCircleA = new google.maps.Circle({
		            strokeColor: '#09D05B',
		            strokeOpacity: 0.8,
		            strokeWeight: 2,
		            fillColor: '#09D05B',
		            fillOpacity: 0.35,
		            map: map,
		            radius:500
		          });
			    var cityCircleB = new google.maps.Circle({
		            strokeColor: '#FF0000',
		            strokeOpacity: 0.8,
		            strokeWeight: 2,
		            fillColor: '#FF0000',
		            fillOpacity: 0.35,
		            map: map,
		            radius:500
		          });
			    
		 function calculateAndDisplayRoute(directionsService, directionsDisplay) {
		    	  // niech wyczysci cirlcle przed wywołaniem
				  rangeInputA.addEventListener('input', function() {
			           cityCircleA.setRadius(Number(this.value)); 
			         });
			        
			      rangeInputB.addEventListener('input', function() {
			           cityCircleB.setRadius(Number(this.value)); 
			         });
			    
				    
			   directionsService.route({
					   origin:  document.getElementById('from').value,
					   destination: document.getElementById('to').value,
					   travelMode: google.maps.DirectionsTravelMode.DRIVING
					    
			     }, function(response, status) {
			      if (status == google.maps.DirectionsStatus.OK) {
			       directionsDisplay.setDirections(response);
			       google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
			        directions = directionsDisplay.getDirections();
			        
			        cityCircleAa.setVisible(false);//disable fake circle when drag
			        cityCircleBb.setVisible(false);
			        cityCircleA.setCenter({lat: directions.routes[0].legs[0].start_location.lat(), lng: directions.routes[0].legs[0].start_location.lng()});
			        cityCircleB.setCenter({lat: directions.routes[0].legs[0].end_location.lat(), lng: directions.routes[0].legs[0].end_location.lng()});
			       
			     // document.getElementById('from').value = directions.routes[0].legs[0].start_location.lat();
			        document.getElementById('fromLat').value = directions.routes[0].legs[0].start_location.lat();
			        document.getElementById('fromLng').value = directions.routes[0].legs[0].start_location.lng();
			        document.getElementById('toLat').value = directions.routes[0].legs[0].end_location.lat();
			        document.getElementById('toLng').value = directions.routes[0].legs[0].end_location.lng();
			      
			     
			       
			    //    google.maps.event.addListener(directionsDisplay,'directions_changed', function() {
			   //     	 
			   //     	   cityCircleA.setCenter({lat: directions.routes[0].legs[0].start_location.lat(), lng: directions.routes[0].legs[0].start_location.lng()});
			   //     	   cityCircleB.setCenter({lat: directions.routes[0].legs[0].end_location.lat(), lng: directions.routes[0].legs[0].end_location.lng()});
			  //         });
			        
			        var fromAddress = document.getElementById('fromLat').value+", "+ document.getElementById('fromLng').value;
			        var destAddress = document.getElementById('toLat').value+", "+ document.getElementById('toLng').value;
			       
			        var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + fromAddress + "&key=AIzaSyCGq-qP3nxn_YmZQAPQ4IYuIcCZGK4X2Wk";
			        $.getJSON(url, function (data) {
			           console.log(data);
			                var address = data.results[0].formatted_address;
			                document.getElementById('from').value = address;
			            
			        })
			        url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + destAddress + "&key=AIzaSyCGq-qP3nxn_YmZQAPQ4IYuIcCZGK4X2Wk";
			        $.getJSON(url, function (data) {
			            console.log(data);
			                var address = data.results[0].formatted_address;

			                document.getElementById('to').value = address;
			            
			        })
			        
			        
			        
			      })
			    } else {
			      //alert("directions request failed:" + status)
			    }
			  });
		      }
		}
			
		$scope.initMap = function(){
			document.getElementById('map').style.display="block";
			$timeout(initialize());
			google.maps.event.addDomListener(window, "load", initialize);
			
		}
			
})
				
.controller('tripController', function($scope, $rootScope, $stateParams, $timeout, $location, $http, loader, Session, reservationService){
			
			var vm = this;
			
			vm.addUser = addUser;
			vm.getChat = getChat;
			vm.canUserTravel = canUserTravel;
			vm.addMessage = addMessage;
			vm.removeUser = removeUser;
			
			vm.confirm = confirm;
			vm.confirmed;
			
			vm.trip={}
		    vm.message={contents:'', tripId:$stateParams.id};
		
		    loader.show();
			
			$http.get("trip/details/"+$stateParams.id).
			  then(function(response) {
				 
				 vm.trip=response.data;
				 if(vm.trip.id==null){
					  $location.path('/');
				 }
				 vm.trip.tripDate = fixDate(vm.trip.tripDate);
				 
				 $timeout(function(){canUserTravel(vm.trip.user.username, $rootScope.session.data.username, vm.trip.tripContainsUser, vm.trip.freeSeat, vm.trip.user.accountNonLocked)},200); 
			    
				 if(vm.trip.tripContainsUser){
					 document.getElementById("removeUserButton").style.display = "block";
				 }	
			 });
			
			   
			
			function canUserTravel(tripUsername, sessionUsername, isContainsUser, freeSeat, userNonLocked){
				
				console.log("freeSeat: "+freeSeat);
				console.log("passenger: "+isContainsUser);
				
				if(tripUsername==sessionUsername || isContainsUser || freeSeat==0){
					
                  }
				else{
					
					document.getElementById("addUserButton").style.display = "block";
					
				}
				isUserLocked(userNonLocked);
				isTripExpired();
				
			}
			

			function fixDate(date){
				date = new Date(date);
				date = date.getTime() + 120*60000;//+2h
				return date;
			}
			
			function isTripExpired(){
				date= new Date();
				if(vm.trip.tripDate < date){
					vm.tripExpired=true;
					document.getElementById("addUserButton").style.display = "none";
					console.log("expired");
				}
			}
			
			function isUserLocked(userLocked){
				console.log("accountNonLocked: "+userLocked);
                if(!userLocked){
				   document.getElementById("addUserButton").style.display = "none";
				   document.getElementById("userNonLocked").style.display = "block";
                }
			}
			
			function getChat(){
			    $http.get("chat/get/"+$stateParams.id).
	    	       then(function(response) {
	    	         $scope.messages=response.data;
					 
			    });
			}
			
			function addMessage(message){
		    	   
		    	   $http.post("message/add", message).
		    	   success(function(data, status, headers, config) {
		    		      console.log('add message');
		    		      getChat();
		    		      
		    		  }).
		    		  error(function(data, status, headers, config) {
		    		     alert("Wystąpił błąd, 271");   
		    		  });
		    }
			
			function addUser(trip){
				addReservation();
				vm.addUserButton=true;
   		        vm.removeUserButton=false;
				$http.post("trip/addUser", trip).
		    	   success(function(data, status, headers, config) {
		    		      console.log('add user');
		    		      $http.get("reservation/isConfirmed/" + $stateParams.id).then(
		  						function(response) {
		  						
		  							vm.confirmed = response.data;

		  				  });
		    		     
		    		      document.getElementById("removeUserButton").style.display = "block";
		    		  }).
		    		  error(function(data, status, headers, config) {
		    		     alert("Wystąpił błąd, 284");   
		    		  });
			}
			
			function removeUser(idTrip){
				vm.addUserButton=false;
  		        vm.removeUserButton=true;
  		        
  		        reservationService.remove(idTrip);
  		      
			    $http.get("trip/removeUser/"+ idTrip).
		    	   success(function(data, status, headers, config) {
		    		   
		    		   $http.get("reservation/isConfirmed/" + $stateParams.id).then(
								function(response) {
								
									vm.confirmed = response.data;

					   });
		    	   }).
		    	   error(function(data, status, headers, config) {
		    		   alert("Wystąpił błąd, 298");   
		    	   });
			    
				
			}
			
			//reservationService
			
			vm.confirmed = isConfirmed();
			  
		    function addReservation(){
		    	 reservationService.add();
		    }
		    function isConfirmed(){
		    	$http.get("reservation/isConfirmed/" + $stateParams.id).then(
						function(response) {
						
							vm.confirmed = response.data;

						});
		    	return reservationService.isConfirmed();
		    	
		    }
		    
		   
		    function confirm(idUser){//for author
		    	vm.isDisabled=true;
		    	reservationService.confirm(idUser);
		    }
		  
			
		    //TRIP DETAILS
		    function initialize() {
				var directionsService = new google.maps.DirectionsService();
				var directionsDisplay = new google.maps.DirectionsRenderer();

				  var myOptions = {
				    zoom: 12,
				    mapTypeId:'roadmap'
				   
				  }

				  map = new google.maps.Map(document.getElementById("tripMap"), myOptions);
				  google.maps.event.trigger(this.map, 'resize');
				  directionsDisplay.setMap(map);
				  
				 
				 
				  var request = {
						  
				    origin: new google.maps.LatLng(vm.trip.fromLat, vm.trip.fromLng),
				    destination: new google.maps.LatLng(vm.trip.toLat, vm.trip.toLng),
				    travelMode: google.maps.DirectionsTravelMode.DRIVING
				  };
				  
				  

				  directionsService.route(request, function(response, status) {
				    if (status == google.maps.DirectionsStatus.OK) {
				      directionsDisplay.setDirections(response);
				      google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
				        directions = directionsDisplay.getDirections();
				        
				     })
				    } else {
				      alert("directions request failed:" + status)
				    }
				  });
				
				}
				
			$scope.initMap = function(){
				
				document.getElementById('tripMap').style.display="block";
				initialize();
				google.maps.event.addDomListener(window, "load", initialize);
			}
			
})
			
.controller('tripMapController', function($scope, $location,$timeout, $http, toastr){
				//ADD TRIP
				var vm=this;
				vm.submitTrip=submitTrip;
				vm.date = new Date();
				
				getAllTaxis();
				
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
				    	  function round(number,X) {
				    	        X = (!X ? 2 : X);
				    	        return Math.round(number*Math.pow(10,X))/Math.pow(10,X);
				    	    }
					      directionsService.route({
							   origin:  document.getElementById('fromAddress').value,
							   destination: document.getElementById('destAddress').value,
							   travelMode: google.maps.DirectionsTravelMode.DRIVING
							    
					       }, function(response, status) {
					       if (status == google.maps.DirectionsStatus.OK) {
					        directionsDisplay.setDirections(response);
					        google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
					         directions = directionsDisplay.getDirections();
					        
					           var distanceText = directions.routes[0].legs[0].distance.text;
					 		   var time = directions.routes[0].legs[0].duration.text;
					           var distanceRaw = directions.routes[0].legs[0].distance.value;
					           var perKm =  2.4; //2.4zł/km
					           var price = ((distanceRaw) * perKm);
					           console.log(price);
					           var rounded_price = round((6000+price)/1000,1); 
					           console.log(rounded_price+'zł');
					         
					           document.getElementById('taxiData').innerHTML =  "Odległość: "+distanceText+"\n\
				                <br/>\n\
				                Czas przejazdu: "+time+"<br/>\n\
				                Orientacyjny koszt: "+rounded_price+"zł\n\
				                <br/>\n\
				                 ";
					        document.getElementById('time').value=time;
					        document.getElementById('taxiPrice').value=rounded_price;
					        
					        document.getElementById('distance').value = directions.routes[0].legs[0].distance.value;
						    document.getElementById('duration').value = directions.routes[0].legs[0].duration.value;
						   // console.log(directions.routes[0].legs[0].distance.value);
						    
					        document.getElementById('fromLat').value = directions.routes[0].legs[0].start_location.lat();
					        document.getElementById('fromLng').value = directions.routes[0].legs[0].start_location.lng();
					        document.getElementById('toLat').value = directions.routes[0].legs[0].end_location.lat();
					        document.getElementById('toLng').value = directions.routes[0].legs[0].end_location.lng();
					      
					     
					        var fromAddress = document.getElementById('fromLat').value+", "+ document.getElementById('fromLng').value;
					        var destAddress = document.getElementById('toLat').value+", "+ document.getElementById('toLng').value;
					       
					        var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + fromAddress + "&key=AIzaSyCGq-qP3nxn_YmZQAPQ4IYuIcCZGK4X2Wk";
					        $.getJSON(url, function (data) {
					           
					                var address = data.results[0].formatted_address;
					                document.getElementById('fromAddress').value = address;
					            
					        })
					        url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + destAddress + "&key=AIzaSyCGq-qP3nxn_YmZQAPQ4IYuIcCZGK4X2Wk";
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
				
			    $timeout(initialize());
				google.maps.event.addDomListener(window, "load", initialize);
				
                function getAllTaxis(){
                	$http.get("taxi/get/all")
					  .then(function(response) {
						 return vm.listTaxis=response.data;
					  }, function(response) {
					      alert('Wystąpił błąd, 453');
					  });
                }
                
				function submitTrip(){
					
					
					var tripFromMap = {
						
					         fromLat:document.getElementById('fromLat').value,
					         fromLng:document.getElementById('fromLng').value,
					         toLat:document.getElementById('toLat').value,
					         toLng:document.getElementById('toLng').value,
					        // tripDate:document.getElementById('tripDate').value,
					         fromPlace:document.getElementById('fromAddress').value,
					         toPlace:document.getElementById('destAddress').value,
					         tripDate:moment(document.getElementById('tripDate').value),
					         distance:document.getElementById('distance').value,
					         duration:document.getElementById('duration').value, 
					         description: document.getElementById('description').value,
					        //taxi
					         time:document.getElementById('time') .value,
						     taxiPrice:document.getElementById('taxiPrice').value,
					        //car 
						     price:document.getElementById('price').value,
						     freeSeat:document.getElementById('freeSeat').value
					 };
					
					 
				     $http.post("trip/addTrip", tripFromMap).
				        success(function(data, status, headers, config) {
				        vm.isDisabled=true;	
	  		            console.log('ok');
	  		            toastr.info("Dodano pomyślnie przejazd");
	  				
	  		      
	  		            }).
	  		          error(function(data, status, headers, config) {
	  		            alert("Wystąpił błąd, 552");   
	  		         });
				
		          }
})

   
.controller('tripTravelerController', function($scope, $stateParams, $timeout, $http, toastr, loader){
				
				var vm=this; 
				
				vm.getTripUserTravel=getTripUserTravel();
				vm.getTravelers=getTravelers;
				vm.isConfirmed=isConfirmed;
				vm.date=moment().format('YYYY-MM-DD HH:mm'); 
				
				
				function getTripUserTravel(){
					$http.get("trip/userTravels")
					  .then(function(response) {
					     vm.listTrip = response.data;
						 loader.show();
					     return vm.listTrip;
					  }, function(response) {
					      alert('Wystąpił błąd, 578');
					  });
				}
					
					
				function isConfirmed(userId){
					
					var response= $http.get("reservation/isUserConfirmed/"+$stateParams.id+"/"+userId);
					return response.success(function(data, status, headers, config) {
						console.log(data);
						return data;
					});
					
					
					
			    }
				
				function getTravelers(){
					$http.get("trip/travelers/"+$stateParams.id)
					  .then(function(response) {
						 loader.show();
						
					     vm.listUsers = response.data;
					    
					     
					  }, function(response) {
					      alert('Wystąpił błąd, 602');
					  });
					 
				}
				
				function initialize(counter, fromLat, fromLng, toLat, toLng) {
					var ax=angular.copy(fromLat);
					var ay=angular.copy(fromLng);
					var bx=angular.copy(toLat);
					var by=angular.copy(toLng);
					
					var directionsService = new google.maps.DirectionsService();
					var directionsDisplay = new google.maps.DirectionsRenderer();
					
					  var myOptions = {
					    zoom: 12,
					    mapTypeId:'roadmap'
					  }

					  map = new google.maps.Map(document.getElementById(counter), myOptions);
					  google.maps.event.trigger(this.map, 'resize');
					  directionsDisplay.setMap(map);
					  
					 
					  
					  console.log(counter);
					  var request = {
							  
					    origin: new google.maps.LatLng(ax, ay),
					    destination: new google.maps.LatLng(bx, by),
					    travelMode: google.maps.DirectionsTravelMode.DRIVING
					  };
					 
					  

					  directionsService.route(request, function(response, status) {
					    if (status == google.maps.DirectionsStatus.OK) {
					      directionsDisplay.setDirections(response);
					      google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
					        directions = directionsDisplay.getDirections();
					        
					     })
					    } else {
					      alert("directions request failed:" + status)
					    }
					  });
					
					}
				
				var c = 0;  // $index listTrip
								
				$scope.initMap = function(counter, fromLat,fromLng,toLat, toLng){
					
					$timeout(function(){countUp(fromLat, fromLng, toLat, toLng)},500);   //timeout, nie wykrywa getElementById(counter), wystarczy milisekunda 
					
					
				}

			    var countUp = function(fromLat,fromLng,toLat, toLng) {
			    	
			    	document.getElementById(c).style.display="block";
			    	
					initialize(c, fromLat, fromLng, toLat, toLng);
					google.maps.event.addDomListener(window, "load", initialize);
					++c;
					
					
			    }

});          
			
