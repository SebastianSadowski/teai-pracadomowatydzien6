package pl.sadowski.teaipracadomowatydzien6.MoviesAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.sadowski.teaipracadomowatydzien6.Aspects.SendEmailBefore;
import pl.sadowski.teaipracadomowatydzien6.MoviesAPI.Model.Movie;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/movie")
public class MovieController {

        List<Movie>  movieArrayList = new ArrayList<>();



    public MovieController() {
    }
@GetMapping
    public ResponseEntity<List<Movie>> getAll(){
        Optional<List<Movie>> op = Optional.ofNullable(movieArrayList);
        return op.map(list -> new ResponseEntity<>(list, HttpStatus.FOUND)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    @SendEmailBefore(attachDate = true)
    public ResponseEntity<String> addVideo(@Valid @RequestBody Movie movie){

        ResponseEntity r = movieArrayList.add(movie)?ResponseEntity.ok("Dodalem pomyslnie film"):new ResponseEntity<>("Blad serwera", HttpStatus.INTERNAL_SERVER_ERROR);
        System.out.println("WYKONALEM");
        return r;
    }


@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationsException(MethodArgumentNotValidException ex){
            Map<String, String> hash = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(e -> hash.put(((FieldError)e).getField(), e.getDefaultMessage()));
            return hash;
    }
}
