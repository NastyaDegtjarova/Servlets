<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
<p>
    ${prod.idProd}
</p>
        <form action = "/prod" method="put">
            <input type = "text" name="name" value = "${prod.nameProd}"/>
            <input type = "hidden" name="id" value = "${prod.idProd}"/>
            <input type = "submit" value = "Update"/>
        </form>
</body>
</html>
