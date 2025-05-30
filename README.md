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
spring.datasource.username=tveely;
spring.jpa.hibernate.ddl-auto=none;
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect;spring.jpa.properties.hibernate.format_sql=true;spring.jpa.show-sql=true;
spring.liquibase.enabled=true;
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml;
spring.liquibase.default-schema=tveely;
spring.liquibase.drop-first=false;
spring.liquibase.contexts=development;
spring.liquibase.labels=version-1;
SPRING_MAIL_HOST=localhost;
SPRING_MAIL_PORT=1025;
SPRING_MAIL_USERNAME=Teveely;
SPRING_MAIL_PASSWORD=Teveely;
SPRING_MAIL_PROPERTIES_MAIL_SMTP_TRUST=*;
SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true;
SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLED=true;
SPRING_MAIL_PROPERTIES_MAIL_CONNECTIONTIMEOUT=5000;
SPRING_MAIL_PROPERTIES_MAIL_TIMEOUT=3000;
SPRING_MAIL_PROPERTIES_MAIL_WRITETIMEOUT=5000;
APPLICATION_SECURITY_JWT_SECRET_KEY=cfd6cd3cb5a78bece694e9a8a58fbd875a7308b337c526ea787815c7dade888b;APPLICATION_SECURITY_JWT_EXPIRATION=3153600000000;
APPLICATION_MAILING_FRONTEND_ACTIVATION_URL=http://localhost:4200/activate-account;ENCODING_OUTPUT_DIRECTORY=C:\encoded;spring.datasource.hikari.auto-commit=false;spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true;
ENCODING_OUTPUT_DIRECTORY=C:\encoded;
AZURE_CONTAINER_NAME=tveely;
VIDEO_PROCESSING_DIRECTORY=./resources/users/;
AZURE_STORAGE_CONNECTION_STRING=You should add your own connection key here
## Add this on the liquibase module only.

spring.main.web-application-type=NONE;
