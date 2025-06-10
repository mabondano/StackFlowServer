# Setup Guide for StackFlowServer

Este documento describe los pasos iniciales para configurar el proyecto en **NetBeans**, la base de datos en **MariaDB** y la estructura de documentación recomendada.

---

## 1. Configuración en NetBeans

1. **Importar el proyecto**

   - Abre NetBeans y selecciona **File > Open Project**.
   - Navega hasta la carpeta raíz `stackflow-server` y haz clic en **Open Project**.

2. **Configurar el servidor WildFly**

   - Ve a **Services > Servers**.
   - Haz clic derecho y selecciona **Add Server**.
   - Elige **WildFly 36** (o la versión que uses) y apunta al directorio de instalación.
   - Configura el puerto de administración (default 9990) y el puerto HTTP (8090).

3. **Configurar el DataSource**

   - Despliega tu servidor en **Services**.
   - Haz clic derecho sobre WildFly > **View Domain** (o **Open Admin Console**).
   - En la consola de administración, ve a **Configuration > Subsystems > Datasources**.
   - Asegúrate de que el datasource `StackflowDS` esté presente y **Enabled**.

4. **Deploy Automático**

   - En el proyecto, haz clic derecho en la raíz > **Properties > Run**.
   - Marca **Deploy on Save** para que cada cambio se despliegue automáticamente.

---

## 2. Configuración de la Base de Datos (MariaDB)

1. **Crear la base de datos**

   ```sql
   CREATE DATABASE stackflowdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   USE stackflowdb;

   USE stackflowdb;
   ```

2. **Ejecutar script de esquema**

   - Abre tu cliente SQL (DBeaver, MySQL Workbench o consola).
   - Ejecuta el contenido de db/db_schema.sql (todos los CREATE TABLE).

3. **Verificar tablas**

   ```sql
   SHOW TABLES;
   ```

Deberías ver las 27 tablas creadas (users, signals, pipelines, pipeline_versions, etc.).

4. **Datasource en WildFly**

Asegúrate de que en standalone-full.xml exista este bloque:

    ```xml
    Kopieren
    <datasource jndi-name="java:/jdbc/StackflowDS" pool-name="StackflowDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:mariadb://localhost:3307/stackflowdb</connection-url>
    <driver>mariadb</driver>
    <security user-name="root" password="root"/>
    </datasource>
    ```

---

3. Estructura de Documentación
   -Coloca los siguientes archivos en la carpeta docs/:
   -README.md: Visión general del proyecto y cómo empezar.
   -docs/SETUP_GUIDE.md: Esta guía de configuración.
   -docs/DATABASE_SCHEMA.md: Detalle de todas las tablas y relaciones.
   -docs/NETBEANS_SETUP.md (opcional): Detallado de NetBeans.
   -docs/DB_SETUP.md (opcional): Detallado de la base de datos.
   -docs/CONTRIBUTING.md: Normas de contribución y estilo de código.
   -docs/DEPLOYMENT.md: Guía de deploy en producción.   
