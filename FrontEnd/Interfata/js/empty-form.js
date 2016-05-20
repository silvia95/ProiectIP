/**
 * Created by iNSIDER on 10.05.2016.
 */
function required()
{
    var emp_username = document.forms["login-form"]["form-username"].value;
    var emp_password = document.forms["login-form"]["form-password"].value;
    if (emp_username == "" && emp_password == ""){
        alert("Please fill the login form");
        return false;
    }
    else if (emp_password == ""){
        alert("Please fill the password area.");
        return false;
    }
    else if (emp_username == ""){
        alert("Please fill the email area.");
        return false;
    }else{
        alert("it's ok");
        window.location="http://infoarea.net/container.php";
        window.location.href = "http://infoarea.net/container.php";
        $(location).attr('href', 'http://infoarea.net/container.php');
    }
}