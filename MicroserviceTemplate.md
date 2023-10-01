# Link for Spring Initializer :

https://start.spring.io/

# Swagger Links :

1. Editor New :- http://editor-next.swagger.io/
2. Editor Old :- http://editor.swagger.io/
3. Swagger UI :- http://localhost:<port>/<context-path>/swagger-ui.html

# Swagger Annotations for Entity : 

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity

@Id
@GeneratedValue(strategy = GeneratedType.IDENTITY)

# Swagger Annotations for DTO : 

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

# Packages to be Made : 

controller
service
configuration
exceptions
exceptionHandler
repo
dto
enums
model

# Controller Template :

@RestController
public class ClassNameController {
    @Autowired
    private ClassNameService classNameService;

    /*
     * Post mapping for adding a new item.
     * */
    @PostMapping("/")
    @Operation(summary = "Add a new item.")
    public ResponseEntity<ClassName> create(@RequestBody ClassName className) {
        return new ResponseEntity<>(classNameService.add(className), HttpStatus.CREATED);
    }

    /*
     * Put mapping for updating an existing item.
     * */
    @PutMapping("/")
    @Operation(summary = "Update an item.")
    public ResponseEntity<ClassName> update(@RequestBody ClassName className) throws InvalidIdException {
        return new ResponseEntity<>(classNameService.update(className), HttpStatus.OK);
    }

    /*
     * Get mapping for fetching an existing item.
     * */
    @GetMapping("/{id}")
    @Operation(summary = "Get an item.")
    public ResponseEntity<ClassName> create(@PathVariable("id") int id) throws InvalidIdException {
        return new ResponseEntity<>(classNameService.get(id), HttpStatus.OK);
    }

    /*
     * Delete mapping for deleting an existing item.
     * */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an item.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) throws InvalidIdException {
        classNameService.delete(id);
    }

}

# Service Interface Template CRUD :

public interface ClassNameService {
    public ClassName add(ClassName className) throws AlreadyExistsException;

    public ClassName update(ClassName className) throws InvalidIdException;

    public ClassName get(int id) throws InvalidIdException;

    public void delete(int id) throws InvalidIdException;
}

# Service Class Template CRUD : 

@Component
public class ClassNameServiceImpl implements ClassNameService {

    @Autowired
    public ClassNameRepo classNameRepo;

    /*
     * Creates a new ClassName if not already existing.
     * returns: Object of the newly created ClassName.
     * throws: AlreadyExistsException if the className already exists.
     * */
    @Override
    public ClassName add(ClassName className) throws AlreadyExistsException {
        if(classNameRepo.findByName(className.getName()) != null) throw new AlreadyExistsException("ClassName with name " + className.getName() + " already exists");
        return classNameRepo.save(className);
    }

    /*
     * Updates an ClassName if it is already existing.
     * returns: Object of the updated ClassName.
     * throws: InvalidIDException if the id does not exist in the database.
     * */
    @Override
    public ClassName update(ClassName className) throws InvalidIdException {
        ClassName cls = classNameRepo.findById(className.getId()).orElseThrow(() -> new InvalidIdException("No className found for id " + className.getId()));
        return classNameRepo.save(className);
    }

    /*
     * Fetches an ClassName if it is already existing using id.
     * returns: Object of the requested ClassName.
     * throws: InvalidIDException if the id does not exist in the database.
     * */
    @Override
    public ClassName get(int id) throws InvalidIdException {
        return classNameRepo.findById(id).orElseThrow(() -> new InvalidIdException("No className found for id " + id));

    }

    /*
     * Deletes an ClassName if already existing using id.
     * returns: Acknowledgement as a String.
     * throws: InvalidIDException if the id does not exist in the database.
     * */
    @Override
    public void delete(int id) throws InvalidIdException {
        ClassName className = classNameRepo.findById(id).orElseThrow(() -> new InvalidIdException("No className found for id " + id));
        classNameRepo.delete(className);
    }
}

# Repository Interface Template :

public interface ClassNameRepo extends JpaRepository<ClassName, Integer> {
    
}