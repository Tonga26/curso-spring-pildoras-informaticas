<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">

    <title>Gestión de Clientes - CRUD</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/estilos/estilos.css">
</head>
<body>
<nav class="navbar navbar-dark bg-dark mb-4">

    <div class="container">

            <span class="navbar-brand mb-0 h1">
                <i class="fa-solid fa-building"></i> Gestión de Clientes
            </span>
    </div>
</nav>

<div class="container">

    <div class="card p-4">

        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2>Directorio de Clientes</h2>

            <a href="muestraFormularioAgregar" class="btn btn-success">
                <i class="fa-solid fa-plus"></i> Agregar Cliente
            </a>
        </div>

        <div class="table-responsive">

            <table class="table table-striped table-hover align-middle">

                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th class="text-center">Acciones</th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="cliente" items="${clientes}">

                    <%--
                    Construimos la ruta de forma dinámica para cada cliente de la lista.
                     --%>
                    <c:url var="linkActualizar" value="/crud/cliente/muestraFormularioActualizar">

                        <%-- Inyectamos el ID del cliente actual en la URL --%>
                        <%-- Spring y el navegador construyen una URL con este formato al final,
                        por ejemplo: .../muestraFormularioActualizar?clienteId=5
                        Ese signo de interrogación (?) significa que empieza el envío de variables --%>
                        <c:param name="clienteId" value="${cliente.id}" />
                    </c:url>

                    <tr>
                        <td>${cliente.id}</td>

                        <td>${cliente.nombre}</td>

                        <td>${cliente.apellido}</td>

                        <td>${cliente.email}</td>

                        <td class="text-center">

                            <a href="${linkActualizar}" class="btn btn-primary btn-sm">
                                <i class="fa-solid fa-pen-to-square"></i> Editar
                            </a>

                            <a href="#" class="btn btn-danger btn-sm ms-1">
                                <i class="fa-solid fa-trash"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>