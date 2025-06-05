package com.upsolucoes.gestaocomercial.service.impl.empresa;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upsolucoes.gestaocomercial.model.empresa.Empresa;
import com.upsolucoes.gestaocomercial.repository.empresa.EmpresaRepository;
import com.upsolucoes.gestaocomercial.service.empresa.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository repository;

    public EmpresaServiceImpl(EmpresaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Empresa salvar(Empresa empresa) {
        return repository.save(empresa);
    }

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return repository.findById(id);
    }
}