<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
        <form action = "/manuf" method="post">
            <input type = "text" name="name" value = "${manuf.nameManufact}"/>
            <input type = "hidden" name="action" value = "save"/>
            <input type = "submit" value = "Save"/>
        </form>
</body>
</html>
