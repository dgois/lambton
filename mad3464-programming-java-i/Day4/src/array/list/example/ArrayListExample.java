/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array.list.example;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author macstudent
 */
public class ArrayListExample {
    
    public static void main(String[] args) {
        int[] a = {10, 1, 50, 30, 40};
        
        Arrays.sort(a);
        
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.binarySearch(a, 30));
        
        Movie[] movies = {new Movie(2, "Matrix"), new Movie(4, "Figth Club"), new Movie(3, "Inception"), new Movie(1, "Forrest Gump")};
        
        Arrays.sort(movies, (o1, o2) -> o1.getId() - o2.getId());
        System.out.println(Arrays.toString(movies));
        
        Arrays.sort(movies, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        System.out.println(Arrays.toString(movies));
        
        ArrayList<Movie> moviesList = new ArrayList<>(Arrays.asList(movies));
        
        moviesList.forEach(System.out::println);
        
    }
}
