<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmación de Registro</title>
</head>
<body>
<h2>¡Registro Exitoso!</h2>

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
El alumno con nombre <b>${elAlumno.nombre}</b> y apellido <b>${elAlumno.apellido}</b> se ha registrado con éxito.

<br><br><hr><br>

<%--
  SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
  Cerramos el ciclo perfecto. El usuario registró el dato exitosamente y ahora puede
  volver a la pantalla principal de tu portfolio para seguir explorando otros ejercicios.
--%>
<a href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</body>
</html>