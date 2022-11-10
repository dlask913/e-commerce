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
    public String getCatalogs(Model model) {
        Iterable<Catalog> catalogList = catalogService.getAllCatalogs();
        List<CatalogDto> result = new ArrayList<>();
        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, CatalogDto.class));
        });
        model.addAttribute("catalogList",result);
        return "catalog/catalogList";
    }

//    @GetMapping("catalog-service/catalogs/{productId}")
//    public ResponseEntity<List<CatalogDto>> getOrders(@PathVariable("productId") String productId,Model model){
//        Catalog catalog = catalogService.getCatalogByProductId(productId);
//        List<CatalogDto> result = new ArrayList<>();
//        result.add(new ModelMapper().map(catalog, CatalogDto.class));
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
}
