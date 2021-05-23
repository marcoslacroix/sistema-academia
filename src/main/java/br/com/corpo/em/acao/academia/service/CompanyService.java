package br.com.corpo.em.acao.academia.service;

import br.com.corpo.em.acao.academia.dto.company.CompanyDto;
import br.com.corpo.em.acao.academia.dto.company.create.CompanyCreateDto;
import br.com.corpo.em.acao.academia.mapper.company.CompanyCreateMapper;
import br.com.corpo.em.acao.academia.mapper.company.CompanyMapper;
import br.com.corpo.em.acao.academia.model.Company;
import br.com.corpo.em.acao.academia.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyDto create(CompanyCreateDto companyCreateDto) {
        verifyCompanyExists(companyCreateDto.getName());
        Company company = CompanyCreateMapper.INSTANCE.toCompany(companyCreateDto);
        companyRepository.save(company);
        return CompanyMapper.INSTANCE.toCompanyDto(company);
    }

    private void verifyCompanyExists(String companyName) {
        Company company = companyRepository.findByName(companyName).orElse(null);
        if (nonNull(company)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Empresa já cadastrada");
        }
    }

    public Company findById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (isNull(company)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Empresa com o ID %s não encontrada ", id));
        }
        return company;
    }

    @Transactional
    public void delete(Long id) {
        Company company = findById(id);
        company.setUpdatedOn(LocalDateTime.now());
        company.setDeleted(true);
        companyRepository.save(company);
    }
}

