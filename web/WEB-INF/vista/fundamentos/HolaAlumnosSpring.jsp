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
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; padding: 20px; box-sizing: border-box;}
        .contenedor-estilo { background: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 500px; width: 100%; text-align: center; }
        .mensaje-destacado { background-color: #f8f9fa; border-left: 4px solid #3498db; padding: 15px; margin: 20px 0; text-align: left; }
        .imagen-spring { max-width: 100%; height: auto; border-radius: 8px; margin-top: 15px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        .link-volver { display: inline-block; margin-top: 20px; text-decoration: none; color: #3498db; font-weight: bold; border: 1px solid #3498db; padding: 8px 15px; border-radius: 5px; transition: all 0.3s; }
        .link-volver:hover { background-color: #3498db; color: white; }
        hr { border: 0; border-top: 1px solid #eee; margin: 20px 0; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
    <%--
      SECCIÓN: LECTURA DE PARÁMETROS DIRECTA (JSP EL)
      ${param.nombreAlumno} accede directamente al parámetro enviado por el formulario HTTP.
      * COMPARACIÓN CON JAVA EE:
      En Java EE clásico (Servlets puros), para imprimir esto tenías que abrir etiquetas
      de código Java (scriptlets) y ensuciar el HTML escribiendo:
      <% out.println(request.getParameter("nombreAlumno")); %>
      JSP Expression Language (EL) te permite hacerlo limpio, manteniendo la separación de roles.
    --%>
    <h3>Hola, <span style="color:#3498db;">${param.nombreAlumno}</span>. Bienvenido al curso de Spring.</h3>

    <div class="mensaje-destacado">
        <h2 style="margin-top: 0; color: #2c3e50; font-size: 18px;">Atención a todos</h2>
        <%--
          SECCIÓN: LECTURA DE ATRIBUTOS DEL MODELO
          ${mensajeClaro} lee automáticamente el dato que empaquetamos y enviamos
          desde el Controlador usando: modelo.addAttribute("mensajeClaro", mensajeFinal);
        --%>
        ${mensajeClaro}
    </div>

    <%-- La imagen también se beneficia de la ruta absoluta dinámica y recibe estilos CSS --%>
    <img class="imagen-spring" src="${pageContext.request.contextPath}/recursos/imgs/imagen_spring.jpg" alt="Logo de Spring Framework">

    <hr>

    <%--
      SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
      Agregamos el botón para que el usuario no quede atrapado en esta pantalla final
      y pueda regresar al menú central a probar el ejercicio de Registro de Alumnos.
    --%>
    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>
</div>

</body>
</html>