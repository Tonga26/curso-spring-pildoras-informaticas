<%--
    SECCIÓN 1: IMPORTACIONES
    Agregamos la librería de Spring Forms. El prefijo "form" nos permitirá
    usar etiquetas (tags) que se comunican directamente con nuestros objetos Java.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Cliente</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/estilos/estilos.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark mb-4">
    <div class="container">
        <span class="navbar-brand mb-0 h1">
            <i class="fa-solid fa-building"></i> Gestión de Clientes
        </span>
    </div>
</nav>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card p-4 shadow-sm">

                <h3 class="mb-4 text-center">Datos del Cliente</h3>

                <%--
                    ETIQUETA form:form (El corazón del Binding)
                    - action="guardarCliente": Es la URL a la que viajarán los datos al hacer clic en Guardar.
                                               Al llamarse "guardar" reflejamos que sirve para ambas acciones (Insert o Update).
                    - modelAttribute="cliente": Debe llamarse EXACTAMENTE IGUAL que el atributo del Controlador (elModelo.addAttribute("cliente", elCliente);).
                    - method="POST": Oculta los datos de la URL por seguridad.
                --%>
                <form:form action="guardarCliente" modelAttribute="cliente" method="POST">

                    <%--
                        CAMPO OCULTO (Hidden)
                        Esto es vital. Es necesario que el formulario viaje con el ID del cliente.
                        Si el ID es 0 o nulo, Spring/Hibernate sabrá que es un cliente NUEVO (Insert).
                        Si el ID tiene un número, sabrá que debe ACTUALIZARLO (Update).
                    --%>
                    <form:hidden path="id" />

                    <div class="mb-3">
                        <label for="nombre" class="form-label fw-bold">Nombre</label>
                            <%-- path="nombre" reemplaza al clásico name="nombre" de HTML y lo enlaza al atributo de tu clase Java --%>
                        <form:input path="nombre" class="form-control" id="nombre" placeholder="Ej. Juan" required="required" />
                    </div>

                    <div class="mb-3">
                        <label for="apellido" class="form-label fw-bold">Apellido</label>
                        <form:input path="apellido" class="form-control" id="apellido" placeholder="Ej. Pérez" required="required" />
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label fw-bold">Correo Electrónico</label>
                        <form:input path="email" type="email" class="form-control" id="email" placeholder="juan@ejemplo.com" required="required" />
                    </div>

                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">

                        <a href="${pageContext.request.contextPath}/crud/cliente/lista" class="btn btn-outline-secondary me-md-2">
                            <i class="fa-solid fa-arrow-left"></i> Cancelar
                        </a>

                        <button type="submit" class="btn btn-success">
                            <i class="fa-solid fa-floppy-disk"></i> Guardar Cliente
                        </button>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>