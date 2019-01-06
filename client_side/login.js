function validateInfomration(){
    let userName = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    if(userName.length == 0 || password.length){
        alert("You have the username and/or password field as empty");
   }
}
