 angular.module('myApp').filter('onlyFirst', function() {
  return function(value) {
      return String(value).charAt(0).toUpperCase();
   }
})