package com.udemy.cursospring.cursospring.services.validation;

import com.udemy.cursospring.cursospring.dto.ClienteNewDTO;
import com.udemy.cursospring.cursospring.model.enums.TipoCliente;
import com.udemy.cursospring.cursospring.resources.exceptions.FieldMessage;
import com.udemy.cursospring.cursospring.services.validation.utils.BRCPFeCNPJ;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClienteInsert ann) {
    }
    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCodigo())
                && !BRCPFeCNPJ.isValidCpf(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
        }

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
                && !BRCPFeCNPJ.isValidCnpj(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}