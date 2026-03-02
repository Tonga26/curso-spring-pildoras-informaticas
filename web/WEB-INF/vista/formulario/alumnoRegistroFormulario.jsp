<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .error-validacion { color: #e74c3c; font-size: 12px; font-weight: bold; display: block; margin-top: 5px; }
    </style>
</head>
<body>

<div class="contenedor-estilo">
    <h2>Registro de Nuevo Alumno</h2>

    <form:form action="procesarFormulario" modelAttribute="elAlumno">

        <%--
          SECCIÓN: MANEJO VISUAL DE ERRORES
          <form:errors> escucha directamente al BindingResult de tu Controlador.
          Si hay un error para este 'path', extrae el 'message' que pusiste en la clase
          Java y lo dibuja aquí. Al usar cssClass="error-validacion", se pintará de rojo.
        --%>
        <div class="campo-grupo">
            <b>Nombre:</b> <br>
            <form:input path="nombre"></form:input>
            <form:errors path="nombre" cssClass="error-validacion"></form:errors>
        </div>

        <div class="campo-grupo">
            <b>Apellido:</b> <br>
            <form:input path="apellido"></form:input>
            <form:errors path="apellido" cssClass="error-validacion"></form:errors>
        </div>

        <div class="campo-grupo">
            <b>Edad:</b> <br>
            <form:input path="edad"></form:input>
            <form:errors path="edad" cssClass="error-validacion"></form:errors>
        </div>

        <div class="campo-grupo">
            <b>E-mail:</b> <br>
            <form:input path="email"></form:input>
            <form:errors path="email" cssClass="error-validacion"></form:errors>
        </div>

        <div class="campo-grupo">
            <b>Código Postal:</b> <br>
            <form:input path="codigoPostal"></form:input>
            <form:errors path="codigoPostal" cssClass="error-validacion"></form:errors>
        </div>

        <div class="campo-grupo">
            <b>Asignaturas optativas:</b> <br>
            <form:select path="optativa" multiple="true">
                <form:option value="Programación" label="Programación"></form:option>
                <form:option value="Bases de datos" label="Bases de datos"></form:option>
                <form:option value="Matemática" label="Matemática"></form:option>
                <form:option value="Inglés" label="Inglés"></form:option>
            </form:select>
        </div>

        <div class="campo-grupo">
            <b>Ciudad de Estudios:</b> <br>
            Mendoza <form:radiobutton path="ciudadEstudios" value="Mendoza"></form:radiobutton> |
            Buenos Aires <form:radiobutton path="ciudadEstudios" value="Buenos Aires"></form:radiobutton> |
            Córdoba <form:radiobutton path="ciudadEstudios" value="Córdoba"></form:radiobutton> |
            Santa Fe <form:radiobutton path="ciudadEstudios" value="Santa Fe"></form:radiobutton>
        </div>

        <div class="campo-grupo">
            <b>Idiomas:</b> <br>
            Inglés <form:checkbox path="idiomasAlumno" value="Inglés"></form:checkbox> |
            Francés <form:checkbox path="idiomasAlumno" value="Francés"></form:checkbox> |
            Alemán <form:checkbox path="idiomasAlumno" value="Alemán"></form:checkbox> |
            Chino <form:checkbox path="idiomasAlumno" value="Chino"></form:checkbox>
        </div>

        <input type="submit" value="Completar Registro">
    </form:form>

    <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

    <a class="link-volver" href="${pageContext.request.contextPath}/">⬅ Volver al Menú Principal</a>

</div>

</body>
</html>