package com.generation.learnpoint.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.learnpoint.model.Usuario;
import com.generation.learnpoint.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		
		Optional<Usuario> usuario = userRepository.findByLogin(userName);
		 
	  
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));


		return usuario.map(UserDetailsImpl::new).get();
	}
}