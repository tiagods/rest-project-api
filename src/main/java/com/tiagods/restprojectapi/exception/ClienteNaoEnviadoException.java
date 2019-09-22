package com.tiagods.restprojectapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ClienteNaoEnviadoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ClienteNaoEnviadoException(String mensagem) {
		super(mensagem);
	}
	public ClienteNaoEnviadoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
