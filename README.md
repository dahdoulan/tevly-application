# Tveely BackEnd
## Overview
- This repo represents the backend application of tveely.
- ## DB Schema Setup:
- Run the following script on dbeaver:
- drop owned by tveely;
  drop role tveely;
  create role tveely NOSUPERUSER NOCREATEDB NOCREATEROLE LOGIN NOREPLICATION password 'tveely';
  create schema tveely authorization tveely;
## Enviroment Variables:
spring.datasource.driver-class-name=org.postgresql.Driver;
spring.datasource.password=tveely;
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres;
spring.datasource.username=tveely;spring.jpa.hibernate.ddl-auto=none;spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect;
spring.jpa.properties.hibernate.format_sql=true;spring.jpa.show-sql=true;
spring.liquibase.enabled=true;
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml;
spring.liquibase.default-schema=tveely;spring.liquibase.drop-first=false;
spring.liquibase.contexts=development;spring.liquibase.labels=version-1;
## Add this on the liquibase module only.
spring.main.web-application-type=NONE;