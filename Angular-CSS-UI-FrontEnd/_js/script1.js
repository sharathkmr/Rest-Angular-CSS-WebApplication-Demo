/**
 * Created by Sharath on 2/21/2017.
 */

var module=angular.module("MyApp",[]);
module.controller("AppController",['$scope','$http', appController]);

/*  To use a service or scope in the controller, we need to inject the respective service
 *  or scope to the controller
 *
 *
 */

function appController($scope,$http) {

    console.log("appController");
    console.log("uname: "+userName);
    $scope.advTask=false;

    var userName= undefined;

    $scope.logoutAction = function () {
        $scope.advTask=false;
        $scope.homeClick();
    };

    $scope.homeClick=function homeClick() {
        console.log("Home Button");
        $scope.template="home";
        $scope.content_type="Home";
        var doc=document.getElementsByName("divContent");
        console.log(doc[0]);
        doc[0].style.height="390px";
        doc[0].style.width="620px";
        console.log($scope.template);
    };

        $scope.loginClick=function loginClick() {
            console.log("login Button");
            $scope.template="login";
            $scope.content_type="Login Page";
            var doc=document.getElementsByName("divContent");
            doc[0].style.height="";
            console.log($scope.template);
        };

        $scope.employeeClick=function () {
            console.log("Employee Button");
            $scope.template="employee";
            $scope.content_type="Employee Details";
            var doc=document.getElementsByName("divContent");
            doc[0].style.height="";
            $http.get("http://localhost:9090/Angular-Rest-Test-Demo/Employee").then(function(response) {
                console.log(response.data);
                $scope.data=response.data;
            });
        }

        /*  As we have passed the http service in the controller
        *   it is unncessary to pass the service in the inner functions
        *
        *
        * */
    $scope.loginCheck = function loginCheck(uname,pass,loginStatus) {

        console.log(uname);
        console.log(pass);

        var url='http://localhost:9090/Angular-Rest-Test-Demo/Employee/check/'+uname+'?callback=JSON_CALLBACK';


        var getPromise=$http.get(url).then(function(response) {

            console.log('pass: '+response.data.upass);
            console.log(pass);
            if(response.data.upass===pass) {
                console.log("Login success");
                $scope.loginStatus='Login success';
                $scope.advTask=true;
                userName=uname;
            } else {
                console.log("Login failure");
                $scope.loginStatus='Login failure';
            }

            if($scope.loginStatus==="Login success") {
                console.log('loginsuccess');
                $scope.template="employee";
				// making a http call to the rest service
                $http.get("http://localhost:9090/Angular-Rest-Test-Demo/Employee").then(function(response) {
                    console.log(response.data);
                    $scope.data=response.data;
                });
            }
        });

        console.log('loginStatus '+loginStatus);


    };
}
