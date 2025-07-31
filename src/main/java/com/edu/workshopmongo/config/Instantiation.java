package com.edu.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edu.workshopmongo.domain.Post;
import com.edu.workshopmongo.domain.User;
import com.edu.workshopmongo.dto.AuthorDTO;
import com.edu.workshopmongo.dto.CommentDTO;
import com.edu.workshopmongo.repository.PostRepository;
import com.edu.workshopmongo.repository.UserRepository;
import com.edu.workshopmongo.utils.DateUtils;

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
		
		Post post1 = new Post(null, DateUtils.createInstantGMT(2018, 3, 21, 12, 0), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, DateUtils.createInstantGMT(2018, 3, 23, 12, 0), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", DateUtils.createInstantGMT(2018, 3, 21, 0, 0), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", DateUtils.createInstantGMT(2018, 3, 22, 0, 0), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", DateUtils.createInstantGMT(2018, 3, 23, 0, 0), new AuthorDTO(alex));
		
		post1.setComments(Arrays.asList(c1, c2));
		post2.setComments(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}
