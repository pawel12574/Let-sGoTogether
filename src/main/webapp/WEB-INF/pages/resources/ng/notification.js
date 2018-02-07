angular.module('myApp').controller('notificationController', function($scope, $timeout, $location, $http,  toastr, loader) {
	
	
	 var vm=this;
	 
	//methods
	 vm.getAllNotification=getAllNotification;
	 vm.confirmNotification=confirmNotification;
	 vm.showNotificationForSimilar=showNotificationForSimilar;
	 vm.showNotificationForUpdated=showNotificationForUpdated;
	 vm.removeNotification=removeNotification;
	
	 
	 //variables
	 vm.similarTripNotification = getSimilarTripNotification(); //variable init
	 vm.updatedTripNotification = getUpdatedTripNotification();
	 vm.allNotification = getAllNotification();
	 
	
	 toastr.clear(); //clear note
	  
	   //loader display/close
	 
	 
	 function showNotificationForSimilar(nota, notb){
		 var str=nota+" do "+notb;
		 
		 toastr.info(str,"Dodano podobny przejazd, sprawdź zakładkę powiadomienia.");
		
	 }
	 
	 function showNotificationForUpdated(nota, notb){
		 var str=nota+" do "+notb;
		 
		 toastr.warning("Edytowano "+str+", sprawdź powiadomienia!");
		
	 }
	 
	 function removeNotification(note){
		 console.log("remove "+ note);
		 vm.allNotification.splice(functiontofindIndexByKeyValue(vm.allNotification, "id", note.id),1 );
		 console.log(note.created);
		 $http.post("notification/remove",note).
	      success(function(data, status, headers, config) {
		      console.log('usunięto notkę');
		   }).
		  error(function(data, status, headers, config) {
		     alert("Wystąpił błąd");   
		  });
	 }
	 	
	 function getSimilarTripNotification(){
		 
		 $http.get("notification/getNConfirmedNotification/similarTrip").
		  then(function(response) {
			
			return vm.similarTripNotification = response.data;
			
		 });
		 
	 }
	 
     function getUpdatedTripNotification(){
		 
		 $http.get("notification/getNConfirmedNotification/tripUpdated").
		  then(function(response) {
			
			return vm.updatedTripNotification = response.data;
			
		 });
		 
	 }
	 
     function getAllNotification(){
    
		 $http.get("notification/getAllNotification").
		  then(function(response) {
			 
			return vm.allNotification = response.data;
			
		 });
     }

	 function confirmNotification(id){
		 
		 $http.get("notification/confirmNotification/"+id).
		  then(function(response) {
			console.log("confirm note");
			
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
	 
