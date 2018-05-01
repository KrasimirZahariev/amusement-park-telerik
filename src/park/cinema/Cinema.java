package park.cinema;

import park.products.*;
import park.stores.CashDesk;
import park.users.User;

import java.util.*;

public class Cinema {
    private String name;
    private CashDesk desk;
    private Map<Product, Integer> productsInStock;
    private Set<Movie> movies;


    public Cinema(String name) {
        this.name = name;
        this.desk = new CashDesk(500);
        this.productsInStock = new HashMap<>();
        this.movies = new HashSet<>();
    }

    public Cinema(String name, CashDesk desk, Map<Product, Integer> productsInStock) {
        this(name);
        this.productsInStock.putAll(productsInStock);
    }

    public Cinema(String name, CashDesk desk, Set<Movie> movies) {
        this(name);
        this.movies.addAll(movies);
    }

    public String getName() {
        return name;
    }

    public CashDesk getDesk() {
        return desk;
    }

    public Set getMovies() {
        return movies;
    }

    public void addMovie(Set<Movie> moviesToAdd) {
        movies.addAll(moviesToAdd);
    }

    public void watchMovie(User user, Movie movie) {
        System.out.println(user.getName() + "is watching " + movie.getName());
    }

    public void displayMovies() {
        System.out.println("Movies you can watch:");
        movies.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public void addProduct(Product product) {
        boolean isContained = isFoodContained(productsInStock, product);
        if (isContained) {
            productsInStock.computeIfPresent(product, (k, v) -> v + 1); //TODO : test this
        } else {
            productsInStock.put(product, 0);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    private boolean isFoodContained(Map<Product, Integer> productsInStock, Product product) {
        return productsInStock.containsKey(product);
    }
}
