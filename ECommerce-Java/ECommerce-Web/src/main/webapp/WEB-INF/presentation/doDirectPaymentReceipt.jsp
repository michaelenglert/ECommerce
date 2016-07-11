<!-- Page to display success esponse from from paypal after Direct Payment -->
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.paypal.sdk.services.NVPCallerServices" %>
<%@ page import="com.paypal.sdk.util.*" %>
<%@ page import="com.paypal.sdk.core.nvp.NVPEncoder" %>
<%@ page import="com.paypal.sdk.core.nvp.NVPDecoder" %>
<%@ page import="java.util.*" %>
<%@ page language="java" %>

<html>
<head>
    <title>DoDirectPayment API</title>
    <link href="sdk.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<br>
<center>
    <font size=2 color=black face=Verdana><b>Do Direct Payment</b>
    </font>
    <br>
    <br>

    <b>Thank you for your payment!</b>
    <br>
    <br>
    <table width=400>

        <tr>
            <td>
                Transaction ID:
            </td>
            <td><%=(String) session.getAttribute("TRANSACTIONID")%>
            </td>
        </tr>
        <tr>
            <td>
                Amount:
            </td>
            <td>
                USD
                <%=(String) session.getAttribute("AMT")%>
            </td>
        </tr>
        <tr>
            <td>
                AVS:
            </td>
            <td><%=(String) session.getAttribute("AVSCODE")%>
            </td>
        </tr>
        <tr>
            <td>
                CVV2:
            </td>
            <td><%=(String) session.getAttribute("CVV2MATCH")%>
            </td>
        </tr>
    </table>
</center>

<a href="<c:url value="/home.html"/>"><fmt:message key="paypal.calls"/></a>

</body>
</html>
