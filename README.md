# to-do-list
Gestión de Tareas

# BD Sql Server:
DROP DATABASE IF EXISTS to_do_list;
create DATABASE to_do_list;
go
USE to_do_list;
go

create table task
(
id       INT PRIMARY KEY IDENTITY(1,1)  NOT NULL,
title    VARCHAR(255) NOT NULL,
description   VARCHAR(255) NOT NULL,
completed   BIT NOT NULL,
);
go

# Crear tarea
curl --location 'http://localhost:8080/tasks' \
--header 'Content-Type: application/json' \
--data '{
"title": "ordenar cocina",
"description": "poner cada cosa en su lugar"
}
'
# Modificar tarea
curl --location --request PATCH 'http://localhost:8080/tasks/4' \
--header 'Content-Type: application/json' \
--data '{
"completed": true
}
'
# Consultar lista de tareas
curl --location --request GET 'http://localhost:8080/tasks' \
--header 'Content-Type: application/json' \
--data '
'
# Consultar lista de tareas por titulo
curl --location --request GET 'http://localhost:8080/tasks/title?title=ordenar%20cocina' \
--header 'Content-Type: application/json' \
--data '{
"itemName": "calza",
"price": 10.21,
"idCart": 2
}
'
# Eliminar tarea
curl --location --request DELETE 'http://localhost:8080/tasks/6' \
--header 'Content-Type: application/json' \
--data '
'
