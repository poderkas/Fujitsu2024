package com.poderkas.fujitsu2024.Exceptions;

public class DeliveryNotFoundException extends RuntimeException{
    public DeliveryNotFoundException(String message) {
        super(message);
    }
}
