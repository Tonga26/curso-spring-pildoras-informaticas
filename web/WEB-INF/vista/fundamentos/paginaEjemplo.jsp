<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ejemplo Fundamentos</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .contenedor-estilo { background: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 500px; width: 100%; text-align: center; }
        h1 { color: #2c3e50; font-size: 24px; margin-bottom: 20px; }
        .btn-principal { display: inline-block; background-color: #3498db; color: white; padding: 12px 25px; text-decoration: none; border-radius: 5px; font-weight: bold; font-size: 16px; transition: background 0.3s; margin-bottom: 20px; }
        .btn-principal:hover { background-color: #2980b9; }
        .link-volver { display: inline-block; margin-top: 10px; text-decoration: none; color: #95a5a6; font-size: 14px; transition: color 0.3s; }
        .link-volver:hover { color: #34495e; text-decoration: underline; }
        hr { border: 0; border-top: 1px solid #eee; margin: 20px 0; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
    <h1>Primer Ejemplo de MVC con Spring</h1>

    <%--
      SECCIÓN: RUTAS RELATIVAS EN VISTAS
      Al no poner una barra "/" al principio del href, estamos creando una RUTA RELATIVA.
      El navegador tomará tu ubicación actual (ej: localhost:8080/TuApp/)
      y le concatenará esta ruta.
      Resultado final: localhost:8080/TuApp/principal/muestraFormulario
    --%>
    <a class="btn-principal" href="principal/muestraFormulario">Ir al formulario</a>

    <hr>

    <%--
      SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
      * COMPARACIÓN CON JAVA EE:
      En HTML estático, volver al inicio a veces rompe las rutas si la app cambia de nombre en Tomcat.
      En JSP, usamos ${pageContext.request.contextPath} para obtener el nombre exacto
      de tu aplicación dinámicamente.
      Al sumarle "/", le decimos que vaya directamente a la raíz (tu Menú Central).
    --%>
    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>
</div>

</body>
</html>