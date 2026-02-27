<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ejemplo Fundamentos</title>
</head>
<body>
<h1>Primer Ejemplo de MVC con Spring</h1>

<%--
  SECCIÓN: RUTAS RELATIVAS EN VISTAS
  Al no poner una barra "/" al principio del href, estamos creando una RUTA RELATIVA.
  El navegador tomará tu ubicación actual (ej: localhost:8080/TuApp/)
  y le concatenará esta ruta.
  Resultado final: localhost:8080/TuApp/principal/muestraFormulario
--%>
<a href="principal/muestraFormulario">Ir al formulario</a>

<br><br><hr><br>

<%--
  SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
  * COMPARACIÓN CON JAVA EE:
  En HTML estático, volver al inicio a veces rompe las rutas si la app cambia de nombre en Tomcat.
  En JSP, usamos ${pageContext.request.contextPath} para obtener el nombre exacto
  de tu aplicación dinámicamente.
  Al sumarle "/", le decimos que vaya directamente a la raíz (tu Menú Central).
--%>
<a href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</body>
</html>