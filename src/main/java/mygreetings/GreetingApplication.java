package mygreetings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingApplication {

private static final String template = "Hello, %s!";
private final AtomicLong counter = new AtomicLong();
List<Greeting> greetings = new ArrayList<>();

@GetMapping("/greetings")
public Greeting greetings(@RequestParam(value = "name", defaultValue = "World") String name) {

return new Greeting(counter.incrementAndGet(),String.format(template, name));

}

@GetMapping("/greetings")
public List<Greeting> greetings(@RequestParam(value = "name" , defaultValue = "World") String name,
  @RequestParam(value = "count", defaultValue = "1") int cont) {


for(int i = 0; i < cont; i++) {
greetings.add(new Greeting(counter.incrementAndGet(), String.format(template, name)));
}

return greetings;
}

@GetMapping("/greetings/{id}")
public Greeting getGreetingsById(@RequestParam(value = "id") int id) {

if(id >= 0 && id < greetings.size()) {
return greetings.get(id);
} else {
throw new IndexOutOfBoundsException("Índice Inválido");
}

}

}
