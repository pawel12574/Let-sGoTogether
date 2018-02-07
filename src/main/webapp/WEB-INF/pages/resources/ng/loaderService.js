angular.module('myApp').service('loader', function($timeout) {
   
	var vm=this;
	vm.show=show;
	vm.hide=hide;
	
	function show() {
		document.getElementById("loader").style.display = "block";
		document.getElementById("loader").style.textAlign="center";
		$timeout(hide, 3000); // 3 seconds

	}
	function hide() {
		document.getElementById("loader").style.display = "none";
		document.getElementById("afterLoader").style.display = "block";

	}

});