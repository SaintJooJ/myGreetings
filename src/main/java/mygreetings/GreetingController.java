package mygreetings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private static final String template = "Hello, $s!";
  private final AtomicLong counter = new AtomicLong();
  private List<Greeting> greetings = new ArrayList<>(null);

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
    greetings.add(greeting);
    return greeting;
  }

  @GetMapping("/greetings")
  public List<Greeting> greetings(){
    return greetings;
  }

}