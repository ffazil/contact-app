package com.leanthoughts.x.contact.api;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author ffazil
 * @since 01/11/15
 */
@SpringBootApplication
public class ContactApiStarter {

    @Bean
    CommandLineRunner runner(ContactRepository contactRepository){
        return args ->{
            Arrays.asList("Firoz,Fahad,Rayan,Navin,Sumitha,Nidhin".split(","))
                    .forEach(name -> contactRepository.save(new Contact(name)));
            contactRepository.findAll().forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactApiStarter.class, args);
    }




}


@RepositoryRestResource
interface ContactRepository extends JpaRepository<Contact, Long>{

    @RestResource(path = "byName")
    Collection<Contact> findByName(@Param("name") String name);
}

@Entity
@Data
class Contact{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Contact(){

    }

    public Contact(String name){
        this.name = name;
    }


}
