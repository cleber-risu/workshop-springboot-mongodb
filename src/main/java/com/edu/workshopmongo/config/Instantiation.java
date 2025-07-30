package com.edu.workshopmongo.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edu.workshopmongo.domain.Post;
import com.edu.workshopmongo.domain.User;
import com.edu.workshopmongo.dto.AuthorDTO;
import com.edu.workshopmongo.repository.PostRepository;
import com.edu.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, criarInstantGMT(2018, 3, 21, 12, 0), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, criarInstantGMT(2018, 3, 23, 12, 0), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

	public Instant criarInstantGMT(int ano, int mes, int dia, int hora, int minuto) {
	    LocalDateTime ldt = LocalDateTime.of(ano, mes, dia, hora, minuto);
	    return ldt.atZone(ZoneId.of("GMT")).toInstant();
	}

}
