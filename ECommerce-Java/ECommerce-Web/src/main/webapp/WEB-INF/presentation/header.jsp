<%@ taglib uri="/struts-tags" prefix="s" %>
<script>
    window.onload = function() {
        if (ADRUM)  {
            ADRUM.command ("addUserData", "username", "${session.USER.email}");
            ADRUM.command ("addUserData", "sessionId", "<%= session.getId() %>");
        }

    }
</script>
<div class="mainContainer">
    <div class="innerContainer">
        <div class="header">
            <s:if test="#session.USER != null">
                <span style="color:#FFFFFF;text-align:right;width:100%;float:left;cursor:pointer;text-decoration:underline;font-size:12px;"
                      onclick="logout();">Logout</span>
                                                                         5``
            </s:if>
            <div class="logo"><img src="images/acmeonline.jpg"/></div>

            <%--
                        <div class="topLogin"><a href="#">Sign In</a> <span class="topLogin1">or</span> <a href="#">Register</a></div> --%>

            <div class="menucontainer">
                <s:if test="#session.USER != null">
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
                                                <a href="ViewItems.action"/>HOME
                                            </li>
                                            <li>
                                                <a href="ViewItems.action"/>NEW PRODUCTS
                                            </li>
                                                <%--
                                                <li style="background-image:none;">
                                                <a href="#">CONTACT US</a>
                                                </li>
                                                --%>
                                        </ul>

                                    </div>
                                </div>
                            </th>
                            <th>
                                <div class="menurightbg"></div>
                            </th>
                        </tr>

                    </table>
                </s:if>
            </div>

        </div>
    </div>
</div>


<script>
    function logout() {
        document.location = 'UserLogOut.action';
    }
</script>