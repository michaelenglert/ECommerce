<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>AppDynamics Pilot</title>
    <link rel="stylesheet" href="css/appdynamics.css"/>
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
                                                    <div align="center"><img
                                                            src="images/The_Lost_City_Of_Z-David_Grann.jpg"
                                                            alt="The Lost City Of Z" width="80" height="104"/></div>
                                                </th>
                                            </tr>

                                            <tr>
                                                <td height="25" colspan="2">
                                                    <div align="center">The Lost City Of Z</div>
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

                                        <div class="Latest">Latest Books</div>
                                        <div class="Featured">Featured Books</div>
                                        <div class="NewItems">
                                            <div class="latestbooks">
                                                <ul>
                                                    <li><img src="images/Farewell_To_Arms-Ernest_Hemingway.jpg"/></li>
                                                    <li><img src="images/Atlas_Shrugged-Ayn_Rand.jpg"/></li>
                                                    <li style="background-image:none;"><img
                                                            src="images/Jordan-Driven_From_Within.jpg"/></li>

                                                </ul>
                                            </div>

                                        </div>
                                        <div class="Items">

                                            <s:form id="itemsForm" name="itemsForm" action="ViewCart!addToCart.action"
                                                    theme="simple">


                                            <s:hidden name="load" value="%{#parameters.load}"/>
                                            <s:hidden name="delay" value="%{#parameters.delay}"/>
                                            <s:hidden name="error" value="%{#parameters.error}"/>
                                            <s:hidden name="username" value="%{#session.USER.email}"/>

                                            <table border="0" cellspacing="0" cellpadding="0">

                                                <c:forEach var="item" items="${itemsList}" varStatus="status" step="1"
                                                           begin="0">
                                                    <c:if test="${status.count%3==1}">
                                                        <tr>
                                                    </c:if>
                                                    <td>
                                                        <div class="itemBoxes">
                                                            <img src='<c:out value="${item.imagePath}"/>' alt="book1"/>

                                                            <div class="itemDes" id="<c:out value='${item.id}'/>_title">
                                                                <c:out value="${item.title}"/></div>
                                                            <div class="Cart">Add to Cart
                                                                <input type="checkbox" id="selectedId" name="selectedId"
                                                                       value="<c:out value="${item.id}"/>"
                                                                       onclick='setValue(this);'/>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <c:if test="${status.count%3==0}">
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>


                                            </table>
                                        </div>


                                    </div>
                                </div>

                                <s:hidden id="selectedItemId" name="selectedItemId"></s:hidden>
                                <s:hidden id="xml" name="xml"></s:hidden>
                                <table>
                                    <tr>
                                        <td>
                                            <div class="buttonClass"><s:submit cssClass="submit" name="submitValue"
                                                                               key="label.addtocart"/></div>
                                        </td>
                                        <td>
                                            <div class="buttonClass"><s:submit cssClass="submit" name="submitValue"
                                                                               key="lable.postxml"
                                                                               onclick="submitXML();"/></div>
                                        </td>
                                    </tr>
                                </table>
                                </s:form>
                                <div class="lcontainerBot"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
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