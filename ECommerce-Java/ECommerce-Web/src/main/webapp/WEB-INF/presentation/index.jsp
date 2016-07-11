<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>


<s:if test="#session.USER != null">
    <c:redirect url="ViewItems.action"></c:redirect>
</s:if>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Login</title>
    <link href="css/appdynamics.css" rel="stylesheet" type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <link href="css/appdynamics.css" rel="stylesheet" type="text/css"/>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "rest/items/all",
                cache: false,
                dataType: "xml",
                success: function (xml) {
                    $(xml).find('product').each(function () {
                        var title = $(this).find("title").text();
                        var image = $(this).find("imagePath").text();
                        insertBook(title, image);
                    });
                }
            });
        });

        function insertBook(title, path) {
            $("<div class='leftAdd'><table width='99%' height='232' border='0' cellpadding='0' cellspacing='0'><tr><th height='137' colspan='2'><div align='center'><img src='" + path + "' alt='" + title + "'/></div></th>/tr><tr><td height='25' colspan='2'><div align='center'>" + title + "</div></td></tr></table></div>").append(".leftBg");
        }
    </script>
</head>

<body>
<table align="center">
    <tr>
        <td>
            <%@include file="header.jsp" %>
        </td>
    </tr>
    <tr>
        <td>
            <div class="mainContainer">
                <div class="innerContainer">
                    <div class="midContainer">
                        <div class="midTopbg"></div>
                        <div class="midbg">
                            <div class="midCLeft">

                                <div class="leftBGnav"></div>
                                <div class="leftBg">
                                    <!--
								<div class="leftAdd">
								  <table width="99%" height="232" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <th height="137" colspan="2" ><div align="center"><img src="images/The_Godfather-Mario_Puzo.jpg" alt="The Godfather" /></div></th>
                                    </tr>
                                    
                                    <tr>
                                      <td height="25" colspan="2"><div align="center">The Godfather</div></td>

                                    </tr>
                                    <%-- 
                                    <tr>
                                      <td width="49%" ><div class="price">$25</div></td>
                                      <td width="51%" ><div class="price1"><a href="#">Buy</a></div></td>
                                    </tr>
                                    --%>
                                  </table>
								</div>
								 -->
                                    <!--
								<div class="leftAdd"><table width="99%" height="232" border="0" cellpadding="0" cellspacing="0">

                                    <tr>
                                      <th height="137" colspan="2" ><div align="center"><img src="images/The_Lost_City_Of_Z-David_Grann.jpg" alt="Book1" width="80" height="104" /></div></th>
                                    </tr>
                                    
                                    <tr>
                                      <td height="25" colspan="2"><div align="center">The Lost City Of Z</div></td>
                                    </tr>
                                    <%-- 
                                    <tr>
                                      <td width="49%" ><div class="price">$30</div></td>

                                      <td width="51%" ><div class="price1"><a href="#">Buy</a></div></td>
                                    </tr>
                                    --%>
                                  </table></div>
								-->
                                </div>

                                <div class="leftBottom" style="float:left;"></div>
                            </div>
                            <div class="midCright">
                                <div class="banner"><img src="images/Banner.jpg"/></div>
                                <div class="LContainer">
                                    <s:form name="loginForm" action="UserLogin.action" theme="simple">
                                    <div class="lcontainerTop"><img src="images/logintop.gif"/></div>
                                    <div class="lcontainerCon">
                                        <div class="logBoxContainer">
                                            <div class="loginBoxTop"></div>
                                            <div class="loginBoxBg">
                                                <div class="loginTextc">

                                                    <table>
                                                        <tr>
                                                            <div class="errorMessage">
                                                                <s:actionerror/>
                                                            </div>

                                                            <td class="logintext">

                                                                <div align="center">User Name :</div>
                                                            </td>
                                                            <td>
                                                                <s:textfield id="textBox" name="loginName"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="logintext">

                                                                <div align="center">Password :</div>
                                                            </td>
                                                            <td>
                                                                <s:password name="password" id="password"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td width="60%" align="left"><s:submit name="Login"
                                                                                                   type="button"
                                                                                                   value="Login"
                                                                                                   cssClass="Button"
                                                                                                   onclick="return validateLogin();"/></td>
                                                        </tr>
                                                    </table>
                                                </div>


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
            </s:form>
        </td>
    </tr>
    <tr>
        <td>
            <%@include file="footer.jsp" %>
        </td>
    </tr>
</table>

</body>
</html>

<script>
    function validateLogin() {
        if (document.getElementById('textBox').value == '') {
//   	document.getElementById('errorMessage').style.display='';
            // 	document.getElementById('printMessage').innerHTML='Please enter login name';
            alert("Enter Login Name");
            document.getElementById('textBox').focus();
            return false;
        }

        if (document.getElementById('password').value == '') {
            //document.getElementById('errorMessage').style.display='';
            //document.getElementById('printMessage').innerHTML='Please enter password';
            alert("Enter Password");
            document.getElementById('password').focus();
            return false;
        }
        return true;
    }
</script>

