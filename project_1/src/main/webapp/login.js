function makeAjaxRequest(userName, password){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            if(xhr.responseText == "success" ){
            	window.location.replace("http://localhost:8080/project_1/employee.html");
            }
            else{
            	alert("Something went wrong");
            }
        }
    } 
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    xhr.open("GET", "http://localhost:8080/project_1/loginServlet?username=" + username + "&password=" + 
    		password, true);
    xhr.send();

}

function validateInfomration(){
    let userName = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    if(userName.length == 0 || password.length == 0){
        alert("You have the username and/or password field as empty");
   }
   else{
        makeAjaxRequest(userName, password);
   }
}
