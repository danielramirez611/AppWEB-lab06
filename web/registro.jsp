<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Productos</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Registro de Productos</h1>
        <form action="ProductoServlet" method="post" class="mt-3">
            <div class="form-group">
                <label for="codigo">Código:</label>
                <input type="text" id="codigo" name="codigo" class="form-control">
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control">
            </div>
            <div class="form-group">
                <label for="precio">Precio:</label>
                <input type="text" id="precio" name="precio" class="form-control">
            </div>
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="text" id="stock" name="stock" class="form-control">
            </div>
            <input type="hidden" name="action" value="registrar">
            <button type="submit" class="btn btn-primary">Registrar</button>
            <button type="reset" class="btn btn-secondary">Limpiar</button>
            <a href="listado.jsp" class="btn btn-info mt-3">Ver Listado</a>
            <a href="busqueda.jsp" class="btn btn-info mt-3">Buscar</a>

        </form>
    </div>
    <!-- Bootstrap JS (opcional, si lo necesitas) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
