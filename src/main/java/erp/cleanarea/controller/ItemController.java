package erp.cleanarea.controller;


import erp.cleanarea.domain.item.Item;
import erp.cleanarea.domain.item.ItemForm;
import erp.cleanarea.repository.ItemRepository;
import erp.cleanarea.service.ItemService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@NoArgsConstructor
public class ItemController {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;


    @GetMapping("/item")
    public String item(Model model,
                       @PageableDefault Pageable pageable
    ){
        Page<Item> items = itemService.getItemList(pageable);
        model.addAttribute("items", items);
        return "item/item";
    }

    @PostMapping("/item/addItem")
    public String addItem(Model model,
                          @ModelAttribute ItemForm itemForm
    ){
        itemRepository.save(new Item(itemForm));
        return "redirect:/item";
    }

    @GetMapping("/item/update")
    public String itemUpdateForm(Model model,
                       @RequestParam(value = "id") Long id){
        Item item = itemRepository.findOneById(id);
        model.addAttribute("item", item);
        return "item/itemUpdate";
    }
    @PostMapping("/item/update")
    public String itemUpdate(Model model,
                       @RequestParam(value = "id") Long id,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "manage") String manage,
                       @RequestParam(value = "division") String division,
                       @RequestParam(value = "buyPrice") int buyPrice,
                       @RequestParam(value = "supply") String supply,
                       @RequestParam(value = "supplyInfo") String supplyInfo,
                       @RequestParam(value = "memo") String memo
                       ){
        Item item = itemRepository.findOneById(id);
        System.out.println(item.getName());
        System.out.println("name = " + name);
        item.setName(name);
        item.setManage(manage);
        item.setDivision(division);
        item.setBuyPrice(buyPrice);
        item.setSupply(supply);
        item.setSupplyInfo(supplyInfo);
        item.setMemo(memo);
        itemRepository.save(item);
        return "redirect:/item";
    }

    @PostMapping("/item/search")
    public String itemSearchPost(Model model,
                             @PageableDefault Pageable pageable,
                             @RequestParam String name
    ){
        Page<Item> items = itemService.itemSearch(pageable, name);
        model.addAttribute("items", items);
        model.addAttribute("name", name);

        return "item/itemSearch";
    }
    @GetMapping("/item/search")
    public String itemSearch(Model model,
                             @PageableDefault Pageable pageable,
                             @RequestParam String name
    ){
        Page<Item> items = itemService.itemSearch(pageable, name);
        model.addAttribute("items", items);
        model.addAttribute("name", name);
        return "item/itemSearch";
    }





//    /**
//     * 물품 목록, 페이징
//     * */
//    @GetMapping("/item")
//    public String itemList(Model model,
//                           @PageableDefault Pageable pageable
//    ){
//        Page<Item> items = itemService.getItemList(pageable);
//        model.addAttribute("items", items);
//        model.addAttribute("today", LocalDate.now());
//        return "item/item";
//    }
//    /**
//     * 물품 목록 검색
//     * */
//    @GetMapping("/item/search")
//    public String itemGetSearch(Model model,
//                             @PageableDefault Pageable pageable,
//                             @RequestParam(value = "name") String name
//    ){
//        Page<Item> items = itemService.itemSearch(pageable, name);
//        model.addAttribute("items", items);
//        model.addAttribute("name", name);
//        return "item/itemSearch";
//    }

//    @PostMapping("/item/search")
//    public String itemPostSearch(Model model,
//                       @PageableDefault Pageable pageable,
//                       @RequestParam(value = "name") String name
//    ){
//        Page<Item> items = itemService.itemSearch(pageable, name);
//        model.addAttribute("items", items);
//        model.addAttribute("name", name);
//        return "item/itemSearch";
//    }

//    /**
//     * 물품 등록 폼
//     * */
//    @GetMapping("/item/new")
//    public String itemInsertForm(Model model){
//        model.addAttribute("itemForm", new ItemForm());
//        return "item/new";
//    }

//    /**
//     * 물품 등록
//     * */
//    @PostMapping("/item/new")
//    public String createItem(
//            @ModelAttribute ItemForm form, Model model){
//        Item item = new Item(form);
//        itemRepository.save(item);
//
//        return "redirect:/item";
//    }


//    /**
//     * 물품 입-출고
//     */
//    @PostMapping("/item/use")
//        public String itemUse(Model model,
//                              @RequestParam("use") String use,
//                              @RequestParam("id") int id,
//                              @RequestParam("count") int count
//    ){
//
//        return "redirect: /item/useList";
//    }


}
