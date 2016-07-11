<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>Selected Items</title>
    <link href="css/appdynamics.css" rel="stylesheet" type="text/css"/>
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
                                    <div class="leftAdd">
                                        <table width="99%" height="232" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <th height="137" colspan="2">
                                                    <div align="center"><img
                                                            src="images/The_Lost_City_Of_Z-David_Grann.jpg" alt="Book1"
                                                            width="80" height="104"/></div>
                                                </th>
                                            </tr>


                                            <tr>
                                                <td height="25" colspan="2">
                                                    <div align="center">The Lost City Of Z</div>
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
                                                    <div align="center"><img src="images/The_Godfather-Mario_Puzo.jpg"
                                                                             alt="The Godfather"/></div>
                                                </th>
                                            </tr>

                                            <tr>
                                                <td height="25" colspan="2">
                                                    <div align="center">The Godfather</div>
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
                                <div class="banner"><img src="images/InnerBanner.jpg" width="751" height="123"/></div>
                                <div class="LContainer">
                                    <div class="lcontainerTop"><img src="images/logintop.gif"/></div>
                                    <div class="lcontainerCon">
                                        <div class="selcetedItemcontainer">

                                            <div class="selcetedItemTop">

                                                <div class="Selecttext">

                                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">

                                                        <tr>
                                                            <td>
                                                                <%
                                                                    String msg = (String) request.getAttribute("msg");
                                                                    if (msg != null && !"null".equalsIgnoreCase(msg)) {
                                                                %>
                                                                <FONT style="color:#ffffff;"><b><%=msg %>
                                                                </b></FONT>
                                                                <%}%>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>

                                            </div>

                                            <div class="selcetedItemBot"></div>
                                        </div>

                                    </div>

                                </div>
                                <div class="lcontainerBot"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </td>
    <tr>
        <td>
            <%@include file="footer.jsp" %>
        </td>
    </tr>
</table>
</body>
</html>
