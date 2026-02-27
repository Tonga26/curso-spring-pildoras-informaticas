<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  SECCIÓN: IMPORTACIÓN DE LA LIBRERÍA DE ETIQUETAS DE SPRING
  IMPORTANTE: Esta directiva importa la librería de etiquetas (Tags) de Spring.
  Al definir prefix="form", le decimos a Tomcat: "Cada vez que veas una etiqueta
  HTML que empiece con <form: , no es HTML normal, es código de Spring que debes procesar".
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Formulario de Registro</title>
</head>
<body>

<%--
  SECCIÓN: DECLARACIÓN DEL FORMULARIO VINCULADO (DATA BINDING)
  el action se refiere al método que se va a llamar cuando se envíe la información (relativo a /alumno/).
  el modelAttribute se refiere al nombre identificativo que le dimos al objeto Alumno
  en el controlador (modelo.addAttribute("elAlumno", ...)).

  * COMPARACIÓN CON JAVA EE (HTML PURO):
  En Java EE clásico usábamos simplemente <form action="..." method="POST">.
  Ese formulario estaba "ciego", no sabía nada de nuestras clases de Java.
  Al usar <form:form> de Spring con modelAttribute, estamos atando físicamente
  este formulario HTML a tu objeto 'Alumno' que viaja en la memoria del servidor.
--%>
<form:form action="procesarFormulario" modelAttribute="elAlumno">

    <%--
      SECCIÓN: CAMPOS DE ENTRADA VINCULADOS
      el path hace referencia a los atributos de la clase Alumno.
      Spring usa Reflexión (Reflection) para buscar internamente los métodos getNombre()/setNombre().
      IMPORTANTE: esta tag form:input, al cargar llama al getter y al enviar llama al setter.

      * COMPARACIÓN CON JAVA EE (EL GRAN SALTO):
      En Java EE, si un usuario se equivocaba al llenar un formulario y la página se recargaba,
      el campo volvía a estar vacío. Para evitarlo y mantener lo que escribió, tenías que
      ensuciar tu HTML así:
      <input type="text" name="nombre" value="<%= request.getAttribute('nombre') != null ? request.getAttribute('nombre') : '' %>">

      ¡Con Spring MVC, <form:input path="nombre"> hace todo eso solo!
      1. Si el objeto 'elAlumno' viene vacío del controlador, muestra la caja en blanco.
      2. Si el objeto ya trae datos (por ejemplo, al editar un alumno), Spring llama a
         getNombre() y rellena la caja de texto automáticamente.
      3. Al hacer clic en "Enviar", Spring agarra lo que tipeaste y llama a setNombre().
    --%>
    Nombre: <form:input path="nombre"></form:input>
    <br><br>

    Apellido: <form:input path="apellido"></form:input>
    <br><br>

    <%--
      El botón de enviar sigue siendo HTML puro, ya que su única función
      es disparar la petición HTTP hacia el servidor.
    --%>
    <input type="submit" value="Enviar">
</form:form>

<br><br><hr><br>

<%--
  SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
  Como este es un formulario de prueba, permitimos al usuario arrepentirse
  y volver al menú principal sin necesidad de enviar los datos.
--%>
<a href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</body>
</html>