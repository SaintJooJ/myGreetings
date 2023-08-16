package mygreetings.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

private static final String template = "Hello, %s!";
private final AtomicLong counter = new AtomicLong();
List<Greetings> greetings = new ArrayList<>();

@GetMapping("/greetings")
public Greetings greetings(@RequestParam(value = "name", defaultValue = "World") String name) {

return new Greetings(counter.incrementAndGet(),String.format(template, name));

}

@GetMapping("/greetings")
public List<Greetings> greetings(@RequestParam(value = "name" , defaultValue = "World") String name,
  @RequestParam(value = "count", defaultValue = "1") int cont) {


for(int i = 0; i < cont; i++) {
greetings.add(new Greetings(counter.incrementAndGet(), String.format(template, name)));
}

return greetings;
}

@GetMapping("/greetings/{id}")
public Greetings getGreetingsById(@RequestParam(value = "id") int id) {

if(id >= 0 && id < greetings.size()) {
return greetings.get(id);
} else {
throw new IndexOutOfBoundsException("Índice Inválido");
}

}

}