<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">

    <title>Gestión de Clientes - CRUD</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <style>
        /* 'body': Seleccionamos el cuerpo de la página para darle un color de fondo gris muy suave */
        body {
            background-color: #f8f9fa;
        }

        /* '.table-hover tbody tr:hover': Pseudo-clase que indica que, cuando el puntero del mouse
           pase sobre una fila de la tabla, esta cambiará sutilmente a un tono gris un poco más oscuro */
        .table-hover tbody tr:hover {
            background-color: #f1f3f5;
        }

        /* '.card': A nuestro contenedor principal le daremos una sombra suave y bordes redondeados
           para que parezca que flota sobre el fondo */
        .card {
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 10px;
            border: none;
        }
    </style>
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

            <a href="#" class="btn btn-success">
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

                    <tr>
                        <td>${cliente.id}</td>

                        <td>${cliente.nombre}</td>

                        <td>${cliente.apellido}</td>

                        <td>${cliente.email}</td>

                        <td class="text-center">

                            <a href="#" class="btn btn-primary btn-sm">
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