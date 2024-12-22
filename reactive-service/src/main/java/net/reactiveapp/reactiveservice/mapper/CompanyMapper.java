package net.reactiveapp.reactiveservice.mapper;

import net.reactiveapp.reactiveservice.dto.CompanyRequest;
import net.reactiveapp.reactiveservice.dto.CompanyResponse;
import net.reactiveapp.reactiveservice.entities.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponse companyToDtoResponse(Company company);
    Company requestDtoToCompany(CompanyRequest request);
    CompanyRequest companyToRequestDTO(Company company);
}
