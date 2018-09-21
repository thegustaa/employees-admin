Prerequisitos:
- JDK 1.8
- Apache Maven instalado (setear en la variable de ambiente PATH la ruta al mvn.exe) 
- Base de datos: MySQL 

Ejecución desde consola de Windows:
- git clone https://github.com/thegustaa/employees-admin.git 
- cd employees-admin
- Crear base de datos, usuario "employees_usr", contraseña "employees_usr", tablas y 
demás objetos:
	- Ver model.sql con ejemplo.
	- Puede cambiar la configuración en resources/application.properties
- Abrir terminal CMD
- Setear JAVA_HOME al JDK 1.8 (Ejemplo: set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181)
- Iniciar API: mvnw spring-boot:run (para detener: Ctrl+C)


Importar en Eclipse:
- git clone https://github.com/thegustaa/employees-admin.git 
- Abra el Eclipse
- File -> Import -> Maven -> Existing Maven project y seleccione la carpeta "employees-admin"

Ejecutar desde Eclipse:
- Abra "Run configuration" (en el botón verde "Run")
- Crear una nueva "Java Application"
- Complete los datos:
	- Name: employees-admin
	- Main class: com.api.employeeadmin.EmployeeAdminApplication
	- Project: employees-admin
- Presione "Run"


Ejemplos:
1)
curl -X POST -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/add --data '{
  "first_name": "Carolina",
  "last_name": "Perez",
  "email": "caro@email.com",
  "phone_number": "1111-2222",
  "hire_date": "01/10/2018",
  "salary": "11111",
  "job": {
  	"job_title": "Programadora",
  	"min_salary": "10000",
  	"max_salary": "15000"
  }
}'

2)
http://localhost:8080/findBy?last_name=Perez&page=0&page_size=3

3)
curl -X POST -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/addDepartment --data '{
"departmentName": "RRHH", 
"managerId": 100, 
"locationId": 1
}'