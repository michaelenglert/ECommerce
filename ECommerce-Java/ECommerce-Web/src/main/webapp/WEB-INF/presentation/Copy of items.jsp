<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>AppDynamics Pilot</title>
    <link rel="stylesheet" href="css/appdynamicsPilot.css"/>
</head>
<body>
<table class="layoutMain" align="center">
    <tr>
        <td>
            <%@include file="header.jsp" %>
        </td>
    </tr>
    <tr>
        <td style="padding-top: 30px;">
            <!--page 2 box-->
            <div class="categoriesBoxMain">
                <div class="categoriesBox">
                    <div class="lb">
                        <div class="rb">
                            <div class="bb">
                                <div class="blc">
                                    <div class="brc">
                                        <div class="tb">
                                            <div class="tlc">
                                                <div class="trc">
                                                    <div class="categoriesContent">
                                                        <s:form id="itemsForm" name="itemsForm"
                                                                action="ViewCart!addToCart.action" theme="simple">
                                                            <div class="tableHead">
                                                                <ul>
                                                                    <li>Item Id</li>
                                                                    <li>Item Title</li>
                                                                    <li>Select</li>
                                                                </ul>
                                                            </div>
                                                            <div style=" padding-left:7px;">

                                                                <table class="others2" border="0" align="left">
                                                                    <c:forEach var="item" items="${itemsList}">
                                                                        <tr>
                                                                            <td width="167"><c:out
                                                                                    value="${item.id}"/></td>
                                                                            <td width="167"
                                                                                id="<c:out value='${item.id}'/>_title">
                                                                                <c:out value="${item.title}"/></td>
                                                                            <td width="167"><img
                                                                                    src='<c:out value="${item.imagePath}"/>'/>
                                                                            </td>
                                                                            <td width="166">
                                                                                <input type="checkbox" id="selectedId"
                                                                                       name="selectedId"
                                                                                       value="<c:out value="${item.id}"/>"
                                                                                       onclick='setValue(this);'/>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </table>
                                                            </div>
                                                            <s:hidden id="selectedItemId"
                                                                      name="selectedItemId"></s:hidden>
                                                            <s:hidden id="xml" name="xml"></s:hidden>
                                                            <table>
                                                                <tr>
                                                                    <td>
                                                                        <div class="buttonClass"><s:submit
                                                                                cssClass="submit" name="submitValue"
                                                                                key="label.addtocart"/></div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="buttonClass"><s:submit
                                                                                cssClass="submit" name="submitValue"
                                                                                key="lable.postxml"
                                                                                onclick="submitXML();"/></div>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </s:form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--page 2 box-->
        </td>
    </tr>
    <tr>
        <td style="padding-top: 100px;">
            <%@include file="footer.jsp" %>
        </td>
    </tr>
</table>
</body>
</html>
<script>
    function setValue(obj) {
        if (obj.checked) {
            document.getElementById('selectedItemId').value = document.getElementById('selectedItemId').value + "," + obj.value;
            document.getElementById('xml').value = document.getElementById('xml').value + '<items xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:java="http://java.sun.com" xsi:type="java:com.appdynamics.xml.ItemVO"><name>' + document.getElementById(obj.value + '_title').innerHTML + '</name></items>';
        }
        else {
            removeid(obj.value);
        }
    }
    function removeid(itemId) {
        var itemIds = document.getElementById('selectedItemId').value;
        var newItems = itemIds.replace(',' + itemId, '');
        var xmldata = document.getElementById('xml').value;
//alert(xmldata);
        var data = '<items xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:java="http://java.sun.com" xsi:type="java:com.appdynamics.xml.ItemVO"><name>' + document.getElementById(itemId + '_title').innerHTML + '</name></items>';
        var newxmldata = xmldata.replace(data, '');

        document.getElementById('xml').value = newxmldata;
        alert(document.getElementById('xml').value);
    }
    function submitXML() {
        document.forms['itemsForm'].action = 'ViewCart!addToCartXML.action';
        var xmldata1 = '<?xml version="1.0" encoding="UTF-8"?><cart-vO>' + document.getElementById('xml').value + '</cart-vO>';

        document.getElementById('xml').value = xmldata1;

        alert("Posting following data \n" + xmldata1);
    }
</script>