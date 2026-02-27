<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario Alumnos</title>
</head>
<body>
<div>
    <%--
      SECCIÓN: FORMULARIO HTML BÁSICO
      Este es un formulario HTML estándar, todavía no estamos usando la "magia"
      de las etiquetas form:form de Spring (eso viene en el proyecto de Registro).

      * COMPARACIÓN CON JAVA EE:
      En Java EE (Servlets), el action normalmente apuntaba a la URL absoluta de un Servlet
      configurado a mano en el archivo web.xml.
      En Spring MVC, este action ("procesaFormulario2") es una RUTA RELATIVA que se
      apoya en el @RequestMapping("/principal") de tu controlador para llegar a destino.
    --%>
    <form action="procesaFormulario2" method="get">

        <%--
          SECCIÓN: ENTRADA DE DATOS
          El atributo 'name' ("nombreAlumno") es CRUCIAL. Es la llave que identifica
          este dato cuando viaja por la red.

          * COMPARACIÓN CON JAVA EE vs SPRING:
          En un Servlet puro, el backend está obligado a buscar exactamente esta llave usando:
          String nombre = request.getParameter("nombreAlumno");
          En Spring MVC, delegamos ese trabajo engorroso usando la anotación en los parámetros del método:
          @RequestParam("nombreAlumno") String nombre
        --%>
        <input type="text" name="nombreAlumno">

        <%-- Botón que dispara la petición HTTP GET hacia el servidor --%>
        <input type="submit" value="Enviar">

    </form>
</div>

<br><br><hr><br>

<%--
  SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
  Para mantener la consistencia en la navegación, agregamos el botón de regreso
  al Menú Central, por si el usuario entra al formulario pero decide no enviarlo.
--%>
<a href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</body>
</html>