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
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; display: flex; justify-content: center; padding: 40px; }
        .contenedor-estilo { background: #ffffff; padding: 30px 40px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 500px; width: 100%; }
        h2 { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 10px; margin-top: 0; }
        .campo-grupo { margin-bottom: 20px; }
        input[type="text"], select { width: 100%; padding: 10px; margin-top: 5px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; font-size: 14px; }
        input[type="submit"] { background-color: #3498db; color: white; border: none; padding: 12px 20px; border-radius: 5px; cursor: pointer; font-size: 16px; width: 100%; margin-top: 10px; transition: background 0.3s; }
        input[type="submit"]:hover { background-color: #2980b9; }
        .link-volver { display: inline-block; margin-top: 20px; text-decoration: none; color: #7f8c8d; font-weight: bold; font-size: 14px; }
        .link-volver:hover { color: #34495e; text-decoration: underline; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
    <h2>Registro de Nuevo Alumno</h2>

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
        <div class="campo-grupo">
            <b>Nombre:</b> <br>
            <form:input path="nombre"></form:input>
        </div>

        <div class="campo-grupo">
            <b>Apellido:</b> <br>
            <form:input path="apellido"></form:input>
        </div>

        <%--
          SECCIÓN: MENÚ DESPLEGABLE VINCULADO
          <form:select> crea la etiqueta HTML <select>.
          path="optativa" lo vincula al atributo 'optativa' de la clase Alumno.
          multiple="true" permite seleccionar varias opciones con Ctrl+Clic.

          * COMPARACIÓN CON JAVA EE:
          Mantener seleccionado un elemento de una lista desplegable en Java EE
          requería lógica compleja en el JSP (usando if/else dentro de cada <option>).
          Spring maneja esto automáticamente si el objeto ya tiene un valor preasignado.
        --%>
        <div class="campo-grupo">
            <b>Asignaturas optativas:</b> <br>
            <form:select path="optativa" multiple="true">
                <form:option value="Programación" label="Programación"></form:option>
                <form:option value="Bases de datos" label="Bases de datos"></form:option>
                <form:option value="Matemática" label="Matemática"></form:option>
                <form:option value="Inglés" label="Inglés"></form:option>
            </form:select>
        </div>

        <%--
          SECCIÓN: BOTONES DE RADIO VINCULADOS
          <form:radiobutton> crea la etiqueta HTML <input type="radio">.
          path="ciudadEstudios" asegura que todos pertenezcan al mismo grupo,
          por lo que solo se puede seleccionar uno a la vez.
        --%>
        <div class="campo-grupo">
            <b>Ciudad de Estudios:</b> <br>
            Mendoza <form:radiobutton path="ciudadEstudios" value="Mendoza"></form:radiobutton> |
            Buenos Aires <form:radiobutton path="ciudadEstudios" value="Buenos Aires"></form:radiobutton> |
            Córdoba <form:radiobutton path="ciudadEstudios" value="Córdoba"></form:radiobutton> |
            Santa Fe <form:radiobutton path="ciudadEstudios" value="Santa Fe"></form:radiobutton>
        </div>

        <%--
          SECCIÓN: CASILLAS DE VERIFICACIÓN VINCULADAS
          <form:checkbox> crea la etiqueta HTML <input type="checkbox">.
          Al compartir el mismo path="idiomasAlumno", Spring sabe que debe
          agrupar las opciones seleccionadas y enviarlas juntas al objeto.
        --%>
        <div class="campo-grupo">
            <b>Idiomas:</b> <br>
            Inglés <form:checkbox path="idiomasAlumno" value="Inglés"></form:checkbox> |
            Francés <form:checkbox path="idiomasAlumno" value="Francés"></form:checkbox> |
            Alemán <form:checkbox path="idiomasAlumno" value="Alemán"></form:checkbox> |
            Chino <form:checkbox path="idiomasAlumno" value="Chino"></form:checkbox>
        </div>

        <%--
          El botón de enviar sigue siendo HTML puro, ya que su única función
          es disparar la petición HTTP hacia el servidor.
        --%>
        <input type="submit" value="Completar Registro">
    </form:form>

    <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

    <%--
      SECCIÓN NUEVA: NAVEGACIÓN HACIA EL DASHBOARD
      Como este es un formulario de prueba, permitimos al usuario arrepentirse
      y volver al menú principal sin necesidad de enviar los datos.
    --%>
    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</div>

</body>
</html>