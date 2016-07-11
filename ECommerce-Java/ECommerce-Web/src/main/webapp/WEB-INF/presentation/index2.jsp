<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib uri="/struts-tags" prefix="s" %>


<s:if test="#session.USER != null">
    <c:redirect url="ViewItems.action"></c:redirect>
</s:if>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Login</title>
    <link href="appdynamics.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="mainContainer">
    <div class="innerContainer">
        <div class="header">
            <div class="logo"><img src="images/logo.png"/></div>

            <div class="topLogin"><a href="#">Sign In</a> <span class="topLogin1">or</span> <a href="#">Register</a>
            </div>
            <div class="menucontainer">
                <table width="969px" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <th>
                            <div class="menuleftbg"></div>
                        </th>
                        <th>
                            <div class="menubg">
                                <div class="menudiv">

                                    <ul>
                                        <li>
                                            <a href="#">HOME</a>
                                        </li>
                                        <li>
                                            <a href="#">NEW PRODUCTS</a></li>
                                        <li>
                                            <a href="#"> MY ACCOUNT</a>
                                        </li>

                                        <li style="background-image:none;">
                                            <a href="#">CONTACT US</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="menurightbg"></div>
                        </th>
                    </tr>

                </table>
            </div>

        </div>
    </div>
</div>
<div class="mainContainer">
    <div class="innerContainer">
        <div class="midContainer">
            <div class="midTopbg"></div>
            <div class="midbg">
                <div class="midCLeft">

                    <div class="leftBGnav"></div>
                    <div class="leftBg">
                        <div class="leftAdd">
                            <table width="99%" height="232" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <th height="137" colspan="2">
                                        <div align="center"><img src="images/inbook1.gif" alt="Book1"/></div>
                                    </th>
                                </tr>

                                <tr>
                                    <td height="25" colspan="2">
                                        <div align="center">11 Practice Tests for the SAT and PSAT, 2009</div>
                                    </td>

                                </tr>
                                <tr>
                                    <td width="49%">
                                        <div class="price">$25</div>
                                    </td>
                                    <td width="51%">
                                        <div class="price1"><a href="#">Buy</a></div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="leftAdd">
                            <table width="99%" height="232" border="0" cellpadding="0" cellspacing="0">

                                <tr>
                                    <th height="137" colspan="2">
                                        <div align="center"><img src="images/ibook2.gif" alt="Book1" width="80"
                                                                 height="104"/></div>
                                    </th>
                                </tr>

                                <tr>
                                    <td height="25" colspan="2">
                                        <div align="center">Big Ideas :100 Modern Inventions That Have
                                            Transformed Our World
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="49%">
                                        <div class="price">$30</div>
                                    </td>

                                    <td width="51%">
                                        <div class="price1"><a href="#">Buy</a></div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                    </div>
                    <div class="leftBottom" style="float:left;"></div>
                </div>
                <div class="midCright">
                    <div class="banner"><img src="images/Banner.jpg"/></div>
                    <div class="LContainer">

                        <div class="lcontainerTop"><img src="images/logintop.gif"/></div>
                        <div class="lcontainerCon">
                            <div class="logBoxContainer">
                                <div class="loginBoxTop"></div>
                                <div class="loginBoxBg">
                                    <div class="loginTextc">
                                        <div class="logintext">
                                            <div align="center">User Name :
                                                <s:textfield id="textBox" name="loginName"/>

                                            </div>
                                        </div>
                                        <div class="logintext">
                                            <div align="center">User Name :
                                                <s:password name="password" id="password"/>
                                            </div>
                                        </div>
                                        <div class="PasswordBut">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <th width="46%">
                                                        <div class="Password">


                                                        </div>
                                                    </th>
                                                    <th width="54%"><input name="Login" type="button" value="Login"
                                                                           class="Button"
                                                                           onclick="return validateLogin();"/></th>
                                                </tr>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                                <div class="loginBoxBot"></div>

                            </div>

                        </div>
                    </div>
                    <div class="lcontainerBot"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mainContainer">
    <div class="innerContainer">
        <div class="footer">Copyright @ Neev Information Technology</div>
    </div>
</div>

</body>
</html>

<script>
    function validateLogin() {
        if (document.getElementById('textBox').value == '') {
            document.getElementById('errorMessage').style.display = '';
            document.getElementById('printMessage').innerHTML = 'Please enter login name';
            //alert("Enter Login Name");
            document.getElementById('textBox').focus();
            return false;
        }

        if (document.getElementById('password').value == '') {
            document.getElementById('errorMessage').style.display = '';
            document.getElementById('printMessage').innerHTML = 'Please enter password';
            //alert("Enter Password");
            document.getElementById('password').focus();
            return false;
        }
        return true;
    }
</script>

