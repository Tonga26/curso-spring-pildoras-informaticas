<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmación de Registro</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; display: flex; justify-content: center; padding: 40px; }
        .contenedor-estilo { background: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 500px; width: 100%; text-align: center; }
        h2 { color: #27ae60; margin-top: 0; }
        .datos-registro { background-color: #f8f9fa; border-left: 4px solid #3498db; padding: 15px; margin: 20px 0; text-align: left; line-height: 1.6; }
        .link-volver { display: inline-block; margin-top: 20px; text-decoration: none; color: #3498db; font-weight: bold; border: 1px solid #3498db; padding: 8px 15px; border-radius: 5px; transition: all 0.3s; }
        .link-volver:hover { background-color: #3498db; color: white; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
    <h2>¡Registro Exitoso! ✅</h2>

    <%--
      SECCIÓN: LECTURA DE OBJETOS DESDE EL MODELO (JSP EL)
      Aquí usamos Expression Language (EL) de JSP -> ${}
      Al escribir ${elAlumno.nombre}, JSP por debajo está usando Reflexión para ejecutar obj.getNombre().
      Es la forma limpia de evitar escribir código Java (scriptlets <%%>) intercalado en el HTML.

      * COMPARACIÓN CON JAVA EE (SERVLETS PUROS):
      En la vieja escuela de Java EE, para mostrar el nombre de un objeto que venía
      en el 'request', tenías que romper tu diseño HTML con un bloque de código Java espantoso,
      hacer un casteo (cast) manual y usar un 'out.print', algo así:

      <%
         Alumno alu = (Alumno) request.getAttribute("elAlumno");
         if(alu != null) {
             out.print(alu.getNombre());
         }
      %>

      ¡Un dolor de cabeza! Con Spring y EL (${elAlumno.nombre}), la vista se mantiene 100%
      como una plantilla limpia, ideal para que la modifique un diseñador web sin romper la lógica.
    --%>

    <div class="datos-registro">
        El alumno <b>${elAlumno.nombre} ${elAlumno.apellido}</b> se ha registrado con éxito.<br><br>
        <b>Edad:</b> ${elAlumno.edad} <br>
        <b>E-mail:</b> ${elAlumno.email} <br>
        <b>Asignatura/s:</b> ${elAlumno.optativa} <br>
        <b>Ciudad:</b> ${elAlumno.ciudadEstudios} <br>
        <b>Idiomas:</b> ${elAlumno.idiomasAlumno}
    </div>

    <%--
      NAVEGACIÓN HACIA EL DASHBOARD
      Cerramos el ciclo perfecto. El usuario registró el dato exitosamente y ahora puede
      volver a la pantalla principal de tu portfolio para seguir explorando otros ejercicios.
    --%>
    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</div>

</body>
</html>