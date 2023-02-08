package com.example.bvk.service;

import com.example.bvk.model.entity.Cart;
import com.example.bvk.model.request.FindByTrxIdRequest;
import com.example.bvk.model.response.CartResponse;
import com.example.bvk.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class GetCartByTrxId {

    CartRepository cartRepository;

    public GetCartByTrxId(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartResponse execute(FindByTrxIdRequest findByTrxIdRequest){
        this.doValidateRequest(findByTrxIdRequest);
        List<Cart> cartList = cartRepository.getCartByTrxId(findByTrxIdRequest.trxId);
        log.info("cart response = {}", cartList);
        return CartResponse.builder()
                .trxId(findByTrxIdRequest.getTrxId())
                .items(cartList)
                .subTotalPrice(this.sumTotalPrice(cartList))
                .build();
    }

    private void doValidateRequest(FindByTrxIdRequest input){
        if(ObjectUtils.isEmpty(input.getTrxId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trx Id cannot be empty");
        }
    }

    private BigDecimal sumTotalPrice(List<Cart> input){
        AtomicReference<BigDecimal> atomicSumTotal = new AtomicReference<>();
        atomicSumTotal.set(BigDecimal.ZERO);
        input.forEach(data -> {
            atomicSumTotal.accumulateAndGet(data.getItem().getPrice().multiply(BigDecimal.valueOf(Long.valueOf(data.getQty()))), BigDecimal::add);
        });
        return atomicSumTotal.get();
    }

}
