<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<script>
    function verify() {

    }

</script>
<body>
<br><br><a href="test.myservlet?data=10">invoke myservlet</b>

    <br><br><br><br><br><b>Submit Form using POST Request</b>

    <form name="mailForm" action="test.mailservlet" method="POST">
        <center>
            Sending mail using javax.mail.Transport.send() method
            <br/><br/>

            <FONT SIZE=4><b>&nbsp;Please enter a valid mail id:</b></FONT>&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="emailId" type="text" maxlength="50" value="appdynamics.neev@gmail.com"
                   onblur="verify();"/><br/>
            <FONT SIZE=4><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter
                the Subject:</b></FONT>&nbsp;&nbsp;&nbsp;&nbsp;
            <input Name="subject" Type="text" maxlength="50" value="test-subject"/><br/>
            <FONT SIZE=4><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter the Body Content:</b></FONT>&nbsp;&nbsp;&nbsp;&nbsp;
            <input Name="content" Type="textarea" rows="3" cols="50" Value="test-content"/><br/>
            <input type="submit" name="button1" value=" Send "/>&nbsp;&nbsp;<input type="reset" name="button2"
                                                                                   value=" clear "/>
        </center>
    </form>
    <br><br><br><br><br><b>Submit Form using Get Request</b>

    <form name="mailForm2" action="test.mailservlet" method="GET">
        <center>
            Sending mail using javax.mail.Transport.send() method
            <br/><br/>

            <FONT SIZE=4><b>&nbsp;Please enter a valid mail id:</b></FONT>&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="emailId" type="text" maxlength="50" value="appdynamics.neev@gmail.com"
                   onblur="verify();"/><br/>
            <FONT SIZE=4><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter
                the Subject:</b></FONT>&nbsp;&nbsp;&nbsp;&nbsp;
            <input Name="subject" Type="text" maxlength="50" value="test-subject"/><br/>
            <FONT SIZE=4><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter the Body Content:</b></FONT>&nbsp;&nbsp;&nbsp;&nbsp;
            <input Name="content" Type="textarea" rows="3" cols="50" Value="test-content"/><br/>
            <input type="submit" name="button1" value=" Send "/>&nbsp;&nbsp;<input type="reset" name="button2"
                                                                                   value=" clear "/>
        </center>
    </form>
</body>
</html>