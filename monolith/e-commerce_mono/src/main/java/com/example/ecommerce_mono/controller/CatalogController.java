package com.example.ecommerce_mono.controller;

import com.example.ecommerce_mono.dto.CatalogDto;
import com.example.ecommerce_mono.dto.OrderDto;
import com.example.ecommerce_mono.entity.Catalog;
import com.example.ecommerce_mono.repository.CatalogRepository;
import com.example.ecommerce_mono.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog-service")
public class CatalogController {
    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
        catalogService.createCatalog();
    }

    @GetMapping(value = "/catalogs")
    public String catalogList(Model model){
        Iterable<Catalog> catalogList = catalogService.getAllCatalogs();
        List<CatalogDto> result = new ArrayList<>();
        catalogList.forEach(v -> {
            CatalogDto catalogDto = new CatalogDto();
            catalogDto.setUnitPrice(v.getUnitPrice());
            catalogDto.setStock(v.getStock());
            catalogDto.setProductName(v.getProductName());
            result.add(catalogDto);
        });
        model.addAttribute("catalogList", result);
        return "catalog/catalogList";
    }

    @GetMapping(value = "/{productName}/{userId}")
    public String catalogDtl(Model model, @PathVariable("productName") String productName, @PathVariable("userId") String userId) {
        Catalog catalog = catalogService.findByProductName(productName);
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(catalog.getId());
        orderDto.setProductName(catalog.getProductName());
        orderDto.setUnitPrice(catalog.getUnitPrice());
        orderDto.setStock(catalog.getStock());
        orderDto.setUserId(userId);
        model.addAttribute("orderDto", orderDto);
        return "orders/orderForm";
    }
}
