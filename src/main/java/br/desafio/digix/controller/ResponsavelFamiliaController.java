package br.desafio.digix.controller;

import java.io.IOException;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.desafio.digix.dto.ResponsavelFamiliaRequestDTO;
import br.desafio.digix.dto.ResponsavelFamiliaResponseDTO;
import br.desafio.digix.models.ResponsavelFamilia;
import br.desafio.digix.repository.ResponsavelFamiliaRepository;
import br.desafio.digix.service.ResponsavelFamiliaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = { "/api/v1/responsavelFamilia" }, produces = { "application/json" })
public class ResponsavelFamiliaController {
    private final ResponsavelFamiliaService responsavelFamiliaService;

    public ResponsavelFamiliaController(ResponsavelFamiliaService responsavelFamiliaService) {
        this.responsavelFamiliaService = responsavelFamiliaService;
    }

    @Autowired
    private ResponsavelFamiliaRepository responsavelFamiliaRepository;

    @Operation(summary = "Cadastrar um novo responsavelFamilia")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<ResponsavelFamilia> cadastrar(
            @RequestBody @Valid ResponsavelFamiliaRequestDTO responsavelFamiliaRequestDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responsavelFamiliaService.cadastrar(responsavelFamiliaRequestDTO));
    }

    @Operation(summary = "Buscar um responsavelFamilia pelo seu id")
    @ApiResponse(responseCode = "200", description = "Retorna a responsavel solicitada")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponsavelFamiliaResponseDTO> buscarPorId(@PathVariable Long id)
            throws NameNotFoundException {
        return ResponseEntity.ok(responsavelFamiliaService.buscarPorId(id));
    }

    @Operation(summary = "Alterar um responsavelFamilia")
    @ApiResponse(responseCode = "200")
    @PutMapping(path = "/{id}", consumes = { "application/json" })
    public ResponseEntity<ResponsavelFamiliaResponseDTO> alterarDesejo(
            @RequestBody ResponsavelFamiliaRequestDTO responsavelFamiliaRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(responsavelFamiliaService.alterar(responsavelFamiliaRequestDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        responsavelFamiliaRepository.deleteById(id);
    }
}
