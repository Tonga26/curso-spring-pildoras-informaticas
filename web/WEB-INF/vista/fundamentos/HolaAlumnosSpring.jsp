<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Respuesta Spring</title>
    <%--
      SECCIÓN: RUTAS ABSOLUTAS DINÁMICAS PARA RECURSOS ESTÁTICOS
      El uso de ${pageContext.request.contextPath} es una práctica excelente.
      * COMPARACIÓN CON JAVA EE:
      En HTML estático, si el nombre de tu archivo .war cambiaba en Tomcat,
      todas las rutas relativas de tus CSS e imágenes se rompían.
      En JSP, al usar este comando, se inyecta dinámicamente el nombre correcto de
      la aplicación (ej: /Spring_MVC_Ejemplo1_Web), haciendo que tu diseño sea a
      prueba de fallos y de cambios de servidor.
    --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/recursos/estilos/miEstilo.css">
</head>
<body>

<%--
  SECCIÓN: LECTURA DE PARÁMETROS DIRECTA (JSP EL)
  ${param.nombreAlumno} accede directamente al parámetro enviado por el formulario HTTP.
  * COMPARACIÓN CON JAVA EE:
  En Java EE clásico (Servlets puros), para imprimir esto tenías que abrir etiquetas
  de código Java (scriptlets) y ensuciar el HTML escribiendo:
  <% out.println(request.getParameter("nombreAlumno")); %>
  JSP Expression Language (EL) te permite hacerlo limpio, manteniendo la separación de roles.
--%>
Hola ${param.nombreAlumno}. Bienvenido al curso de Spring.

<p><br>
<h2>Atención a todos</h2>
<%--
  SECCIÓN: LECTURA DE ATRIBUTOS DEL MODELO
  ${mensajeClaro} lee automáticamente el dato que empaquetamos y enviamos
  desde el Controlador usando: modelo.addAttribute("mensajeClaro", mensajeFinal);
--%>
${mensajeClaro}
</p>

<%-- La imagen también se beneficia de la ruta absoluta dinámica --%>
<img src="${pageContext.request.contextPath}/recursos/imgs/imagen_spring.jpg" alt="foto">

<br><br><hr><br>

<%--
  SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
  Agregamos el botón para que el usuario no quede atrapado en esta pantalla final
  y pueda regresar al menú central a probar el ejercicio de Registro de Alumnos.
--%>
<a href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</body>
</html>