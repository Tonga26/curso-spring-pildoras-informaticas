<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bienvenida - Registro de Alumnos</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .contenedor-estilo { background: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 500px; width: 100%; text-align: center; }
        h1 { color: #2c3e50; font-size: 24px; margin-bottom: 10px; }
        h2 { color: #7f8c8d; font-size: 18px; font-weight: normal; margin-bottom: 30px; }
        .btn-principal { display: inline-block; background-color: #3498db; color: white; padding: 12px 25px; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 16px; transition: background 0.3s; margin-bottom: 20px; }
        .btn-principal:hover { background-color: #2980b9; }
        .link-volver { display: inline-block; margin-top: 10px; text-decoration: none; color: #95a5a6; font-size: 14px; transition: color 0.3s; }
        .link-volver:hover { color: #34495e; text-decoration: underline; }
        hr { border: 0; border-top: 1px solid #eee; margin: 20px 0; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
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
    <a class="btn-principal" href="alumno/muestraFormulario">Ir al formulario de registro</a>

    <hr>

    <%--
      SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
      Mantenemos la simetría de nuestra arquitectura agregando el botón
      para volver al menú central, permitiendo saltar libremente entre ejercicios.
      ${pageContext.request.contextPath} garantiza que siempre apuntemos a la raíz de la app.
    --%>
    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>
</div>

</body>
</html>