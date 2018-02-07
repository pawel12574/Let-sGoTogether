angular.module('myApp').controller('uploadAvatarController', function($scope, $timeout, $location, $http, Session, toastr) {
	
	var vm=this;
	vm.upload=upload;
	vm.defaultAvatar=defaultAvatar;
	
	if(Session.data.avatar==null)
		vm.isDisabled=true;
	
	function defaultAvatar(){
	   $http.get("user/delete/avatar").
	     then(function(response) {
		   vm.isDisabled=true;
		   vm.isDeleted;
		   Session.data.avatar=null;
	   });
	}
	
	function upload(){
		var file = $scope.myFile;
		var fd = new FormData();
		fd.append('file', file);
		if(file.size<=15360){
		   uploadSuccess(fd);
		   vm.isDisabled=false;
		}   
		else toastr.error("Zmniejsz rozmiar zdjęcia");
    }
	
	function uploadSuccess(fd) {
		$http.post("user/add/avatar", fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function() {
			toastr.info("Zaktualizowano avatar");
			Session.updateSession();
		}).error(function() {
		});
    }
	
	
})

.directive('fileModel', ['$parse', function ($parse, toastr, $rootScope) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs, $rootScope) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function($rootScope){
                	modelSetter(scope, element[0].files[0]);
                	//$rootScope.imageSrc=element[0].files[0];
                    $rootScope.size=element[0].files[0].size/1024;
                });
            });
           
        }
    };
}]);
/*
.directive('checkUploadPicture', function(toastr) {
  return {
    link: function(scope, elem, attr, ctrl) {
      function bindEvent(element, type, handler) {
        if (element.addEventListener) {
          element.addEventListener(type, handler, false);
        } else {
          element.attachEvent('on' + type, handler);
        }
      }
      bindEvent(elem[0], 'change', function() {
       if(this.files[0].size>51240)
    	   toastr.error("Rozmiar zjdęcia jest większy niż 50KB");
       this.files[0].value=null;
      });
    }
  }
});*/