<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta name="robots" content="noindex, nofollow">

<title>Login | Famous Fingers</title>

<link rel="stylesheet" href="css/style.css">

<script src="js/jquery-1.9.1.min.js"></script>

<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>

<body>
<div id="wrapper">
    <div id="left_div">
        <div id="logo_container">
            <a href="an-home.html"><h1 id="logo" class="hide_text">Famous Fingers</h1></a>
        </div>
        
        <aside id="content_sidebar">
            <section id="register">
                <h2 class="register icon">New consultant</h2>
                <form id="register_form" action="authenticateUser.php" method="post" name="register_form">
                    <label for="cs_id">Your consultant ID</label>
                    <input type="text" id="cs_id" required name="cs_id" >

                    <input id="register_submit" type="submit" value="Register" name="register_submit">
                </form>
            </section><!-- END OF SECTION #NOTIFICATION -->
        </aside><!-- END OF ASIDE #CONTENT_SIDEBAR -->
    </div><!-- END OF DIV #LEFT_DIV -->
    
    
    <div id="right_div">
        <section id="content">
            <section id="login">
                <h2 class="login icon">Login</h2>
                <form:form id="login_form" class="content_form" action="login.php" method="post" commandName="loginForm">
                    <label for="email">Email</label>
                    <form:input path="email" type="email"/>
                    <form:errors path="email" cssClass="error"/>
                    <label for="pwd">Password</label>
                    <form:input path="password"/>
                    <form:errors path="password" cssClass="error" />
                    <a id="forgot_pwd_link" href="forgot_pwd.html">Forgot password?</a>
                    <input id="login_submit" type="submit" value="Login" name="login_submit">
                </form:form>
            </section><!-- END OF SECTION #LOGIN -->
        </section><!-- END OF SECTION #CONTENT -->
    
    
        <footer id="main_footer">
        	<div id="footer_container">
                <p><em>2057</em> consultants registered.</p>
                <p><em>12034</em> fingerprints analysed.</p>
                
                <small id="copyright">Consultant Comunication Application. <span class="dont_break">&copy; 2013 <em>FamousFingers</em>.</span></small>
            </div>
        </footer>
    </div><!-- END OF DIV #RIGHT_DIV -->
</div><!-- END OF DIV #WRAPPER -->


</body>
</html>