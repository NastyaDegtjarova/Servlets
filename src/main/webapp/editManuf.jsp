<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
<p>
    ${manuf.idManufact}
</p>
        <form action = "/manuf" method="post">
            <input type = "text" name="name" value = "${manuf.nameManufact}"/>
            <input type = "hidden" name="id" value = "${manuf.idManufact}"/>
            <input type = "hidden" name="action" value = "update"/>
            <input type = "submit" value = "Update"/>
        </form>
</body>
</html>
