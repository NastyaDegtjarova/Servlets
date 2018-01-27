<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
<p>
    ${prod.idProd}
</p>
        <form action = "/prod" method="post">
            <input type = "text" name="name" value = "${prod.nameProd}"/>
            <input type = "hidden" name="id" value = "${prod.idProd}"/>
            <input type = "hidden" name="action" value = "update"/>
            <input type = "submit" value = "Update"/>

        </form>
</body>
</html>
