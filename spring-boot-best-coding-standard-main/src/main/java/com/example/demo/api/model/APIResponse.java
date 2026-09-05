package com.example.demo.api.model;


public record APIResponse<T>(T data, String message, int status) {

}
