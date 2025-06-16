# API Reference

All endpoints are under the base path:

```
http://localhost:8090/stackflow/api
```

---

## Users

### 1. List all users

```
GET /users
```

- **Response 200 OK**  
  ```json
  [
    {
      "id": 1,
      "username": "alice",
      "email": "alice@example.com"
    },
    {
      "id": 2,
      "username": "bob",
      "email": "bob@example.com"
    }
  ]
  ```

---

### 2. Get a single user

```
GET /users/{id}
```

- **Path parameter**  
  | Name | Type  | Description          |
  |------|-------|----------------------|
  | id   | Long  | Identifier of user   |

- **Responses**  
  - **200 OK**  
    ```json
    {
      "id": 1,
      "username": "alice",
      "email": "alice@example.com"
    }
    ```
  - **404 Not Found**  
    ```json
    {
      "error": "User not found: 999"
    }
    ```

---

### 3. Create a new user

```
POST /users
Content-Type: application/json
```

- **Request body**  
  ```json
  {
    "username": "alice",
    "email": "alice@example.com"
  }
  ```
- **Responses**  
  - **201 Created**  
    ```json
    {
      "id": 1,
      "username": "alice",
      "email": "alice@example.com"
    }
    ```
  - **400 Bad Request** (validation errors)  
    ```json
    {
      "errors": [
        { "field": "username", "message": "Username is required" },
        { "field": "email",    "message": "Email must be valid" }
      ]
    }
    ```
  - **409 Conflict** (duplicate username)  
    ```json
    {
      "error": "Username ya existe: alice"
    }
    ```

---

### 4. Update an existing user

```
PUT /users/{id}
Content-Type: application/json
```

- **Path parameter**  
  | Name | Type | Description        |
  |------|------|--------------------|
  | id   | Long | Identifier of user |

- **Request body**  
  ```json
  {
    "username": "alice",
    "email": "alice2@example.com"
  }
  ```
- **Responses**  
  - **200 OK**  
    ```json
    {
      "id": 1,
      "username": "alice",
      "email": "alice2@example.com"
    }
    ```
  - **400 Bad Request** (validation errors)  
  - **404 Not Found** (if user doesn’t exist)

---

### 5. Delete a user

```
DELETE /users/{id}
```

- **Path parameter**  
  | Name | Type | Description        |
  |------|------|--------------------|
  | id   | Long | Identifier of user |

- **Responses**  
  - **204 No Content**  
  - **404 Not Found** (if user doesn’t exist)  
    ```json
    {
      "error": "User not found: 999"
    }
    ```

---

## Error format

Except for DELETE `204`, every error returns JSON:

```json
// Validation errors (400)
{ 
  "errors": [ 
    { "field": "email", "message": "Email must be valid" } 
  ] 
}

// Business conflict (409)
{ 
  "error": "Username ya existe: alice" 
}

// Not found (404)
{ 
  "error": "User not found: 999" 
}

// Internal server error (500)
{ 
  "error": "An internal server error occurred" 
}
```

---

> **Next steps**:  
> - Add authentication/authorization headers  
> - Document Signals, Pipelines and other resources following the same pattern  
> - (Optional) Generate an OpenAPI spec or import this into Postman as a JSON collection  
