<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>MemoryLeak</title>
</head>
<body>

<table>
    <tr>
        <td>Free Memory (byte):</td>
        <td><c:out value="${freeMem}"/></td>
    </tr>
    <tr>
        <td>Total Memory (byte):</td>
        <td><c:out value="${totalMem}"/></td>
    </tr>
    <tr>
        <td>Free Percent:</td>
        <td><c:out value="${freePercent}"/>%</td>
    </tr>
    <tr>
        <td>Used Percent:</td>
        <td><c:out value="${inUsedPercent}"/>%</td>
    </tr>
    <tr>
        <td>Object count in data structure:</td>
        <td><c:out value="${objectCount}"/></td>
    </tr>
</table>

</body>
</html>