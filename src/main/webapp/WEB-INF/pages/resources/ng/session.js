myApp.factory('Session', function($http) {
  var Session = {
    data: {avatar:"iVBORw0KGgoAAAANSUhEUgAAAE8AAAA9CAIAAABjvKMbAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAMwSURBVGhD1ZhtjoQgDIZ1TrW/9gR7/+u4KqKAtH1big6TTDLJtPR9+oHI/PP7N1k+8zQtpB/7pyVa4tOy9scSew1Io7J/WqIVPnSOL8NNQ+1jomUDImpaoZeJ4gkr7xoqJmpaPkwrBug/7zyClEra1bRPlA5kxqSkOdHRfkVhwVxEs7QLdLRUNt/Nghw9EutoqbRiPaUsCmyORV+tZphWTiCsrpuhpHFhaVNvLIHdQKCFRY0sregNaagZSVUwL8w/mCjannKqJzG/gEyNKNp+ZSXK9kjAG61fjlu60cm3hLnRPpJjPYytCNtTJ40FP4H0Al09QhEMzFn1RqENqYvSDdS7/1i06dk3/Ma4o9WYtNeQnI3KYsf34YsWy5LrMHouJrzwhqxctF+6GWsyUm7BpS/+VqCJ+qItf4nkM7ddp8BxcR/aUabAh7Zr65bTCFzAUXocaB07jVKZAWcX1rrgDrTqNk4U4mL3KKV5uGnFP820uN5TVKJQefytNTXO6nBy1CW3Lq1aN4JiaTksN9dWk9rE9t4SF4awZHSl8sx0W2/aeuj6vK22aKdw82O4qTGW7OZWD00KQoEXCpffRnrV1rB5HZnCgKl88c3RixZ7EyM6CAM2tF8X2rSw6CQW2vsA+9O66VQOA2LuT7sYq2lozMwFCXvSIqnBBPmthMW7djfR3r+2YshOBtLFxRb2pEUaATzkuOHsXaJoFZHBs7ZJMIXEam6C/76giHBfgIzuSZuENUjMxs/uzyaoEy3QzHkBzqZt4xTifua5tesAsppJjmVtWi74HeyzvPV8NCapGBfhZaj4++jkl+prJ94Eb1/yZSgsXczFQdt1WuxMhGd6OFUpL3epIYp8JwRll7TXQqqkuZdPuaB4sxXSQT+B3N5llMJt5hsuV59wrmSftwMBx12LOWmuwNLpYiTgUFuuwhJt2OhdZxjcUbKOPn3WH5Q/IBKgjcAWlfIhChvTk2T9AVBRi2K0O3BDFAypvxVM21/KAxGMtF5d/QBhGsJIG7t6MGojbUxYoP4+ZmLrrtMq5b+zfx0HiqpWYuuu07bLV+aLnV+iUMdhkdB6XoakS/8DlM2FPne0A2sAAAAASUVORK5CYII="},
     //only during loading avatar
    updateSession: function() { 
      
      $http.get('user/get/avatar')
        .then(function(response) { return Session.data = response.data;})
        
    }
  };
  Session.updateSession();
  return Session; 
});