1. list all projects
gradle projects

2. insert sample data
   INSERT INTO address VALUES (1,'Happy Street','Delhi'),(2,'Down the town Street','NY');
   INSERT INTO student ("id", "address_id", "email", "first_name", "last_name") VALUES ('1', '1', 'raj_dave@yahoo.com', 'Raj', 'Dave');
   INSERT INTO student ("id", "address_id", "email", "first_name", "last_name") VALUES ('2', '2', 'john_smith@gmail.com', 'John', 'Smith');

3. test address service 
   curl 'http://localhost:8082/api/address/getById/1'
   curl 'http://localhost:8080/api/student/getById/1'

calling through api gateway
   curl 'http://localhost:9090/student-service/api/student/getById/1'

   getting 404? wait a minute, it takes time for api gateway to discover services
