package br.com.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.training.model.User;
import br.com.training.repository.UserRepository;

@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody @Valid User user) {
		return userRepository.save(user);
	}

	@GetMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
    public User getUser (@PathVariable String cpf){
        return userRepository.findByCpf(cpf);
    }
	
	@DeleteMapping (value = "/{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable String cpf) {
		User user = userRepository.findByCpf(cpf);
		
		if(user != null) {
			userRepository.delete(user);
		}
	}
	
	@PutMapping (value = "/{cpf}")
	public ResponseEntity<User> putUser(@PathVariable String cpf, @RequestBody @Valid User userDetails) {
		User existingUser = userRepository.findByCpf(cpf);
		
		if(existingUser != null) {
			existingUser.setName(existingUser.getName());
			existingUser.setCpf(existingUser.getCpf());
			existingUser.setBirthDate(existingUser.getBirthDate());
			existingUser.setEmail(existingUser.getEmail());
			return ResponseEntity.ok(existingUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
