package erp.cleanarea.repository;

import erp.cleanarea.domain.item.Item;
import erp.cleanarea.domain.item.ItemUse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ItemUseRepository extends JpaRepository<ItemUse, Long> {

    Page<ItemUse> findAll(Pageable pageable);

    List<ItemUse> findAllByUseDayBetween(LocalDate startDay, LocalDate endDay);

    List<ItemUse> findAllItemUseByUseDayBetween(LocalDate startDay, LocalDate endDay);
}
