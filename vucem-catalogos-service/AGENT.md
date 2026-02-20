# AGENT.md — Guía para generación de CRUDs

## Información mínima requerida para generar un CRUD

1. **Entidad** — ruta del `.java` de la entidad (o su contenido)
2. **Key del catálogo** — clave corta para nombrar rutas, ej: `cat_colonia`, `cat_arancelPro`

Con esos dos datos se generan automáticamente los 6 artefactos descritos abajo.

---

## Estructura de paquetes

```
src/main/java/com/example/vucem_catalogos_service/
├── controller/
│   └── Cat{Nombre}Controller.java
├── business/
│   ├── Interface/
│   │   └── ICat{Nombre}Service.java
│   └── Cat{Nombre}ServiceImpl.java
├── persistence/
│   └── repo/
│       └── ICat{Nombre}Repository.java
├── model/
│   ├── dto/
│   │   └── Cat{Nombre}DTO.java
│   └── entity/
│       └── Cat{Nombre}.java          ← proporcionada por el usuario
└── core/
    └── constants/
        └── CatalogPaths.java         ← se le agregan las constantes nuevas
```

---

## Artefactos a generar (en orden)

### 1. `Cat{Nombre}DTO.java`
- Paquete: `model.dto`
- Anotaciones: `@Data`, `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor`
- Campos: todos los campos de la entidad
  - Si la entidad tiene `@EmbeddedId`, los campos del ID se aplanan en el DTO
  - Si hay FK (`@ManyToOne`) a otra entidad, incluir la clave foránea como `String`/tipo primitivo, y opcionalmente el campo descriptivo (`nombre`) de la entidad relacionada
- Tipos: respetar los tipos de la entidad (`Instant`, `LocalDate`, `String`, `Long`, `Boolean`, etc.)

### 2. `ICat{Nombre}Repository.java`
- Paquete: `persistence.repo`
- Extiende: `JpaRepository<Cat{Nombre}, {TipoPK}>`
  - Si la PK es compuesta usar el `{Nombre}Id` embeddable
- Anotación: `@Repository`
- Queries JPQL con constructor DTO:
  - `search(@Param("search") String, @Param("activo") Boolean, Pageable)` → `Page<Cat{Nombre}DTO>`
    - Filtrar por texto en campos string relevantes (`nombre`, `cve*`, códigos)
    - Filtrar por `blnActivo` cuando `activo` no es null
  - `findBy{Nombre}DTO(@Param("{pk}") {Tipo})` → `Cat{Nombre}DTO`

### 3. `ICat{Nombre}Service.java`
- Paquete: `business.Interface`
- Métodos:
  ```java
  PageResponseDTO<Cat{Nombre}DTO> list(String search, Pageable pageable);
  Cat{Nombre}DTO findById({TipoPK} id);        // parámetros según PK simple o compuesta
  Cat{Nombre}DTO create(Cat{Nombre}DTO dto);
  Cat{Nombre}DTO update({TipoPK} id, Cat{Nombre}DTO dto);
  ```

### 4. `Cat{Nombre}ServiceImpl.java`
- Paquete: `business`
- Anotaciones: `@Service`, `@Transactional`
- Inyección: `@Autowired` de los repositorios necesarios
- `list`: parsear `"activo"` → `true`, `"inactivo"` → `false`, cualquier otro valor → `texto`
- `create`: instanciar entidad, setear campos desde DTO, guardar, retornar `mapToDTO()`
- `update`: buscar por PK con `.orElseThrow(() -> new RuntimeException("... no encontrado"))`, setear campos (sin modificar la PK), guardar, retornar `mapToDTO()`
  - **No sobreescribir la clave primaria en `update`**
- `mapToDTO()`: método privado con builder del DTO
- Si hay FK, inyectar el repositorio de la entidad relacionada y resolver con `.orElseThrow()`

### 5. `Cat{Nombre}Controller.java`
- Paquete: `controller`
- Anotaciones: `@RestController`, `@RequestMapping(CatalogPaths.CONTROLLER)`
- Inyección: `@Autowired ICat{Nombre}Service service`
- Endpoints:
  ```java
  @GetMapping(LIST_{KEY})    → ResponseEntity<PageResponseDTO<DTO>>  // ?search=&page=&size=
  @GetMapping(FIND_{KEY})    → ResponseEntity<DTO>                   // @PathVariable PK
  @PostMapping(SAVE_{KEY})   → ResponseEntity<DTO>  HttpStatus.CREATED
  @PutMapping(UPDATE_{KEY})  → ResponseEntity<DTO>                   // @PathVariable PK + @RequestBody
  ```
- **No incluir imports de otros catálogos**

### 6. `CatalogPaths.java` — agregar constantes
```java
/*entidad CAT_{NOMBRE} */
public static final String LIST_{KEY}   = "/{key}/list";
public static final String SAVE_{KEY}   = "/{key}/save";
public static final String FIND_{KEY}   = "/{key}/{pk}";
public static final String UPDATE_{KEY} = "/{key}/{pk}";
```
- Si la PK es compuesta: `FIND_{KEY} = "/{key}/{pk1}/{pk2}"` con nombres descriptivos

---

## Reglas y convenciones del proyecto

| Aspecto | Convención |
|---|---|
| Base path | `/api/catalogs` (heredado de `ConstPath.BASE_API_PATH`) |
| Paginación | `PageResponseDTO<T>` con fields: `content`, `page`, `size`, `totalElements`, `totalPages`, `last` |
| Fechas entidad | `Instant` o `LocalDate` según la entidad — respetar el tipo existente |
| Fechas DTO | Mismo tipo que la entidad |
| Inyección | `@Autowired` (no constructor injection) |
| Transacciones | `@Transactional` en el `ServiceImpl` |
| Error handling | `RuntimeException` con mensaje descriptivo |
| Lombok en entidad | `@Data` es suficiente — no agregar `@Getter`/`@Setter` redundantes |
| Lombok en DTO | `@Data` + `@Builder` + `@AllArgsConstructor` + `@NoArgsConstructor` |

---

## Caso especial: PK compuesta (`@EmbeddedId`)

Cuando la entidad usa `@EmbeddedId`:
- El `findById` en el service recibe los dos campos por separado: `findById(String pk1, String pk2)`
- El `update` recibe los dos campos como `@PathVariable` en el controller
- El path de `FIND` y `UPDATE` tiene dos variables: `/{pk1}/{pk2}`
- En el `ServiceImpl.create()` se instancia el `{Nombre}Id` y se asigna a `entity.setId(id)`
- En el `ServiceImpl.update()` **no** se modifica el `id` embebido

---

## Ejemplo de objeto JSON para prueba de INSERT

```json
{
  "campo1": "valor_ejemplo",
  "campoBoolActivo": true,
  "fecIniVigencia": "2024-01-01T00:00:00Z",
  "fecFinVigencia": null
}
```
- `fecIniVigencia` y `blnActivo` son **siempre obligatorios**
- Fechas en formato ISO-8601: `"2024-01-01T00:00:00Z"` para `Instant`, `"2024-01-01"` para `LocalDate`
