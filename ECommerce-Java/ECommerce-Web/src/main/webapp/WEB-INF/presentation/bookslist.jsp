<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Cart - Books List</title>
</head>
<body>
<table>
    <c:forEach items="${itemsList}" var="item">
        <tr>
            <td width="58%"><c:out value="${item.title}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>