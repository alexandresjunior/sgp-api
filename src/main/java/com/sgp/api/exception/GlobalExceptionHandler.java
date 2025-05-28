package com.sgp.api.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sgp.api.dto.ExcecaoDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExcecaoDTO> tratarExcecaoValidacao(MethodArgumentNotValidException ex) {
        List<FieldError> camposErros = ex.getFieldErrors();

        List<String> erros = new ArrayList<>();

        for (FieldError campoErro : camposErros) {
            String erro = campoErro.getDefaultMessage();

            erros.add(erro);
        }

        ExcecaoDTO dto = new ExcecaoDTO(LocalDate.now(), erros);
        
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExcecaoDTO> tratarExcecoesGenericas(Exception ex) {
        List<String> erros = new ArrayList<>();

        erros.add("Erro inesperado! Tente novamente mais tarde.");
        erros.add(ex.toString());

        return ResponseEntity.internalServerError().body(new ExcecaoDTO(LocalDate.now(), erros));
    }
}
