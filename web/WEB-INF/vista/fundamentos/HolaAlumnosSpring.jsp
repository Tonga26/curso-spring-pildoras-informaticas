<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/recursos/estilos/miEstilo.css">
</head>
<body>
Hola ${param.nombreAlumno}. Bienvenido al curso de Spring.

<p><br>
    <h2>Atención a todos</h2>
    ${mensajeClaro}
</p>
<img src="${pageContext.request.contextPath}/recursos/imgs/imagen_spring.jpg" alt="foto">
</body>
</html>
