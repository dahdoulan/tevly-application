# Tveely BackEnd

## Overview

- This repo represents the backend application of tveely.
- ## DB Schema Setup:
- Run the following script on dbeaver:
- drop owned by tveely;
  drop role tveely;
  create role tveely NOSUPERUSER NOCREATEDB NOCREATEROLE LOGIN NOREPLICATION password 'tveely';
  create schema tveely authorization tveely;
