package com.affordmed.demo.model;

import java.util.SortedSet;

/**
 * @author Udhaya
 * Created on 02-03-2023
 */
public class Response {
    private SortedSet<Integer> numbers;

    public Response(SortedSet<Integer> numbers) {
        this.numbers = numbers;
    }

    public SortedSet<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(SortedSet<Integer> numbers) {
        this.numbers = numbers;
    }
}

