package com.example.catalogservice.controller;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("catalog-service/catalogs")
    public ResponseEntity<List<CatalogDto>> getCatalogs(Model model){
        Iterable<Catalog> catalogList = catalogService.getAllCatalogs();
        List<CatalogDto> result = new ArrayList<>();
        catalogList.forEach(v -> {
            CatalogDto catalogDto = new ModelMapper().map(v, CatalogDto.class);
            catalogDto.setQty(v.getStock());
            result.add(catalogDto);
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
