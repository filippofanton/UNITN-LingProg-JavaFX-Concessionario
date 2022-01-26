package it.untin.ldpA.main;

import java.util.LinkedHashSet;

public class Automobili extends LinkedHashSet<Auto> {
    public String toString(){
        String ret = "Marca\t-\tModello\t-\tPrezzo\n";
        for(Auto a : this){
            ret += a.toString();
            ret += "\n";
        }
        return ret;
    }
}
