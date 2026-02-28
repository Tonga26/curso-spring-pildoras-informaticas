<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario Alumnos</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .contenedor-estilo { background: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 400px; width: 100%; text-align: center; }
        input[type="text"] { width: 100%; padding: 12px; margin: 15px 0; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; font-size: 14px; }
        input[type="submit"] { background-color: #3498db; color: white; border: none; padding: 12px 20px; border-radius: 5px; cursor: pointer; font-size: 16px; width: 100%; transition: background 0.3s; }
        input[type="submit"]:hover { background-color: #2980b9; }
        .link-volver { display: inline-block; margin-top: 15px; text-decoration: none; color: #95a5a6; font-size: 14px; }
        .link-volver:hover { color: #34495e; text-decoration: underline; }
        hr { border: 0; border-top: 1px solid #eee; margin: 20px 0; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
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

        <h3>Ingresa el nombre del alumno</h3>

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
        <input type="text" name="nombreAlumno" placeholder="Ej: Gastón...">

        <%-- Botón que dispara la petición HTTP GET hacia el servidor --%>
        <input type="submit" value="Enviar">

    </form>

    <hr>

    <%--
      SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
      Para mantener la consistencia en la navegación, agregamos el botón de regreso
      al Menú Central, por si el usuario entra al formulario pero decide no enviarlo.
    --%>
    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>
</div>

</body>
</html>