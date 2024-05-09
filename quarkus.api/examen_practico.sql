-- Creacion de tabla GENDERS
CREATE TABLE GENDERS (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(255)
);

-- Creacion de tabla JOBS
CREATE TABLE JOBS (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(255),
    SALARY NUMBER(9,2)
);

-- Creacion de tabla EMPLOYEES
CREATE TABLE EMPLOYEES (
    ID NUMBER PRIMARY KEY,
    GENDER_ID NUMBER,
    JOB_ID NUMBER,
    NAME VARCHAR2(255),
    LAST_NAME VARCHAR2(255),
    BIRTHDATE DATE,
    FOREIGN KEY (GENDER_ID) REFERENCES GENDERS(ID),
    FOREIGN KEY (JOB_ID) REFERENCES JOBS(ID)
);

-- Creacion de tabla EMPLOYEE_WORKED_HOURS
CREATE TABLE EMPLOYEE_WORKED_HOURS (
    ID NUMBER PRIMARY KEY,
    EMPLOYEE_ID NUMBER,
    WORKED_HOURS NUMBER,
    WORKED_DATE DATE,
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEES(ID)
);

--------------function para obtener salario total pagado a un empleado en un rango de fechas------------

CREATE OR REPLACE FUNCTION CALCULATE_TOTAL_SALARY(
    employee_id IN NUMBER,
    start_date IN DATE,
    end_date IN DATE
)
RETURN NUMBER
IS
    total_salary NUMBER;
BEGIN
    SELECT SUM(e.salary * h.worked_hours)
    INTO total_salary
    FROM employees e
    JOIN employee_worked_hours h ON e.id = h.employee_id
    WHERE e.id = employee_id
    AND h.worked_date BETWEEN start_date AND end_date;

    RETURN total_salary;
END;
/

--------------store procedure para insertar las horas trabajadas para un empleado especifico------------

CREATE OR REPLACE PROCEDURE INSERT_WORKED_HOURS(
    employee_id IN NUMBER,
    hours_worked IN NUMBER,
    worked_date IN DATE
)
IS
BEGIN
    INSERT INTO employee_worked_hours (employee_id, worked_hours, worked_date)
    VALUES (employee_id, hours_worked, worked_date);
    COMMIT;
END;
/
