package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public void adicionarUsuario(Usuario usuario, MultipartFile imagem) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		String caminho = "images/" + usuario.getNome() + ".png";
		AulaFileUtils.salvarImagem(caminho,imagem);
		
		usuarioRepository.save(usuario);
	}


	public List<Usuario> retornarTodosOsUsuarios() {
		
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.getOne(id);
	}


	public void removerUsuario(Long id) {
		usuarioRepository.deleteById(id);
		
	}
	

}
