package org.might.sinaction.properties;

import org.might.sinaction.db.entity.Ingredient;
import org.might.sinaction.db.entity.Taco;
import org.might.sinaction.db.entity.User;
import org.might.sinaction.db.repositories.IngredientRepository;
import org.might.sinaction.db.repositories.TacoRepository;
import org.might.sinaction.db.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@Profile("development")
public class DevelopmentConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo, PasswordEncoder passwordEncoder, TacoRepository tacoRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.deleteAll();
                userRepo.deleteAll();

                userRepo.save(new User(
                        "123",
                        passwordEncoder.encode("123"),
                        "Craig Walls",
                        "123 North Street",
                        "Cross Roads",
                        "TX",
                        "76227",
                        "123-123-1234"));

                Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
                Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
                Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
                Ingredient carnitas = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
                Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
                Ingredient lettuce = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
                Ingredient cheddar = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
                Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
                Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
                Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);
                repo.save(flourTortilla);
                repo.save(cornTortilla);
                repo.save(groundBeef);
                repo.save(carnitas);
                repo.save(tomatoes);
                repo.save(lettuce);
                repo.save(cheddar);
                repo.save(jack);
                repo.save(salsa);
                repo.save(sourCream);

                Taco taco1 = new Taco();
                taco1.setName("Carnivore");
                taco1.setIngredients(Arrays.asList(
                        flourTortilla, groundBeef, carnitas,
                        sourCream, salsa, cheddar));
                tacoRepository.save(taco1);

                Taco taco2 = new Taco();
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(
                        cornTortilla, groundBeef, cheddar,
                        jack, sourCream));
                tacoRepository.save(taco2);

                Taco taco3 = new Taco();
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(
                        flourTortilla, cornTortilla, tomatoes,
                        lettuce, salsa));
                tacoRepository.save(taco3);
            }
        };
    }

}
