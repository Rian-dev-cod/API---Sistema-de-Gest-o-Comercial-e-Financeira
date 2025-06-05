package com.upsolucoes.gestaocomercial.service.empresa;

import java.util.Optional;

import com.upsolucoes.gestaocomercial.model.empresa.Empresa;

public interface EmpresaService {
    Empresa salvar(Empresa empresa);
    Optional<Empresa> buscarPorId(Long id);
}