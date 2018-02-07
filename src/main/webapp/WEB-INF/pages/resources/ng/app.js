var myApp = angular.module('myApp', [ 'ui.router', 'ngResource', 'ngMessages', 'vsGoogleAutocomplete','toastr', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngCookies', 'alexjoffroy.angular-loaders'])

.config(
		function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider,toastrConfig) {
			angular.extend(toastrConfig, {
			    allowHtml: false,
			    closeButton: true,
			    closeHtml: '<button>&times;</button>',
			    extendedTimeOut: 0,
			    iconClasses: {
			      error: 'toast-error',
			      info: 'toast-info',
			      success: 'toast-success',
			      warning: 'toast-warning'
			    },  
			    messageClass: 'toast-message',
			    onHidden: null,
			    onShown: null,
			    onTap: null,
			    progressBar: false,
			    tapToDismiss: false,
			    templates: {
			      toast: 'directives/toast/toast.html',
			      progressbar: 'directives/progressbar/progressbar.html'
			    },
			    timeOut: 0,
			    titleClass: 'toast-title',
			    toastClass: 'toast'
			  });
			$urlRouterProvider.otherwise("/")
			
           /*state('trip', {
				url : "/",
				templateUrl : "./welcome",
			       })*/
			$stateProvider.state('myprofile', {
				url : "/myprofile",
				templateUrl : "./user/view"

			}).state('registration', {
				url : "/registration",
				templateUrl : "./registration"

			}).state('login', {
				url : "/login",
				templateUrl : "./login"

			}).state('welcome', {
				url : "/",
				templateUrl : "./tripFinder"

			}).state('tripAdd', {
				url : "/trip/add",
				templateUrl : "./tripAdd"
			
			}).state('tripDetails',{
				url : "/trip:id",
				templateUrl : "./tripDetails"   
				
			}).state('tripSdAdd',{
				url : "/tripsearched/add",
				templateUrl : "./tripSd/add"
				
			}).state('tripSdGet',{
				url : "/trips/searched",
				templateUrl : "./tripsd/all"		
			
			}).state('notification',{
				url : "/notification",
				templateUrl : "./notification/get"
				
			}).state('trips',{
				url : "/trip/get",
				templateUrl : "./trip/get"
				
			}).state('changePassword',{
				url : "/changePassword",
				templateUrl : "./user/changepass"
			
			}).state('forgotPassword',{
				url : "/forgotPassword",
				templateUrl : "./user/forgotPassword"
			
			
			
			}).state('admin',{
				url : "/admin",
				templateUrl : "./admin/view",
				abstract:true
				
			}).state('admin.listTrip',{
				url : "/",
				templateUrl : "./admin/view/listTrip"
					
			}).state('admin.listTripSd',{
				url : "/",
				templateUrl : "./admin/view/listTripSd"		
			
			}).state('admin.listUsers',{
				url : "/",
				templateUrl : "./admin/view/listUsers"
			
			}).state('admin.listTaxi',{
				url : "/",
				templateUrl : "./admin/view/listTaxi"		
			
			
			
			}).state('user',{
				url : "/user",
				templateUrl : "./user/view",
				abstract:true
			
			}).state('user.password',{
				url : "/",
				templateUrl : "./user/view/password",
			
			}).state('user.avatar',{
				url : "/",
				templateUrl : "./user/view/avatar"
			
			}).state('user.data',{
				url : "/",
				templateUrl : "./user/view/data"
			
			
			
			}).state('loginFailure',{
				url : "/loginFailure",
				templateUrl : "./loginFailure"
			
			}).state('userTraveler',{
				url : "/traveler",
				templateUrl : "./user/userTraveler"
			
			});			
			 
			/*$locationProvider.html5Mode({
				  enabled: true,
				  requireBase: false
				});*/
		});

		
