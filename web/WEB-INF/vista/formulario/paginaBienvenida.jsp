<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bienvenida - Registro de Alumnos</title>
</head>
<body>
<h1>Segundo Ejemplo de MVC con Spring</h1>
<h2>Página de registro de alumnos</h2>

<%--
  SECCIÓN: ENRUTAMIENTO RELATIVO HACIA EL CONTROLADOR
  Al usar "alumno/muestraFormulario", estamos apuntando exactamente al namespace
  que definimos en la clase AlumnoController con @RequestMapping("/alumno").

  * COMPARACIÓN CON JAVA EE:
  En HTML estático o Servlets tradicionales, si movías tu Servlet a otro paquete
  o cambiabas su mapeo en el web.xml, tenías que rastrear todos los archivos JSP
  para actualizar los enlaces a mano. En Spring, la ruta relativa mantiene la cohesión.
--%>
<a href="alumno/muestraFormulario">Ir al formulario de registro</a>

<br><br><hr><br>

<%--
  SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
  Mantenemos la simetría de nuestra arquitectura agregando el botón
  para volver al menú central, permitiendo saltar libremente entre ejercicios.
  ${pageContext.request.contextPath} garantiza que siempre apuntemos a la raíz de la app.
--%>
<a href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</body>
</html>