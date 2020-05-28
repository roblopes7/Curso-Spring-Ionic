package com.udemy.cursospring.cursospring.resources;

import com.udemy.cursospring.cursospring.model.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {
        Categoria categoria1 = new Categoria(1, "Informatica");
        Categoria categoria2 = new Categoria(1, "Escritorio");

        List<Categoria> categorias = new ArrayList<>();
        categorias.add(categoria1);
        categorias.add(categoria2);

        return  categorias;
    }
}
