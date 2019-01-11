function makeAjaxRequest(userName, password){
    let xhr = new XMLHttpRequest();
    xhtr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            if(xhr.responseText == "success" ){
            	window.location.replace("https://www.google.com");
            }
            else if (xhr.responseText == "failure"){
            	
            }
        }
    } 
    xhr.open("Get", "loginServlet", true);
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
