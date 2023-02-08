package com.example.bvk.repository;

import com.example.bvk.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT MAX(id) FROM cart", nativeQuery = true)
    public Long selectMaxId();

    @Query(value = "SELECT * FROM cart c WHERE c.trx_id = :trxId", nativeQuery = true)
    public List<Cart> getCartByTrxId(@Param("trxId") String trxId);

    @Query(value = "SELECT * FROM cart c WHERE c.trx_id = :trxId and c.item_id = :itemId", nativeQuery = true)
    public Optional<Cart> getItemOnCart(@Param("trxId") String trxId, @Param("itemId") Long itemId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM cart WHERE trx_id = :trxId and item_id = :itemId", nativeQuery = true)
    public void doDeleteItemOnCart(@Param("trxId") String trxId, @Param("itemId") Long itemId);

    @Query(value = "SELECT count(*) from cart where item_id = :itemId", nativeQuery = true)
    public Integer checkItemsOnCart(@Param("itemId") Long itemId);

}
