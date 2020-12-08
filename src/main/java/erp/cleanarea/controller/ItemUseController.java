package erp.cleanarea.controller;

import erp.cleanarea.domain.item.InOut;
import erp.cleanarea.domain.item.Item;
import erp.cleanarea.domain.item.ItemUse;
import erp.cleanarea.repository.ItemRepository;
import erp.cleanarea.repository.ItemUseRepository;
import erp.cleanarea.service.ItemUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class ItemUseController {

    @Autowired
    private ItemUseRepository itemUseRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemUseService itemUseService;

    @GetMapping("/item/itemUse/insert")
    public String itemUseInsertForm(Model model,
                                    @RequestParam Long id
    ) {

        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Item item = itemRepository.findOneById(id);
        model.addAttribute("id", id);
        model.addAttribute("name", "물품명 : " + item.getName());
        model.addAttribute("count", "현 재고량 : " + item.getCount() + "(" + item.getCountSub() + ")");
        model.addAttribute("today", LocalDate.now());
        return "item/itemUseForm";
    }

    @PostMapping("/item/itemUse/insert")
    public String itemUseInsert(Model model,
                                @RequestParam("id") Long id,
                                @RequestParam("useDay") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate useDay,
                                @RequestParam("useCount") int useCount,
                                @RequestParam("place") String place,
                                @RequestParam("price") int price,
                                @RequestParam("io") InOut io,
                                @RequestParam("memo") String memo
    ) {
        Item item = itemRepository.findOneById(id);

        if (io.equals(InOut.입고)) {
            item.setCount(item.getCount() + useCount);
            itemRepository.save(item);
        } else if (io.equals(InOut.출고)) {
            item.setCount(item.getCount() - useCount);
            itemRepository.save(item);
        }
        System.out.println(item.getCount());
        ItemUse itemUse = ItemUse.builder()
                .item(item).useDay(useDay).place(place).price(price)
                .useCount(useCount).io(io).memo(memo).stack(item.getCount())
                .build();
        itemUseRepository.save(itemUse);
        return "redirect:/item/itemUse";
    }
    @GetMapping("/item/itemUse")
    public String itemUse(Model model,
                          @PageableDefault Pageable pageable
    ){
        Page<ItemUse> itemUses = itemUseService.getItemUseList(pageable);
        for (ItemUse itemUse : itemUses) {
            System.out.println(itemUse.getUseDay());
        }
        model.addAttribute("itemUses", itemUses);
        return "/item/itemUse";
    }
    @GetMapping("/item/itemUse/byItem")
    public String itemUseByItem(Model model
    ){
        return "/item/itemUse";
    }
//    @GetMapping("/item/useList/search")
//    public String itemUseListSearch(Model model,
//                              @RequestParam(value="useDay", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate useDay,
//                              @PageableDefault Pageable pageable
//    ){
//        Page<ItemUse> itemUses = itemUseService.getItemUseList(pageable);
//        model.addAttribute("itemUses", itemUses);
//        return "item/useList";
//    }
//
//
//    @PostMapping("/item/use")
//    public String itemUse(Model model,
//                          @RequestParam(value="useTo") String useTo, //Enum Input, Output
//                          @RequestParam(value="id") Long id,
//                          @RequestParam(value="count") int count,
//                          @RequestParam(value="place") String place,
//                          @RequestParam(value="memo", defaultValue = "") String memo,
//                          @RequestParam(value="useDay") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate useDay
//    ){
//        Item item = itemRepository.findOnlyById(id);
//        if(useTo.equals("Input")){
//            ItemUse itemUse = new ItemUse(item, useDay, InOut.입고, count, place, memo);
//            item.setCount(item.getCount() + count);
//            itemUseRepository.save(itemUse);
//        }else if(useTo.equals("Output")){
//            ItemUse itemUse = new ItemUse(item, useDay, InOut.출고, count, place, memo);
//            item.setCount(item.getCount() - count);
//            itemUseRepository.save(itemUse);
//        }
//        return "redirect:/item/useList";
//    }
//
//    @GetMapping("/item/useList/perItem")
//    public String itemUseListPerItem (Model model,
//                          @RequestParam(value="useDay") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDay,
//                          @RequestParam(value="useDay") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDay
//
//    ){
//        model.addAttribute("startDay", startDay);
//        model.addAttribute("endDay", endDay);
//
//        return "";
//    }
//
//    @GetMapping("/item/useList/total")
//    public String itemUseListTotal (Model model,
//                                      @RequestParam(value="useDay") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDay,
//                                      @RequestParam(value="useDay") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDay
//
//    ){
//        model.addAttribute("startDay", startDay);
//        model.addAttribute("endDay", endDay);
//        return "";
//    }

}
