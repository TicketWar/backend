package com.ticketwar.ticketwar.customer.dto;

import com.ticketwar.ticketwar.customer.entity.Customer;

public class AddCustomerRequest {
    private String nickname;
    private String email;

    public  Customer toEntity() {
        return Customer.builder()
                .nickname(nickname)
                .email(email)
                .build();
    }
}
