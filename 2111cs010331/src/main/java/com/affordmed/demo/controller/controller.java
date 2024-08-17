package com.affordmed.demo.controller;

import com.affordmed.demo.model.Response;
import com.affordmed.demo.network.HttpRequest;
import com.affordmed.demo.util.URLUtil; // Assuming toURL is in URLUtil
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class Problem1Controller {

    @GetMapping("/numbers")
    public CompletableFuture<Response> numbers(@RequestParam("url") List<String> urls) {
        return getResponses(urls).thenApply(response -> {
            TreeSet<Integer> unique = new TreeSet<>();
            for (SortedSet<Integer> set : response) {
                if (set != null) {
                    unique.addAll(set);
                }
            }
            return new Response(unique);
        });
    }

    public CompletableFuture<List<SortedSet<Integer>>> getResponses(List<String> urls) {
        List<CompletableFuture<SortedSet<Integer>>> futures = new ArrayList<>();
        for (String url : urls) {
            CompletableFuture<SortedSet<Integer>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return url != null ? HttpRequest.getResponse(URLUtil.toURL(url)).getNumbers() : null;
                } catch (Exception e) {
                    return null;
                }
            });
            futures.add(future);
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }
}

